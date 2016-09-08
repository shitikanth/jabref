package org.bibsonomy.plugin.jabref.util;

import net.sf.jabref.gui.worker.AbstractWorker;
import net.sf.jabref.gui.worker.CallBack;
import net.sf.jabref.gui.worker.Worker;

/**
 * {@link WorkerUtil} runs a worker asynchronouslys
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class WorkerUtil {
	
	public static void performAsynchronously(AbstractWorker worker) throws Throwable {

		Worker wrk = worker.getWorker();
		CallBack cb = worker.getCallBack();
			
		worker.init();
			
		wrk.run();
			
		cb.update();
	}
}