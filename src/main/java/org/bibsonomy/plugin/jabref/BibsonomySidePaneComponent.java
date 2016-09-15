package org.bibsonomy.plugin.jabref;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.gui.SidePaneComponent;
import net.sf.jabref.gui.SidePaneManager;

import org.bibsonomy.plugin.jabref.gui.BibsonomySidePanel;

/**
 * {@link BibsonomySidePaneComponent} holds the dimension of the {@link BibsonomySidePanel}.
 * Additionally it sets the icon and the name.
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class BibsonomySidePaneComponent extends SidePaneComponent {

    private static final long serialVersionUID = 8823318411871094917L;

    /**
     * the side pane manager
     */
    private SidePaneManager manager;

    /**
     * the jabref frame
     */
    private JabRefFrame jabRefFrame;

    public BibsonomySidePaneComponent(SidePaneManager manager, JabRefFrame jabRefFrame) {
        // set the icon and the name
        super(manager, new ImageIcon("/images/tag-label.png"), "BibSonomy");

        this.manager = manager;
        this.jabRefFrame = jabRefFrame;

        // add the sidepanel
        super.add(new BibsonomySidePanel(jabRefFrame));
    }

    /**
     * get the jabRefFrame
     *
     * @return the {@link JabRefFrame}
     */
    public JabRefFrame getJabRefFrame() {

        return jabRefFrame;
    }

    /**
     * get the sidePaneManager
     *
     * @return the {@link SidePaneManager}
     */
    public SidePaneManager getSidePaneManager() {

        return manager;
    }

    /**
     * set the preferred size to 550 pixels
     */
    @Override
    public Dimension getPreferredSize() {
        //TODO: Previous location was in GUIGlobals. Check alternatives - zellerdev
        final int SPLIT_PANE_DIVIDER_LOCATION = 145 + 15; // + 15 for possible scrollbar.
        return new Dimension(SPLIT_PANE_DIVIDER_LOCATION, 550);
    }

    /**
     * set the maximum size to 550 pixels
     */
    @Override
    public Dimension getMaximumSize() {

        return getPreferredSize();
    }

    @Override
    public int getRescalingWeight() {
        return 0;
    }
}
