package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.gui.SidePaneManager;
import net.sf.jabref.logic.l10n.Localization;

import org.bibsonomy.plugin.jabref.BibsonomySidePaneComponent;
import org.bibsonomy.plugin.jabref.gui.BibsonomySidePanel;

/**
 * Display or hide the {@link BibsonomySidePanel}
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class ToggleSidePaneComponentAction extends AbstractAction {

    private SidePaneManager manager;
    private JabRefFrame jabRefFrame;

    private BibsonomySidePaneComponent sidePaneComponent;

    public void actionPerformed(ActionEvent e) {
        if (!manager.hasComponent("BibSonomy"))
            manager.register("BibSonomy", sidePaneComponent);

        if (jabRefFrame.getTabbedPane().getTabCount() > 0)
            manager.toggle("BibSonomy");

    }

    public ToggleSidePaneComponentAction(BibsonomySidePaneComponent sidePaneComponent) {
        super(Localization.lang("Search entries"), new ImageIcon(ToggleSidePaneComponentAction.class.getResource("/images/images/tag-label.png")));

        this.sidePaneComponent = sidePaneComponent;
        this.manager = sidePaneComponent.getSidePaneManager();
        this.jabRefFrame = sidePaneComponent.getJabRefFrame();
    }
}
