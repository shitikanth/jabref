package org.bibsonomy.plugin.jabref.listener;

import java.awt.event.ActionEvent;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

import net.sf.jabref.gui.JabRefFrame;

import org.bibsonomy.plugin.jabref.action.AbstractBibsonomyAction;
import org.bibsonomy.plugin.jabref.gui.GroupingComboBoxItem;
import org.bibsonomy.plugin.jabref.gui.SearchType;
import org.bibsonomy.plugin.jabref.worker.ImportPostsByCriteriaWorker;

/**
 * {@link BibsonomyHyperLinkListener} runs the {@link ImportPostsByCriteriaWorker} as soon as
 * the user clicks on a hyperlink in the tag cloud
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class BibsonomyHyperLinkListener extends AbstractBibsonomyAction implements HyperlinkListener {

    private JComboBox<?> visibilityComboBox;

    public void hyperlinkUpdate(HyperlinkEvent e) {

        if (e.getEventType() == EventType.ACTIVATED) {
            StringTokenizer tokenizer = new StringTokenizer(e.getDescription(), " ");
            if (tokenizer.hasMoreElements()) {

                String criteria = tokenizer.nextToken();
                ImportPostsByCriteriaWorker worker = new ImportPostsByCriteriaWorker(getJabRefFrame(), criteria, SearchType.TAGS, ((GroupingComboBoxItem) visibilityComboBox.getSelectedItem()).getKey(), ((GroupingComboBoxItem) visibilityComboBox.getSelectedItem()).getValue(), false);
                performAsynchronously(worker);
            }

        }
    }

    public BibsonomyHyperLinkListener(JabRefFrame jabRefFrame, JComboBox<?> visibilityComboBox) {
        super(jabRefFrame, null, null);
        this.visibilityComboBox = visibilityComboBox;
    }

    public void actionPerformed(ActionEvent e) {}

}
