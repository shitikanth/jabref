package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.model.entry.BibEntry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bibsonomy.plugin.jabref.util.CheckTagsUtil;
import org.bibsonomy.plugin.jabref.worker.ExportWorker;

/**
 * {@link ExportSelectedEntriesAction} checks for entries without keywords and does
 * an export of all selected entries to the service.
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class ExportSelectedEntriesAction extends AbstractBibsonomyAction {

    private static final Log LOGGER = LogFactory
            .getLog(ExportSelectedEntriesAction.class);

    private static final long serialVersionUID = -3680150888244016437L;

    public void actionPerformed(ActionEvent e) {
        /*
         * fetch selected entries
		 */
        List<BibEntry> entries = getJabRefFrame().getCurrentBasePanel().getSelectedEntries();
		/*
		 * check if they all have keywords
		 */
        CheckTagsUtil ctu = new CheckTagsUtil(entries, getJabRefFrame());
        switch (ctu.hasAPostNoTagsAssigned()) {
            case JOptionPane.YES_OPTION:
                // tags missing & user has explicitly chosen to add default tag
                ctu.assignDefaultTag();
            case JOptionPane.DEFAULT_OPTION:
                // this means that all posts have tags
                ExportWorker worker = new ExportWorker(getJabRefFrame(), entries);
                performAsynchronously(worker);
                break;
            default:
                // happens when tags are missing, and user wants to cancel export
                LOGGER.debug("Selected post have no tags assigned");
        }

    }

    public ExportSelectedEntriesAction(JabRefFrame jabRefFrame) {

        super(jabRefFrame, "Export selected entries", new ImageIcon(ExportSelectedEntriesAction.class.getResource("/images/images/document--arrow.png")));
    }
}