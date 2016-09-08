package org.bibsonomy.plugin.jabref.gui;

import net.sf.jabref.EntryEditor;
import net.sf.jabref.Globals;
import net.sf.jabref.preferences.JabRefPreferences;

import org.bibsonomy.plugin.jabref.PluginGlobals;
import org.bibsonomy.plugin.jabref.PluginProperties;


/**
 * {@link EntryEditorTabExtender} extends the {@link EntryEditor} with custom tabs.
 * @author Waldemar Biller <biller@cs.uni-kassel.de>
 *
 */
public class EntryEditorTabExtender {

	public static void extend() {
		
		boolean generalTab = false, bibsonomyTab = false, extraTab = false;
		int lastTabId = 0, extraTabID = -1;
		
		JabRefPreferences preferences = JabRefPreferences.getInstance();
		if(preferences.hasKey(JabRefPreferences.CUSTOM_TAB_NAME)) {
			
			
			while(preferences.hasKey(JabRefPreferences.CUSTOM_TAB_NAME + lastTabId)) {
				
				if(preferences.get(JabRefPreferences.CUSTOM_TYPE_NAME + lastTabId).equals(Globals.lang("General")))
					generalTab = true;
				
				if(preferences.get(JabRefPreferences.CUSTOM_TAB_NAME + lastTabId).equals(PluginGlobals.PLUGIN_NAME))
					bibsonomyTab = true;
				
				if("Extra".equals(preferences.get(JabRefPreferences.CUSTOM_TAB_NAME + lastTabId))) {
					extraTab = true; extraTabID = lastTabId;
				}
				
				lastTabId++;
			}
		}
		
		if(!generalTab) {
			
			preferences.put(JabRefPreferences.CUSTOM_TAB_FIELDS + lastTabId, "crossref;file;doi;url;citeseerurl;comment;owner;timestamp");
			preferences.put(JabRefPreferences.CUSTOM_TAB_NAME + lastTabId, Globals.lang("General"));
			lastTabId++;
		}
		
		if (!bibsonomyTab) {
			preferences.put(JabRefPreferences.CUSTOM_TAB_FIELDS + lastTabId, "interhash;intrahash;keywords;groups;privnote");
			preferences.put(JabRefPreferences.CUSTOM_TAB_NAME + lastTabId, "Bibsonomy");
			lastTabId++;
		}

		if (!extraTab) {
			preferences.put(JabRefPreferences.CUSTOM_TAB_FIELDS + lastTabId, PluginProperties.getExtraTabFields());
			preferences.put(JabRefPreferences.CUSTOM_TAB_NAME + lastTabId, "Extra");
		}

		if (extraTab) {
			if (!preferences.get(JabRefPreferences.CUSTOM_TAB_FIELDS + extraTabID).equals(
					PluginProperties.getExtraTabFields())) {
				preferences.put(JabRefPreferences.CUSTOM_TAB_FIELDS + extraTabID,
						PluginProperties.getExtraTabFields());
			}
		}
	}
}
