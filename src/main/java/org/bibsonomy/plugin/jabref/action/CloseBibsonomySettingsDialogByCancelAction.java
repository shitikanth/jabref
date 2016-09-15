package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.bibsonomy.plugin.jabref.gui.BibsonomySettingsDialog;

/**
 * {@link CloseBibsonomySettingsDialogByCancelAction} closes the {@link BibsonomySettingsDialog}
 * without saving the properties
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class CloseBibsonomySettingsDialogByCancelAction extends AbstractAction {

    private static final long serialVersionUID = -6587488658676754916L;

    private BibsonomySettingsDialog settingsDialog;

    public void actionPerformed(ActionEvent e) {

        settingsDialog.setVisible(false);
    }

    public CloseBibsonomySettingsDialogByCancelAction(BibsonomySettingsDialog settingsDialog) {

        super("Cancel", new ImageIcon(CloseBibsonomySettingsDialogByCancelAction.class.getResource("/images/images/cross.png")));
        this.settingsDialog = settingsDialog;
    }

}
