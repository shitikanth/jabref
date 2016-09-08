package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import net.sf.jabref.gui.JabRefFrame;

import org.bibsonomy.plugin.jabref.worker.SynchronizationWorker;


/**
 * {@link SynchronizeAction} runs the {@link SynchronizationWorker}
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class SynchronizeAction extends AbstractPluginAction {

	private static final long serialVersionUID = 5463500412046057018L;
	
	public void actionPerformed(ActionEvent e) {
		
		SynchronizationWorker worker = new SynchronizationWorker(getJabRefFrame());
		performAsynchronously(worker);
	}

	public SynchronizeAction(JabRefFrame jabRefFrame) {
		
		super(jabRefFrame,  "Synchronize", new ImageIcon(SynchronizeAction.class.getResource("/images/arrow-circle-double-135.png")));
		
	}
}
