package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.gui.SidePaneManager;

import org.bibsonomy.plugin.jabref.PluginSidePaneComponent;
import org.bibsonomy.plugin.jabref.gui.PluginSidePanel;

/**
 * Display or hide the {@link PluginSidePanel}
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class ToggleSidePaneComponentAction extends AbstractAction {

    private static final long serialVersionUID = -7479157135407308314L;

    private SidePaneManager manager;
    private JabRefFrame jabRefFrame;

    private PluginSidePaneComponent sidePaneComponent;

    public void actionPerformed(ActionEvent e) {

        if (!manager.hasComponent("BibSonomy"))
            manager.register("BibSonomy", sidePaneComponent);

        if (jabRefFrame.getTabbedPane().getTabCount() > 0)
            manager.toggle("BibSonomy");

    }

    public ToggleSidePaneComponentAction(PluginSidePaneComponent sidePaneComponent) {

        super("Search entries", new ImageIcon(ToggleSidePaneComponentAction.class.getResource("/images/tag-label.png")));

        this.sidePaneComponent = sidePaneComponent;

        this.manager = sidePaneComponent.getSidePaneManager();
        this.jabRefFrame = sidePaneComponent.getJabRefFrame();
    }
}
