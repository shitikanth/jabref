package org.bibsonomy.plugin.jabref.util;

import java.util.Set;

import net.sf.jabref.model.entry.BibEntry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Helper methods for BibEntry objects (the internal jabref representation)
 *
 * @author Dominik Benz <benz@cs.uni-kassel.de>
 */
public class BibtexEntryUtil {

    private static final Log LOGGER = LogFactory.getLog(BibtexEntryUtil.class);

    /**
     * Check the (string) equality of two BibTex entries
     * //TODO: Search for another solution - zellerdev
     * //FIXME: Use FieldName values and check isPresent()
     * @param firstBibEntry
     * @param secondBibEntry
     * @return true if the entries are the same
     */
    public static boolean areEqual(final BibEntry firstBibEntry, final BibEntry secondBibEntry) {
        final Set<String> commonFields = firstBibEntry.getFieldNames();
        commonFields.addAll(secondBibEntry.getFieldNames());
        LOGGER.debug("Total nr. of common fields: "
                + commonFields.size());
        for (final String field : commonFields) {
            BibtexEntryUtil.LOGGER.debug("Comparing field: " + field);

            // fields that should be ignored
            if ((field != null) && !field.startsWith("__")
                    && !"id".equals(field) && !"".equals(field)
                    && !"timestamp".equals(field)
                    && !"owner".equals(field)) {
                // check if b1 lacks a field that b2 has
                if (StringUtil.isEmpty(firstBibEntry.getField(field).get())
                        && !StringUtil.isEmpty(secondBibEntry.getField(field).get())) {
                    LOGGER.debug("field " + field
                            + " is null for b1 but not for b2");
                    return false;
                }
                // check if b2 lacks a field that b1 has
                if (StringUtil.isEmpty(secondBibEntry.getField(field).get())
                        && !StringUtil.isEmpty(firstBibEntry.getField(field).get())) {
                    LOGGER.debug("field " + field
                            + " is null for b2 but not for b1");
                    return false;
                }
                // check if both are empty/null -> OK
                if (StringUtil.isEmpty(firstBibEntry.getField(field).get())
                        && StringUtil.isEmpty(secondBibEntry.getField(field).get())) {
                    continue;
                }
                // check for fields of b1 if they are the same in b2
                if (!firstBibEntry.getField(field).equals(secondBibEntry.getField(field))) {
                    LOGGER.debug("Found inequality for field: "
                            + field);
                    return false;
                }
            }
        }
        return true;
    }
}