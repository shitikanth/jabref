package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JComboBox;

import net.sf.jabref.gui.JabRefFrame;

import org.bibsonomy.plugin.jabref.gui.GroupingComboBoxItem;
import org.bibsonomy.plugin.jabref.worker.UpdateVisibilityWorker;

/**
 * {@link UpdateVisibilityAction} runs the {@link UpdateVisibilityWorker}
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class UpdateVisibilityAction extends AbstractBibsonomyAction {

    private static final long serialVersionUID = 2487196975142597818L;

    private JComboBox<GroupingComboBoxItem> visibility;
    private List<GroupingComboBoxItem> defaultGroupings;

    public UpdateVisibilityAction(JabRefFrame jabRefFrame, JComboBox<GroupingComboBoxItem> visibility, List<GroupingComboBoxItem> defaultGroupings) {
        super(jabRefFrame, null, null);

        this.visibility = visibility;
        this.defaultGroupings = defaultGroupings;
    }

    public void actionPerformed(ActionEvent e) {

        UpdateVisibilityWorker worker = new UpdateVisibilityWorker(getJabRefFrame(), visibility, defaultGroupings);
        performAsynchronously(worker);
    }

}
