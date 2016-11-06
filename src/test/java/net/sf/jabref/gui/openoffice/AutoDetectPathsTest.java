package net.sf.jabref.gui.openoffice;

import javax.swing.JDialog;

import net.sf.jabref.Globals;
import net.sf.jabref.logic.openoffice.OpenOfficePreferences;
import net.sf.jabref.preferences.JabRefPreferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AutoDetectPathsTest {
    AutoDetectPaths adp;
    JDialog diag;
    @Before
    public void setUp() {
        diag = new JDialog();
        diag.setVisible(true);

        OpenOfficePreferences prefs =  new OpenOfficePreferences(JabRefPreferences.getInstance());
        adp = AutoDetectPaths.getInstance(diag, prefs);
    }

    @Test
    public void testAutoDetectPaths() {
        assertEquals(true, adp.autoDetectPaths());
    }

    @After
    public void tearDown() {
        diag.dispose();
    }
}
