package li.antonio.code_eval.find_min;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.codeeval.com/browse/85/
 */
public class MainTest {

    @Test
    public void find_min() {
        assertEquals(26, Main.find_min(78, 51, 3, 5, 5, 51230));
        assertEquals(34, Main.find_min(186, 75, 68, 16, 539, 312));
        assertEquals(1, Main.find_min(137, 135, 48, 17, 461, 512));
        assertEquals(6, Main.find_min(98, 22, 6, 30, 524, 100));
        assertEquals(12, Main.find_min(46, 18, 7, 11, 9, 46));
    }

    @Test
    public void minimumNotContained() {
        assertEquals(0, Main.minimumNotContained(new int[] { 1, 2 }));
        assertEquals(2, Main.minimumNotContained(new int[] { 0, 1 }));
        assertEquals(1, Main.minimumNotContained(new int[] { 0, 2 }));
    }

    @Test
    public void left_shift() {
        final int[] m = { 0, 1, 2 };
        assertEquals(0, Main.left_shift(m, 3));
        assertArrayEquals(new int[] { 1, 2, 3 }, m);
    }

    @Test
    public void replace() {
        { // replace with self
            final int[] m = { 0, 1, 2 };
            Main.replace(m, 1, 1);
            assertArrayEquals(new int[] { 0, 1, 2 }, m);
        }
        { // replace at the start
            final int[] m = { 1, 2, 3 };
            Main.replace(m, 1, 0);
            assertArrayEquals(new int[] { 0, 2, 3 }, m);
        }
        { // add to the end
            final int[] m = { 0, 1, 2 };
            Main.replace(m, 1, 3);
            assertArrayEquals(new int[] { 0, 2, 3 }, m);
        }
        { // replace at the end
            final int[] m = { 1, 2, 3 };
            Main.replace(m, 3, 4);
            assertArrayEquals(new int[] { 1, 2, 4 }, m);
        }
        { // swap in location
            final int[] m = { 0, 2, 3 };
            Main.replace(m, 2, 1);
            assertArrayEquals(new int[] { 0, 1, 3 }, m);
        }
        {
            final int[] m = { 0, 2, 3, 4, 5 };
            Main.replace(m, 4, 1);
            assertArrayEquals(new int[] { 0, 1, 2, 3, 5 }, m);
        }
        {
            final int[] m = { 0, 1, 2, 3, 5 };
            Main.replace(m, 1, 4);
            assertArrayEquals(new int[] { 0, 2, 3, 4, 5 }, m);
        }
    }
}
