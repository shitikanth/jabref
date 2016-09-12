package org.bibsonomy.plugin.jabref.listener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.jabref.gui.JabRefFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bibsonomy.plugin.jabref.worker.DownloadDocumentsWorker;

/**
 * {@link PluginDataBaseChangeListener} runs the {@link DownloadDocumentsWorker} as soon as a new entry was added
 * to the database
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
//public class PluginDataBaseChangeListener implements DatabaseChangeListener {
public class PluginDataBaseChangeListener {

    private static final Log LOGGER = LogFactory.getLog(PluginDataBaseChangeListener.class);

    private JabRefFrame jabRefFrame;

    private final ExecutorService threadPool;

    public PluginDataBaseChangeListener(JabRefFrame jabRefFrame) {

        this.jabRefFrame = jabRefFrame;

        threadPool = Executors.newSingleThreadExecutor();

    }
/*
    public void databaseChanged(final DatabaseChangeEvent event) {

        if (event.getType() == DatabaseChangeEvent.ChangeType.ADDED_ENTRY) {

            threadPool.execute(new Runnable() {
                public void run() {
                    final BibEntry entry = event.getSource().getEntryById(event.getEntry().getId());
                    if (entry == null) {
                        return;
                    }
                    try {
                        // sleep here because we implicitly rely on another
                        // DatabaseChangeListener (the JabRef internal
                        // GlazedEntrySorter) to have finished in advance.
                        // Simply sleeping is not 100% safe also but there
                        // seems to be no event to wait for this properly.
                        Thread.sleep(200);
                        DownloadDocumentsWorker worker = new DownloadDocumentsWorker(jabRefFrame, entry, true);
                        WorkerUtil.performAsynchronously(worker);

                    } catch (InterruptedException e) {
                        LOGGER.warn("Interrupt while downloading private documents", e);
                        Thread.currentThread().interrupt();
                    } catch (Throwable e) {
                        LOGGER.error("Error while downloading private documents", e);
                    }
                }
            });
        }

    }
    */

}
