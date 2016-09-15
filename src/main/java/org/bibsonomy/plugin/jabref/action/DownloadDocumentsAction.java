package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.ImageIcon;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.model.entry.BibEntry;

import org.bibsonomy.plugin.jabref.worker.DownloadDocumentsWorker;

/**
 * {@link DownloadDocumentsAction} collects all entries of a the {@link net.sf.jabref.model.database.BibDatabase BibDatabase} and
 * fetches all documents available for the post.
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class DownloadDocumentsAction extends AbstractPluginAction {

    private static final long serialVersionUID = 4623437071801648348L;

    public DownloadDocumentsAction(JabRefFrame jabRefFrame) {
        super(jabRefFrame, "Download my documents", new ImageIcon(DownloadDocumentsAction.class.getResource("/images/images/document-pdf-text.png")));

    }

    public void actionPerformed(ActionEvent e) {

        if (getJabRefFrame().getCurrentBasePanel() != null) {
            Collection<BibEntry> entries = getJabRefFrame().getCurrentBasePanel().getDatabase().getEntries();
            for (BibEntry entry : entries) {

                DownloadDocumentsWorker worker = new DownloadDocumentsWorker(getJabRefFrame(), entry, false);
                performAsynchronously(worker);
            }
        }
    }
}
