package org.bibsonomy.plugin.jabref.util;

import java.util.Set;

import net.sf.jabref.model.entry.BibEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Helper methods for BibEntry objects (the internal jabref representation)
 * 
 * @author Dominik Benz <benz@cs.uni-kassel.de>
 * 
 */
public class BibtexEntryUtil {

	private static final Log LOG = LogFactory.getLog(BibtexEntryUtil.class);

	/**
	 * Check the (string) equality of two BibTex entries
	 * 
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static boolean areEqual(final BibEntry b1, final BibEntry b2) {
		final Set<String> commonFields = b1.getFieldNames();
		commonFields.addAll(b2.getFieldNames());
		LOG.debug("Total nr. of common fields: "
				+ commonFields.size());
		for (final String field : commonFields) {
			BibtexEntryUtil.LOG.debug("Comparing field: " + field);

			// fields that should be ignored
			if ((field != null) && !field.startsWith("__")
					&& !"id".equals(field) && !"".equals(field)
					&& !"timestamp".equals(field)
					&& !"owner".equals(field)) {
				// check if b1 lacks a field that b2 has
				if (StringUtil.isEmpty(b1.getField(field))
						&& !StringUtil.isEmpty(b2.getField(field))) {
					LOG.debug("field " + field
							+ " is null for b1 but not for b2");
					return false;
				}
				// check if b2 lacks a field that b1 has
				if (StringUtil.isEmpty(b2.getField(field))
						&& !StringUtil.isEmpty(b1.getField(field))) {
					LOG.debug("field " + field
							+ " is null for b2 but not for b1");
					return false;
				}
				// check if both are empty/null -> OK
				if (StringUtil.isEmpty(b1.getField(field))
						&& StringUtil.isEmpty(b2.getField(field))) {
					continue;
				}
				// check for fields of b1 if they are the same in b2
				if (!b1.getField(field).equals(b2.getField(field))) {
					LOG.debug("Found inequality for field: "
							+ field);
					return false;
				}
			}
		}
		return true;
	}
}