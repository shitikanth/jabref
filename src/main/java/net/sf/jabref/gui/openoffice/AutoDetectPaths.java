package net.sf.jabref.gui.openoffice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import net.sf.jabref.gui.worker.AbstractWorker;
import net.sf.jabref.logic.l10n.Localization;
import net.sf.jabref.logic.openoffice.OpenOfficeFileSearch;
import net.sf.jabref.logic.openoffice.OpenOfficePreferences;
import net.sf.jabref.logic.util.OS;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Tools for automatically detecting JAR and executable paths to OpenOffice and/or LibreOffice.
 */
public abstract class AutoDetectPaths extends AbstractWorker {


    private final OpenOfficePreferences preferences;

    private boolean foundPaths;
    private boolean fileSearchCanceled;
    private JDialog prog;
    private final JDialog parent;


    protected final OpenOfficeFileSearch fileSearch = new OpenOfficeFileSearch();


    private AutoDetectPaths(JDialog parent, OpenOfficePreferences preferences) {
        this.parent = parent;
        this.preferences = preferences;
    }

    public boolean runAutodetection() {
        foundPaths = false;
        if (preferences.checkAutoDetectedPaths()) {
            return true;
        }
        init();
        getWorker().run();
        update();
        return foundPaths;
    }

    @Override
    public void run() {
        foundPaths = autoDetectPaths();
    }

    public boolean canceled() {
        return fileSearchCanceled;
    }

    @Override
    public void init() {
        prog = showProgressDialog(parent, Localization.lang("Autodetecting paths..."),
                Localization.lang("Please wait..."));
    }

    @Override
    public void update() {
        prog.dispose();
    }

    protected abstract List<File> findProgramFilesDir();

    protected abstract String getExecutableForPlatform();

    private List<File> findSofficeFiles(List<File> progFiles) {
        return new ArrayList<>(fileSearch.findFileInDirs(progFiles, getExecutableForPlatform()));
    }

    private boolean autoDetectPaths() {
        fileSearch.resetFileSearch();
        List<File> progFiles = findProgramFilesDir();
        List<File> sofficeFiles = findSofficeFiles(progFiles);

        if (fileSearchCanceled) {
            return false;
        }

        if (sofficeFiles.isEmpty()) {
            // TODO
        }
        Optional<File> actualFile = checkAndSelectAmongMultipleInstalls(sofficeFiles);
        if (actualFile.isPresent()) {
            return setupPreferencesForOO(actualFile.get().getParentFile(), actualFile.get(),
                    getExecutableForPlatform());
        }
        return false;
    }

    private boolean setupPreferencesForOO(String usrRoot, File inUsr, String sofficeName) {
        return setupPreferencesForOO(new File(usrRoot), inUsr, sofficeName);
    }

    private boolean setupPreferencesForOO(File rootDir, File inUsr, String sofficeName) {
        preferences.setExecutablePath(new File(inUsr, sofficeName).getPath());
        Optional<File> jurt = fileSearch.findFileInDir(rootDir, "jurt.jar");
        if (fileSearchCanceled) {
            return false;
        }
        if (jurt.isPresent()) {
            preferences.setJarsPath(jurt.get().getPath());
            return true;
        } else {
            return false;
        }
    }

    private Optional<File> checkAndSelectAmongMultipleInstalls(List<File> sofficeFiles) {
        if (sofficeFiles.isEmpty()) {
            return Optional.empty();
        } else if (sofficeFiles.size() == 1) {
            return Optional.of(sofficeFiles.get(0));
        }
        // Otherwise more than one file found, select among them
        DefaultListModel<File> mod = new DefaultListModel<>();
        for (File tmpfile : sofficeFiles) {
            mod.addElement(tmpfile);
        }
        JList<File> fileList = new JList<>(mod);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fileList.setSelectedIndex(0);
        FormBuilder builder = FormBuilder.create().layout(new FormLayout("left:pref", "pref, 2dlu, pref, 4dlu, pref"));
        builder.add(Localization.lang("Found more than one OpenOffice/LibreOffice executable.")).xy(1, 1);
        builder.add(Localization.lang("Please choose which one to connect to:")).xy(1, 3);
        builder.add(fileList).xy(1, 5);
        int answer = JOptionPane.showConfirmDialog(null, builder.getPanel(),
                Localization.lang("Choose OpenOffice/LibreOffice executable"), JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.CANCEL_OPTION) {
            return Optional.empty();
        } else {
            return Optional.of(fileList.getSelectedValue());
        }

    }

    public JDialog showProgressDialog(JDialog progressParent, String title, String message) {
        fileSearchCanceled = false;
        final ProgressDialog progressDialog = new ProgressDialogWithCancel(
                progressParent,
                title,
                message,
                event -> {
                    fileSearchCanceled = true;
                    fileSearch.cancelFileSearch();
                    ((JButton) event.getSource()).setEnabled(false);
                });
        progressDialog.showDialog();
        return progressDialog;
    }

    public static AutoDetectPaths getInstance(JDialog parent, OpenOfficePreferences preferences) {
        if(OS.WINDOWS)
            return new AutoDetectPathsWindows(parent, preferences);
        if(OS.OS_X)
            return new AutoDetectPathsOSX(parent, preferences);
        return new AutoDetectPathsLinux(parent, preferences);
    }

    private static class AutoDetectPathsWindows extends AutoDetectPaths {
        public AutoDetectPathsWindows(JDialog parent, OpenOfficePreferences preferences) {
            super(parent, preferences);
        }

        @Override
        protected List<File> findProgramFilesDir() {
            return fileSearch.findWindowsProgramFilesDir();
        }

        @Override
        protected String getExecutableForPlatform() {
            return OpenOfficePreferences.WINDOWS_EXECUTABLE;
        }
    }

    private static class AutoDetectPathsOSX extends AutoDetectPaths {
        public AutoDetectPathsOSX(JDialog parent, OpenOfficePreferences preferences) {
            super(parent, preferences);
        }

        @Override
        protected List<File> findProgramFilesDir() {
            return fileSearch.findOSXProgramFilesDir();
        }

        @Override
        protected String getExecutableForPlatform() {
            return OpenOfficePreferences.OSX_EXECUTABLE;
        }
    }

    private static class AutoDetectPathsLinux extends AutoDetectPaths {
        public AutoDetectPathsLinux(JDialog parent, OpenOfficePreferences preferences) {
            super(parent, preferences);
        }

        @Override
        protected List<File> findProgramFilesDir() {
            return fileSearch.findLinuxProgramFilesDir();
        }

        @Override
        protected String getExecutableForPlatform() {
            return OpenOfficePreferences.LINUX_EXECUTABLE;
        }
    }
}

