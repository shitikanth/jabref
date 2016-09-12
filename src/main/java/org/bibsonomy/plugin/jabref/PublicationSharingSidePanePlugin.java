package org.bibsonomy.plugin.jabref;

import javax.swing.JMenuItem;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.gui.SidePaneComponent;
import net.sf.jabref.gui.SidePaneManager;

import org.bibsonomy.plugin.jabref.gui.EntryEditorTabExtender;
import org.bibsonomy.plugin.jabref.gui.BibsonomyMenuItem;
import org.bibsonomy.plugin.jabref.gui.BibsonomyToolBarExtender;
import org.bibsonomy.plugin.jabref.listener.BibsonomyDataBaseChangeListener;
import org.bibsonomy.plugin.jabref.listener.TabbedPaneChangeListener;

/**
 * PublicationSharingSidePanelPlugin - This is the entry point of the plugin.
 * It defines the MenuItem, the ShortcutKey and the SidePaneComponent.
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class PublicationSharingSidePanePlugin {

    /**
     * The plugins side pane component
     */
    private BibsonomySidePaneComponent sidePaneComponent;

    /**
     * The plugins menu
     */
    public JMenuItem getMenuItem() {
        return new BibsonomyMenuItem(sidePaneComponent);
    }

    /**
     * the plugins shortcut key. None defined.
     */
    public String getShortcutKey() {
        return null;
    }

    /**
     * the plugins side pane component
     */
    public SidePaneComponent getSidePaneComponent() {
        return sidePaneComponent;
    }

    /**
     * This method will be called from JabRef to initialize the plugin.
     */
    public void init(JabRefFrame jabRefFrame, SidePaneManager manager) {

        // create a ChangeListener to react on newly added entries.
        BibsonomyDataBaseChangeListener bibsonomyDataBaseChangeListener = new BibsonomyDataBaseChangeListener(jabRefFrame);

        // set a ChangeListener of the Tabbed Pane which registers the databasechangelistener to all database tabs that are added later
        jabRefFrame.getTabbedPane().addChangeListener(new TabbedPaneChangeListener(bibsonomyDataBaseChangeListener));
        // ...but maybe we were too late: Tabs are created by another (swing)thread so the initial tab change event after tab(and database) creation may be over already.
        // Therefore add the listener to the database of the current tab if it is already present.
        if (jabRefFrame.getCurrentBasePanel() != null && jabRefFrame.getCurrentBasePanel().getDatabase() != null) {
            jabRefFrame.getCurrentBasePanel().getDatabase().registerListener(bibsonomyDataBaseChangeListener);
        }

        this.sidePaneComponent = new BibsonomySidePaneComponent(manager, jabRefFrame);
        BibsonomyToolBarExtender.extend(jabRefFrame, sidePaneComponent);
        EntryEditorTabExtender.extend();
    }

}
