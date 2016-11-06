package net.sf.jabref.logic.openoffice;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OpenOfficeFileSearchTest {
    OpenOfficeFileSearch openOfficeFileSearch;
    @Before void setup() {
        openOfficeFileSearch = new OpenOfficeFileSearch();
    }
    @Test
    public void testFindOSXProgramFilesDir() {
        List<File> dirList = openOfficeFileSearch.findOSXProgramFilesDir();
        for (File dir : dirList) {
            System.out.println(dir.getAbsolutePath());
        }
    }
}
