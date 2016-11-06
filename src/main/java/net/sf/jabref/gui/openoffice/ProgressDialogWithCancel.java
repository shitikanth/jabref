package net.sf.jabref.gui.openoffice;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import net.sf.jabref.logic.l10n.Localization;

public class ProgressDialogWithCancel extends ProgressDialog {
    private ActionListener actionListener;
    public ProgressDialogWithCancel(JDialog parent, String title, String message, ActionListener actionListener) {
        super(parent, title, message);
        this.actionListener = actionListener;
    }

    @Override
    protected void setupUI() {
        super.setupUI();
        addCancelButton();
    }

    private void addCancelButton() {
        JButton cancel = new JButton(Localization.lang("Cancel"));
        cancel.addActionListener(actionListener);
        add(cancel, BorderLayout.SOUTH);
    }
}
