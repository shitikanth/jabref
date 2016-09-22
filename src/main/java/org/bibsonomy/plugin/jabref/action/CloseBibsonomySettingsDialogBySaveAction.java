package org.bibsonomy.plugin.jabref.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import net.sf.jabref.logic.l10n.Localization;

import org.bibsonomy.plugin.jabref.BibsonomyProperties;
import org.bibsonomy.plugin.jabref.gui.BibsonomySettingsDialog;
import org.bibsonomy.plugin.jabref.gui.GroupingComboBoxItem;
import org.bibsonomy.plugin.jabref.gui.OrderComboBoxItem;

/**
 * {@link CloseBibsonomySettingsDialogBySaveAction} saves the properties and closes the {@link BibsonomySettingsDialog}.
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class CloseBibsonomySettingsDialogBySaveAction extends AbstractAction {

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
    private BibsonomySettingsDialog settingsDialog;
    private JComboBox<?> order;

    public void actionPerformed(ActionEvent e) {

        BibsonomyProperties.setApiUrl(apiUrl.getText());
        BibsonomyProperties.setUsername(username.getText());
        BibsonomyProperties.setApiKey(apiKey.getText());
        BibsonomyProperties.setStoreApiKey(saveApiKey.isSelected());
        BibsonomyProperties.setNumberOfPostsPerRequest((Integer) numberOfPosts.getValue());
        BibsonomyProperties.setTagCloudSize((Integer) tagCloudSize.getValue());
        BibsonomyProperties.setIgnoreNoTagsAssigned(ignoreNoTagsAssigned.isSelected());
        BibsonomyProperties.setUpdateTagsOnStartup(updateTags.isSelected());
        BibsonomyProperties.setUploadDocumentsOnExport(uploadDocuments.isSelected());
        BibsonomyProperties.setDownloadDocumentsOnImport(downloadDocuments.isSelected());
        BibsonomyProperties.setIgnoreMorePostsWarning(morePosts.isSelected());
        BibsonomyProperties.setExtraFields(extraFields.getText());
        BibsonomyProperties.setTagCloudOrder(((OrderComboBoxItem) order.getSelectedItem()).getKey());

        switch (((GroupingComboBoxItem) visibility.getSelectedItem()).getKey()) {
            case USER:
                BibsonomyProperties.setDefaultVisisbility("private");
                break;
            case GROUP:
                BibsonomyProperties.setDefaultVisisbility(((GroupingComboBoxItem) visibility.getSelectedItem()).getValue());
                break;
            default:
                BibsonomyProperties.setDefaultVisisbility("public");
        }

        BibsonomyProperties.save();
        settingsDialog.setVisible(false);
    }

    public CloseBibsonomySettingsDialogBySaveAction(BibsonomySettingsDialog settingsDialog, JTextField apiUrl, JTextField username, JTextField apiKey,
                                                    JCheckBox saveApiKey, JSpinner numberOfPosts,
                                                    JSpinner tagCloudSize, JCheckBox ignoreNoTagsAssigned,
                                                    JCheckBox updateTags, JCheckBox uploadDocuments,
                                                    JCheckBox downloadDocuments, JComboBox<?> visibility,
                                                    JCheckBox morePosts, JTextField extraFields, JComboBox<?> order) {

        super(Localization.lang("Save"), new ImageIcon(CloseBibsonomySettingsDialogBySaveAction.class.getResource("/images/images/disk-black.png")));
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
