package org.bibsonomy.plugin.jabref.worker;

import java.util.List;

import javax.swing.JComboBox;

import net.sf.jabref.gui.JabRefFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bibsonomy.common.enums.GroupingEntity;
import org.bibsonomy.model.Group;
import org.bibsonomy.model.User;
import org.bibsonomy.plugin.jabref.BibsonomyProperties;
import org.bibsonomy.plugin.jabref.action.ShowSettingsDialogAction;
import org.bibsonomy.plugin.jabref.gui.GroupingComboBoxItem;
import org.bibsonomy.rest.exceptions.AuthenticationException;

/**
 * Fetch the users groups and add them to the "import posts from..." field
 *
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
public class UpdateVisibilityWorker extends AbstractBibsonomyWorker {

    private static final Log LOG = LogFactory.getLog(UpdateVisibilityWorker.class);

    private JComboBox<? super GroupingComboBoxItem> visibility;
    private List<GroupingComboBoxItem> defaultGroupings;

    public UpdateVisibilityWorker(JabRefFrame jabRefFrame, JComboBox<? super GroupingComboBoxItem> visibility, List<GroupingComboBoxItem> defaultGroupings) {
        super(jabRefFrame);
        this.visibility = visibility;
        this.defaultGroupings = defaultGroupings;
    }

    public void run() {
        GroupingComboBoxItem item = (GroupingComboBoxItem) visibility.getSelectedItem();

        visibility.removeAllItems();
        if (defaultGroupings != null) {
            for (GroupingComboBoxItem defaultGrouping : defaultGroupings) {
                visibility.addItem(defaultGrouping);
            }
        }

        try {
            User user = getLogic().getUserDetails(BibsonomyProperties.getUsername());

            for (Group g : user.getGroups()) {
                visibility.addItem(new GroupingComboBoxItem(GroupingEntity.GROUP, g.getName()));
            }

            if (item != null) {
                int count = visibility.getItemCount();
                for (int i = 0; i < count; i++) {
                    GroupingComboBoxItem currentItem = (GroupingComboBoxItem) visibility.getItemAt(i);
                    if (currentItem.getValue().equals(item.getValue())) {
                        visibility.setSelectedIndex(i);
                    }
                }

            }

        } catch (AuthenticationException ex) {
            (new ShowSettingsDialogAction(jabRefFrame)).actionPerformed(null);
        } catch (Exception ex) {
            LOG.error("Failed to get user details for user: " + BibsonomyProperties.getUsername(), ex);
        }
    }

}
