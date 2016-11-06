package net.sf.jabref.logic.openoffice;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenOfficeFileSearchTest {
    OpenOfficeFileSearch openOfficeFileSearch;

    @Before
    public void setup() {
        openOfficeFileSearch = new OpenOfficeFileSearch();
    }
    @Test
    public void testFindOSXProgramFilesDir() {
        List<File> dirList = openOfficeFileSearch.findOSXProgramFilesDir();
        assertEquals(2, dirList.size());
        assertEquals("", dirList.get(0).getName());
        assertEquals("", dirList.get(1).getName());
    }
}
