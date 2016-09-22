package org.bibsonomy.plugin.jabref.worker;

import java.util.Arrays;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.logic.l10n.Localization;
import net.sf.jabref.model.entry.BibEntry;
import net.sf.jabref.model.entry.FieldName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bibsonomy.plugin.jabref.BibsonomyProperties;

/**
 * Delete a Post from the service
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class DeletePostsWorker extends AbstractBibsonomyWorker {

    private static final Log LOGGER = LogFactory.getLog(DeletePostsWorker.class);

    private BibEntry[] entries;

    public void run() {
        for (BibEntry entry : entries) {
            final String intrahash = entry.getField(FieldName.INTRAHASH).get();
            final String username = entry.getField(FieldName.USERNAME).get();
            if ((intrahash == null) || ("".equals(intrahash)) || ((intrahash != null) && !(BibsonomyProperties.getUsername().equals(username)))) {
                continue;
            }

            try {
                getLogic().deletePosts(BibsonomyProperties.getUsername(), Arrays.asList(intrahash));
                jabRefFrame.output(Localization.lang("Deleting post %0", intrahash));
                entry.clearField(FieldName.INTRAHASH);
            } catch (Exception ex) {
                LOGGER.error(Localization.lang("Failed deleting post %0", intrahash));
            }
        }
        jabRefFrame.output(Localization.lang("Done"));
    }

    public DeletePostsWorker(JabRefFrame jabRefFrame, BibEntry[] entries) {
        super(jabRefFrame);
        this.entries = entries;
    }
}
