package net.sf.jabref.logic.search;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatchesHighlighterTest {

    @Test
    public void testHighlightWords() {
        assertEquals("", MatchesHighlighter.highlightWordsWithHTML("", Optional.empty()));
        assertEquals("Einstein", MatchesHighlighter.highlightWordsWithHTML("Einstein", Optional.empty()));
        assertEquals("<span style=\"background-color:#3399FF;\">Einstein</span>", MatchesHighlighter.highlightWordsWithHTML("Einstein", Optional.of(Pattern.compile("Einstein"))));
    }

    @Test
    public void testHighlightWordsIgnoreHTMLTags() {
        assertEquals("<div></div>", MatchesHighlighter.highlightWordsWithHTML("<div></div>", Optional.of(Pattern.compile("div"))));
    }

    @Test(expected = NullPointerException.class)
    public void testNullText() {
        MatchesHighlighter.highlightWordsWithHTML(null, Optional.empty());
    }

    @Test(expected = NullPointerException.class)
    public void testNullList() {
        MatchesHighlighter.highlightWordsWithHTML("", null);
    }

    @Test
    public void testNoWords() {
        assertEquals(Optional.empty(), SearchQueryHighlightObservable.getPatternForWords(Collections.emptyList(), true, true));
    }

    @Test
    public void testPatternCaseInsensitive() {
        Predicate<String> predicate = SearchQueryHighlightObservable.getPatternForWords(Collections.singletonList("abc"), true, false).get().asPredicate();
        assertTrue(predicate.test("abc"));
        assertTrue(predicate.test("ABC"));
        assertFalse(predicate.test("abd"));
        assertFalse(predicate.test("ab"));
    }

    @Test
    public void testPatternCaseSensitive() {
        Predicate<String> predicate = SearchQueryHighlightObservable.getPatternForWords(Collections.singletonList("abc"), true, true).get().asPredicate();
        assertTrue(predicate.test("abc"));
        assertFalse(predicate.test("ABC"));
        assertFalse(predicate.test("abd"));
        assertFalse(predicate.test("ab"));
    }
}