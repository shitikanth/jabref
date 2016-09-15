package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import net.sf.jabref.gui.JabRefFrame;

import org.bibsonomy.plugin.jabref.gui.BibsonomySettingsDialog;


/**
 * {@link ShowSettingsDialogAction} creates and displays the {@link BibsonomySettingsDialog}
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class ShowSettingsDialogAction extends AbstractAction {

    private static final long serialVersionUID = -953537334224313648L;

    private JabRefFrame jabRefFrame;

    public void actionPerformed(ActionEvent e) {

        BibsonomySettingsDialog psd = new BibsonomySettingsDialog(jabRefFrame);
        psd.setVisible(true);
        psd.setLocationRelativeTo(jabRefFrame);
    }

    public ShowSettingsDialogAction(JabRefFrame jabRefFrame) {

        super("Settings", new ImageIcon("/images/wrench-screwdriver.png"));
        this.jabRefFrame = jabRefFrame;
    }
}
