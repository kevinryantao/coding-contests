package crackingcodinginterview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Chapter1ArraysAndStringsTest {
    @org.junit.Test
    public void testAreAllCharsUnique() throws Exception {
        assertTrue(Chapter1ArraysAndStrings.areAllCharsUnique("ABC"));
        assertFalse(Chapter1ArraysAndStrings.areAllCharsUnique("??ABC"));
        assertFalse(Chapter1ArraysAndStrings.areAllCharsUnique("ABC12345678901"));
    }

    @org.junit.Test
    public void testRemoveDuplicates() throws Exception {
        assertEquals("ABC", Chapter1ArraysAndStrings.removeDuplicates("ABC"));
        assertEquals("ABC", Chapter1ArraysAndStrings.removeDuplicates("ABCABCABC"));
        assertEquals("ABC", Chapter1ArraysAndStrings.removeDuplicates("ABABCABC"));
        assertEquals("A", Chapter1ArraysAndStrings.removeDuplicates("AAAA"));
    }


}
