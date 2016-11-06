package net.sf.jabref.gui.openoffice;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ProgressDialog extends JDialog {
    private String message;
    public ProgressDialog(JDialog parent, String title, String message) {
        super(parent, title, false);
        this.message = message;
    }

    public void showDialog() {
        this.setupUI();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    protected void setupUI() {
        addProgressBar();
        addMessageLabel();
    }

    private void addProgressBar() {
        JProgressBar bar = new JProgressBar(SwingConstants.HORIZONTAL);
        bar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bar.setIndeterminate(true);
        this.add(bar, BorderLayout.CENTER);
    }

    private void addMessageLabel() {
        this.add(new JLabel(this.message), BorderLayout.NORTH);
    }
}
