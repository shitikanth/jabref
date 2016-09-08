package org.bibsonomy.plugin.jabref.action;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.gui.worker.AbstractWorker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bibsonomy.plugin.jabref.util.WorkerUtil;

/**
 * {@link AbstractPluginAction} is the base class for all actions.
 * Provides a method to run workers asynchronously.
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public abstract class AbstractPluginAction extends AbstractAction {

    private static final long serialVersionUID = -5607100284690271238L;

    private static final Log LOG = LogFactory.getLog(SearchAction.class);

    /**
     * the jabRefFrame
     */
    private JabRefFrame jabRefFrame;

    /**
     * creates a new Action with the parameters.
     *
     * @param jabRefFrame
     * @param text
     * @param icon
     */
    public AbstractPluginAction(JabRefFrame jabRefFrame, String text, Icon icon) {

        super(text, icon);
        this.jabRefFrame = jabRefFrame;
    }

    /**
     * Creates a action without text and icon
     *
     * @param jabRefFrame
     */
    public AbstractPluginAction(JabRefFrame jabRefFrame) {

        super();
        this.jabRefFrame = jabRefFrame;
    }

    /**
     * Runs a worker asynchronously. Includes exception handling.
     *
     * @param worker the worker to be run asynchronously
     */
    protected void performAsynchronously(AbstractWorker worker) {

        try {

            WorkerUtil.performAsynchronously(worker);
        } catch (Throwable t) {

            jabRefFrame.unblock();
            LOG.error("Failed to initialize Worker", t);
            t.printStackTrace();
        }
    }

    /**
     * get the jabRefFrame
     *
     * @return the {@link JabRefFrame}
     */
    protected JabRefFrame getJabRefFrame() {

        return jabRefFrame;
    }
}
