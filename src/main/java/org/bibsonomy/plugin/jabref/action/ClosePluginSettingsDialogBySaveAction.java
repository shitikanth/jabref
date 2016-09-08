package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.bibsonomy.plugin.jabref.PluginProperties;
import org.bibsonomy.plugin.jabref.gui.GroupingComboBoxItem;
import org.bibsonomy.plugin.jabref.gui.OrderComboBoxItem;
import org.bibsonomy.plugin.jabref.gui.PluginSettingsDialog;

/**
 * {@link ClosePluginSettingsDialogBySaveAction} saves the properties and closes the {@link PluginSettingsDialog}.
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class ClosePluginSettingsDialogBySaveAction extends AbstractAction {


	private static final long serialVersionUID = 2629139512763809317L;
	private JTextField apiUrl;
	private JTextField username;
	private JTextField apiKey;
	private JCheckBox saveApiKey;
	private JSpinner numberOfPosts;
	private JSpinner tagCloudSize;
	private JCheckBox ignoreNoTagsAssigned;
	private JCheckBox updateTags;
	private JCheckBox uploadDocuments;
	private JCheckBox downloadDocuments;
	private JComboBox<?> visibility;
	private JCheckBox morePosts;
	private JTextField extraFields;
	private PluginSettingsDialog settingsDialog;
	private JComboBox<?> order;

	public void actionPerformed(ActionEvent e) {
	
		PluginProperties.setApiUrl(apiUrl.getText());
		PluginProperties.setUsername(username.getText());
		PluginProperties.setApiKey(apiKey.getText());
		PluginProperties.setStoreApiKey(saveApiKey.isSelected());
		PluginProperties.setNumberOfPostsPerRequest((Integer)numberOfPosts.getValue());
		PluginProperties.setTagCloudSize((Integer)tagCloudSize.getValue());
		PluginProperties.setIgnoreNoTagsAssigned(ignoreNoTagsAssigned.isSelected());
		PluginProperties.setUpdateTagsOnStartup(updateTags.isSelected());
		PluginProperties.setUploadDocumentsOnExport(uploadDocuments.isSelected());
		PluginProperties.setDownloadDocumentsOnImport(downloadDocuments.isSelected());
		PluginProperties.setIgnoreMorePostsWarning(morePosts.isSelected());
		PluginProperties.setExtraFields(extraFields.getText());
		PluginProperties.setTagCloudOrder(((OrderComboBoxItem)order.getSelectedItem()).getKey());
		
		switch (((GroupingComboBoxItem)visibility.getSelectedItem()).getKey()) {
		case USER:
			PluginProperties.setDefaultVisisbility("private");
			break;
		case GROUP:
			PluginProperties.setDefaultVisisbility(((GroupingComboBoxItem)visibility.getSelectedItem()).getValue());
			break;
		default:
			PluginProperties.setDefaultVisisbility("public");
		}
		
		PluginProperties.save();
		settingsDialog.setVisible(false);
	}

	public ClosePluginSettingsDialogBySaveAction(PluginSettingsDialog settingsDialog, JTextField apiUrl, JTextField username, JTextField apiKey, 
												 JCheckBox saveApiKey, JSpinner numberOfPosts, 
												 JSpinner tagCloudSize, JCheckBox ignoreNoTagsAssigned, 
												 JCheckBox updateTags, JCheckBox uploadDocuments, 
												 JCheckBox downloadDocuments, JComboBox<?> visibility, 
												 JCheckBox morePosts, JTextField extraFields, JComboBox<?> order) {
		
		super("Save", new ImageIcon(ClosePluginSettingsDialogBySaveAction.class.getResource("/images/disk-black.png")));
		this.apiUrl = apiUrl;
		this.settingsDialog = settingsDialog;
		this.username = username;
		this.apiKey = apiKey;
		this.saveApiKey = saveApiKey;
		this.numberOfPosts = numberOfPosts;
		this.tagCloudSize = tagCloudSize;
		this.ignoreNoTagsAssigned = ignoreNoTagsAssigned;
		this.updateTags = updateTags;
		this.uploadDocuments = uploadDocuments;
		this.downloadDocuments = downloadDocuments;
		this.visibility = visibility;
		this.morePosts = morePosts;
		this.extraFields = extraFields;
		this.order = order;
	}
}
