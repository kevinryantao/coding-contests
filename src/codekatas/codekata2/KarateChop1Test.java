package codekatas.codekata2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KarateChop1Test {
    @Test
    public void testChop() throws Exception {
        assertEquals(-1, KarateChop1.chop(3, new int[]{}));
        assertEquals(-1, KarateChop1.chop(3, new int[]{1}));
        assertEquals(0,  KarateChop1.chop(1, new int[]{1}));
        assertEquals(0,  KarateChop1.chop(1, new int[]{1, 3, 5}));
        assertEquals(1,  KarateChop1.chop(3, new int[]{1, 3, 5}));
        assertEquals(2,  KarateChop1.chop(5, new int[]{1, 3, 5}));
        assertEquals(-1, KarateChop1.chop(0, new int[]{1, 3, 5}));
        assertEquals(-1, KarateChop1.chop(2, new int[]{1, 3, 5}));
        assertEquals(-1, KarateChop1.chop(4, new int[]{1, 3, 5}));
        assertEquals(-1, KarateChop1.chop(6, new int[]{1, 3, 5}));
        assertEquals(0,  KarateChop1.chop(1, new int[]{1, 3, 5, 7}));
        assertEquals(1,  KarateChop1.chop(3, new int[]{1, 3, 5, 7}));
        assertEquals(2,  KarateChop1.chop(5, new int[]{1, 3, 5, 7}));
        assertEquals(3,  KarateChop1.chop(7, new int[]{1, 3, 5, 7}));
        assertEquals(-1, KarateChop1.chop(0, new int[]{1, 3, 5, 7}));
        assertEquals(-1, KarateChop1.chop(2, new int[]{1, 3, 5, 7}));
        assertEquals(-1, KarateChop1.chop(4, new int[]{1, 3, 5, 7}));
        assertEquals(-1, KarateChop1.chop(6, new int[]{1, 3, 5, 7}));
        assertEquals(-1, KarateChop1.chop(8, new int[]{1, 3, 5, 7}));
    }
}
