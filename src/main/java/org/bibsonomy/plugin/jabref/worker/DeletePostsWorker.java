package org.bibsonomy.plugin.jabref.worker;

import java.util.Arrays;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.model.entry.BibEntry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bibsonomy.plugin.jabref.BibsonomyProperties;

/**
 * Delete a Post from the service
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class DeletePostsWorker extends AbstractBibsonomyWorker {

    private static final Log LOG = LogFactory.getLog(DeletePostsWorker.class);

    private BibEntry[] entries;

    public void run() {
        for (BibEntry entry : entries) {
            final String intrahash = entry.getField("intrahash").get();
            final String username = entry.getField("username").get();
            if ((intrahash == null) || ("".equals(intrahash)) || ((intrahash != null) && !(BibsonomyProperties.getUsername().equals(username)))) {
                continue;
            }

            try {
                getLogic().deletePosts(BibsonomyProperties.getUsername(), Arrays.asList(intrahash));
                jabRefFrame.output("Deleting post " + intrahash);
                entry.clearField("intrahash");
            } catch (Exception ex) {
                LOG.error("Failed deleting post " + intrahash);
            }
        }
        jabRefFrame.output("Done.");
    }

    public DeletePostsWorker(JabRefFrame jabRefFrame, BibEntry[] entries) {
        super(jabRefFrame);
        this.entries = entries;
    }
}
