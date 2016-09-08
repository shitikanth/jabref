package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import net.sf.jabref.gui.JabRefFrame;

import org.bibsonomy.common.enums.GroupingEntity;
import org.bibsonomy.plugin.jabref.PluginProperties;
import org.bibsonomy.plugin.jabref.gui.SearchType;
import org.bibsonomy.plugin.jabref.worker.ImportPostsByCriteriaWorker;


/**
 * {@link ImportAllMyPostsAction} runs the {@link ImportPostsByCriteriaWorker} to import all posts of the user.
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class ImportAllMyPostsAction extends AbstractPluginAction {

    public ImportAllMyPostsAction(JabRefFrame jabRefFrame) {

        super(jabRefFrame);
    }

    private static final long serialVersionUID = -6627950788884668738L;

    public void actionPerformed(ActionEvent e) {

        ImportPostsByCriteriaWorker worker = new ImportPostsByCriteriaWorker(getJabRefFrame(), "", SearchType.FULL_TEXT, GroupingEntity.USER, PluginProperties.getUsername(), true);
        performAsynchronously(worker);
    }

}
