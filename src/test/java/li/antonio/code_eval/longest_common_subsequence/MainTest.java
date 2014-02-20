package li.antonio.code_eval.longest_common_subsequence;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void testLcs() throws Exception {
        assertEquals("BCBA", Main.lcs("ABCBDAB", "BDCABA"));
        assertEquals("BDAB", Main.lcs("BDCABA", "ABCBDAB"));
        assertEquals("MJAU", Main.lcs("XMJYAUZ", "MZJAWXU"));
        assertEquals("MJAU", Main.lcs("MZJAWXU", "XMJYAUZ"));
    }
}
