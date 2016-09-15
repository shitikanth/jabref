package org.bibsonomy.plugin.jabref.worker;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.gui.worker.AbstractWorker;

import org.bibsonomy.model.logic.LogicInterface;
import org.bibsonomy.plugin.jabref.BibsonomyProperties;
import org.bibsonomy.plugin.jabref.util.JabRefFileFactory;
import org.bibsonomy.rest.client.RestLogicFactory;
import org.bibsonomy.rest.client.util.FileFactory;

/**
 * {@link AbstractBibsonomyWorker} is the base for all Workers which need to support stopping execution.
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public abstract class AbstractBibsonomyWorker extends AbstractWorker {

    private boolean fetchNext = true;
    private final FileFactory fileFactory;
    protected final JabRefFrame jabRefFrame;

    public AbstractBibsonomyWorker(JabRefFrame jabRefFrame) {
        this.jabRefFrame = jabRefFrame;
        this.fileFactory = new JabRefFileFactory(jabRefFrame);
    }

    public synchronized void stopFetching() {
        fetchNext = false;
    }

    protected synchronized boolean fetchNext() {
        return fetchNext;
    }

    protected LogicInterface getLogic() {
        return new RestLogicFactory(BibsonomyProperties.getApiUrl(), RestLogicFactory.DEFAULT_RENDERING_FORMAT, RestLogicFactory.DEFAULT_CALLBACK_FACTORY, fileFactory).getLogicAccess(BibsonomyProperties.getUsername(), BibsonomyProperties.getApiKey());
    }
}
