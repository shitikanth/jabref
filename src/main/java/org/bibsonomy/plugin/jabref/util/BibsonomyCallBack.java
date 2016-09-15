package org.bibsonomy.plugin.jabref.util;

import net.sf.jabref.gui.importer.ImportInspectionDialog;

import org.bibsonomy.plugin.jabref.worker.AbstractBibsonomyWorker;

/**
 * {@link BibsonomyCallBack} is a util to stop execution of workers
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class BibsonomyCallBack implements ImportInspectionDialog.CallBack {

    private AbstractBibsonomyWorker worker;

    public void stopFetching() {
        if (worker != null)
            worker.stopFetching();

    }

    public BibsonomyCallBack(AbstractBibsonomyWorker pluginWorker) {
        this.worker = pluginWorker;
    }
}
