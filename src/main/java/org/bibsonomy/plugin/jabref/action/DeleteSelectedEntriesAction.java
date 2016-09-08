package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import net.sf.jabref.gui.JabRefFrame;

import org.bibsonomy.plugin.jabref.worker.DeletePostsWorker;

/**
 * {@link DeleteSelectedEntriesAction} runs the {@link DeletePostsWorker}.
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class DeleteSelectedEntriesAction extends AbstractPluginAction {

	private static final long serialVersionUID = 7650355756937904531L;
	
	public void actionPerformed(ActionEvent e) {
		
		DeletePostsWorker worker = new DeletePostsWorker(getJabRefFrame(), getJabRefFrame().getCurrentBasePanel().getSelectedEntries());
		performAsynchronously(worker);
	}

	public DeleteSelectedEntriesAction(JabRefFrame jabRefFrame) {
		
		super(jabRefFrame, "Delete selected entries", new ImageIcon(DeleteSelectedEntriesAction.class.getResource("/images/document--minus.png")));
	}
}
