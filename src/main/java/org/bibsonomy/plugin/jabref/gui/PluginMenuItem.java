package org.bibsonomy.plugin.jabref.gui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.bibsonomy.plugin.jabref.PluginGlobals;
import org.bibsonomy.plugin.jabref.PluginSidePaneComponent;
import org.bibsonomy.plugin.jabref.action.DeleteSelectedEntriesAction;
import org.bibsonomy.plugin.jabref.action.DownloadDocumentsAction;
import org.bibsonomy.plugin.jabref.action.ExportSelectedEntriesAction;
import org.bibsonomy.plugin.jabref.action.ImportAllMyPostsAction;
import org.bibsonomy.plugin.jabref.action.ShowSettingsDialogAction;
import org.bibsonomy.plugin.jabref.action.SynchronizeAction;
import org.bibsonomy.plugin.jabref.action.ToggleSidePaneComponentAction;

/**
 * {@link PluginMenuItem} is the plugins menu item 
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class PluginMenuItem extends JMenu {

	
	private static final long serialVersionUID = -9004684574235429985L;

	private PluginSidePaneComponent sidePaneComponent;
	
	public PluginMenuItem(PluginSidePaneComponent sidePaneComponent) {
		
		super(PluginGlobals.PLUGIN_NAME);
		
		this.sidePaneComponent = sidePaneComponent;
		
		add(getSidePaneComponentToggleMenuItem());
		add(getExportSelectedEntriesMenuItem());
		add(getDeleteSelectedEntriesMenuItem());
		addSeparator();
		add(getSynchronizeMenuItem());
		add(getDownloadDocumentsMenuItem());
		add(getAllMyPostsMenuItem());
		addSeparator();
		add(getSettingsMenuItem());
	}
	
	public JMenuItem getSidePaneComponentToggleMenuItem() {
		
		return new JMenuItem(new ToggleSidePaneComponentAction(sidePaneComponent));
	}
	
	public JMenuItem getExportSelectedEntriesMenuItem() {
		
		return new JMenuItem(new ExportSelectedEntriesAction(sidePaneComponent.getJabRefFrame()));
	}
	
	public JMenuItem getDeleteSelectedEntriesMenuItem() {
		
		return new JMenuItem(new DeleteSelectedEntriesAction(sidePaneComponent.getJabRefFrame()));
	}
	
	public JMenuItem getSynchronizeMenuItem() {
		
		return new JMenuItem(new SynchronizeAction(sidePaneComponent.getJabRefFrame()));
	}
	
	public JMenuItem getSettingsMenuItem() {
		
		return new JMenuItem(new ShowSettingsDialogAction(sidePaneComponent.getJabRefFrame()));
	}
	
	public JMenuItem getAllMyPostsMenuItem() {
		JMenuItem item = new JMenuItem(new ImportAllMyPostsAction(sidePaneComponent.getJabRefFrame()));
		item.setText("Import all my posts");
		return item;
	}
	
	public JMenuItem getDownloadDocumentsMenuItem() {
		
		return new JMenuItem(new DownloadDocumentsAction(sidePaneComponent.getJabRefFrame()));
	}
}
