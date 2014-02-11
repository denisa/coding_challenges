package li.antonio.code_eval.find_min;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://www.codeeval.com/browse/85/
 */
public class FindMinTest {


    private static int[] fill(final int[] m, final int k) {
        for (int i = k; i < m.length; i++) {

            final Set<Integer> set = new HashSet<>();
            for (int j = i - k; j < i; j++) {
                set.add(m[j]);
            }

            final Integer[] integers = set.toArray(new Integer[set.size()]);
            Arrays.sort(integers);
            m[i] = smallestMissingInt(integers);
        }
        return m;
    }

    private static int smallestMissingInt(final Integer[] integers) {
        if (integers[0] > 0) {
            return 0;
        }
        for (int j = 1; j < integers.length; j++) {
            if (integers[j] - integers[j - 1] > 1) {
                return integers[j - 1] + 1;
            }
        }
        if (integers[integers.length - 1] - integers[integers.length - 2] > 1) {
            return integers[integers.length - 2] + 1;
        }
        return integers[integers.length - 1] + 1;
    }

    private static int print(final int[] m) {
        return m[m.length - 1];
    }

    @Test
    public void find_min() {
        Assert.assertEquals(26, FindMin.find_min(78, 51, 3, 5, 5, 51230));
        Assert.assertEquals(34, FindMin.find_min(186, 75, 68, 16, 539, 312));
        Assert.assertEquals(1, FindMin.find_min(137, 135, 48, 17, 461, 512));
        Assert.assertEquals(6, FindMin.find_min(98, 22, 6, 30, 524, 100));
        Assert.assertEquals(12, FindMin.find_min(46, 18, 7, 11, 9, 46));
    }

    @Test
    public void minimumNotContained() {
        Assert.assertEquals(0, FindMin.minimumNotContained(new int[] { 1, 2 }));
        Assert.assertEquals(2, FindMin.minimumNotContained(new int[] { 0, 1 }));
        Assert.assertEquals(1, FindMin.minimumNotContained(new int[] { 0, 2 }));
    }

    @Test
    public void left_shift() {
        final int[] m = { 0, 1, 2 };
        Assert.assertEquals(0, FindMin.left_shift(m, 3));
        Assert.assertArrayEquals(new int[] { 1, 2, 3 }, m);
    }

    @Test
    public void replace() {
        {
            final int[] m = { 0, 1, 2 };
            FindMin.replace(m, 1, 3);
            Assert.assertArrayEquals(new int[] { 0, 2, 3 }, m);
        }
        {
            final int[] m = { 1, 2, 3 };
            FindMin.replace(m, 1, 0);
            Assert.assertArrayEquals(new int[] { 0, 2, 3 }, m);
        }
        {
            final int[] m = { 0, 2, 3 };
            FindMin.replace(m, 2, 1);
            Assert.assertArrayEquals(new int[] { 0, 1, 3 }, m);
        }
    }
}
