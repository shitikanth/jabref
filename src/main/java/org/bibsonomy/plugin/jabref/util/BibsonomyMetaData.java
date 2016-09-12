package org.bibsonomy.plugin.jabref.util;

import net.sf.jabref.MetaData;

public class BibsonomyMetaData {

    private static MetaData metaData;

    private BibsonomyMetaData() {
    }

    public static MetaData getMetaData() {
        if (BibsonomyMetaData.metaData == null) {
            metaData = new MetaData();
            metaData.initializeNewDatabase();
        }
        return metaData;
    }

}
