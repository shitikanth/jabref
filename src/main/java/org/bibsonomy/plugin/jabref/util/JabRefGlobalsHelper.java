package org.bibsonomy.plugin.jabref.util;

import java.util.List;

import net.sf.jabref.Globals;
import net.sf.jabref.gui.JabRefFrame;

public class JabRefGlobalsHelper {

    /**
     * Returns database specific UserFileDir as String if exists, else null
     *
     * @param jabRefFrame
     * @return
     */
    public static String getDBSpecificUserFileDir(JabRefFrame jabRefFrame) {
        return getMetaDataValue(jabRefFrame, Globals.prefs.get("userFileDir"));
    }

    /**
     * Returns database specific UserFileDirIndividual as String if exists, else null
     *
     * @param jabRefFrame
     * @return
     */
    public static String getDBSpecificUserFileDirIndividual(JabRefFrame jabRefFrame) {
        return getMetaDataValue(jabRefFrame, Globals.prefs.get("userFileDirIndividual"));
    }

    /**
     * Returns database specific PDFDirectory as String if exists, else null
     *
     * @param jabRefFrame
     * @return
     */
    public static String getDBSpecificPDFDirectory(JabRefFrame jabRefFrame) {
        return getMetaDataValue(jabRefFrame, "pdfDirectory");
    }

    /**
     * Returns database specific PDFDirectory as String if exists, else null
     *
     * @param jabRefFrame
     * @return
     */
    public static String getDBSpecificPSDirectory(JabRefFrame jabRefFrame) {
        return getMetaDataValue(jabRefFrame, "psDirectory");
    }

    /**
     * Use {@link #getMetaDataValue(String)} instead
     */
    @Deprecated
    private static String getMetaDataValue(JabRefFrame jabRefFrame, String key) {
        //TODO: Find Meta Data
        //MetaData metaData = jabRefFrame.getCurrentBasePanel().metaData();
        List<String> fileDI = BibsonomyMetaData.getMetaData().getData(key);
        if (fileDI != null && fileDI.size() >= 1) {
            return fileDI.get(0).trim();
        }
        return null;
    }

    private static String getMetaDataValue(String key) {
        List<String> fileDI = BibsonomyMetaData.getMetaData().getData(key);
        if (fileDI != null && fileDI.size() >= 1) {
            return fileDI.get(0).trim();
        }
        return null;
    }

}
