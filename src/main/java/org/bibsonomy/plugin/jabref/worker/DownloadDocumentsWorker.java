package org.bibsonomy.plugin.jabref.worker;

import java.net.URLEncoder;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.model.entry.BibEntry;

import ca.odell.glazedlists.BasicEventList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bibsonomy.model.BibTex;
import org.bibsonomy.model.Document;
import org.bibsonomy.model.Post;
import org.bibsonomy.model.Resource;
import org.bibsonomy.plugin.jabref.BibsonomyProperties;
import org.bibsonomy.plugin.jabref.action.ShowSettingsDialogAction;
import org.bibsonomy.rest.exceptions.AuthenticationException;
import org.bibsonomy.util.file.FileUtil;

/**
 * Download all private documents of a post from the service
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class DownloadDocumentsWorker extends AbstractBibsonomyWorker {

    private static final Log LOG = LogFactory.getLog(DownloadDocumentsWorker.class);

    private static final String BIBTEX_FILE_FIELD = "file";


    private BibEntry entry;
    private boolean isImport;

    public DownloadDocumentsWorker(JabRefFrame jabRefFrame, BibEntry entry, boolean isImport) {
        super(jabRefFrame);
        this.entry = entry;
        this.isImport = isImport;
    }

    public void run() {

        if (isImport && !BibsonomyProperties.getDownloadDocumentsOnImport()) {
            return;
        }


        String intrahash = entry.getField("intrahash").get();
        if (intrahash != null && !"".equals(intrahash)) {
            final Post<? extends Resource> post;
            try {
                post = getLogic().getPostDetails(intrahash, BibsonomyProperties.getUsername()); // client.executeQuery(getPostDetailsQuery);
            } catch (AuthenticationException e) {
                (new ShowSettingsDialogAction(jabRefFrame)).actionPerformed(null);
                return;
            } catch (Exception e) {
                LOG.error("Failed getting details for post " + intrahash, e);
                jabRefFrame.output("Failed getting details for post.");
                return;
            }
            Resource r = post.getResource();
            if (!(r instanceof BibTex)) {
                LOG.warn("requested resource with intrahash '" + intrahash + "' is not bibtex");
                jabRefFrame.output("Error: Invalid Resourcetype.");
                return;
            }
            for (Document document : ((BibTex) r).getDocuments()) {
                jabRefFrame.output("Downloading: " + document.getFileName());
                try {
                    getLogic().getDocument(BibsonomyProperties.getUsername(), intrahash, URLEncoder.encode(document.getFileName(), "UTF-8"));
                } catch (Exception ex) {
                    LOG.error("Failed downloading file: " + document.getFileName(), ex);
                }

                try {
                    BasicEventList<BibEntry> list = new BasicEventList<BibEntry>();
                    String downloadedFileBibTex = ":" + document.getFileName() + ":" + FileUtil.getFileExtension(document.getFileName()).toUpperCase();

                    String entryFileValue = entry.getField(BIBTEX_FILE_FIELD).get();

                    list.getReadWriteLock().writeLock().lock();
                    list.add(entry);
                    if (entryFileValue != null && !"".equals(entryFileValue)) {

                        if (!entryFileValue.contains(downloadedFileBibTex))
                            entry.setField(BIBTEX_FILE_FIELD, entryFileValue + ";" + downloadedFileBibTex);
                    } else entry.setField(BIBTEX_FILE_FIELD, downloadedFileBibTex);
                    list.getReadWriteLock().writeLock().lock();

                } catch (AuthenticationException e) {
                    (new ShowSettingsDialogAction(jabRefFrame)).actionPerformed(null);
                } catch (Exception e) {
                    LOG.error("Failed adding file to entry " + entry.getCiteKey(), e);
                }

            }
        }

        jabRefFrame.output("Done.");
    }
}
