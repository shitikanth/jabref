package org.bibsonomy.plugin.jabref.util;

import net.sf.jabref.gui.importer.ImportInspectionDialog;
import org.bibsonomy.plugin.jabref.worker.AbstractPluginWorker;

/**
 * {@link PluginCallBack} is a util to stop execution of workers
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class PluginCallBack implements ImportInspectionDialog.CallBack {

	private AbstractPluginWorker worker;

	public void stopFetching() {
		
		if(worker != null)
			worker.stopFetching();
		
	}

	public PluginCallBack(AbstractPluginWorker pluginWorker) {
		
		this.worker = pluginWorker;
	}
}
