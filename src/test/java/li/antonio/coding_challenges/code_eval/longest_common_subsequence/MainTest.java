package li.antonio.coding_challenges.code_eval.longest_common_subsequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void testLcs() {
        assertEquals("BCBA", Main.lcs("ABCBDAB", "BDCABA"));
        assertEquals("BDAB", Main.lcs("BDCABA", "ABCBDAB"));
        assertEquals("MJAU", Main.lcs("XMJYAUZ", "MZJAWXU"));
        assertEquals("MJAU", Main.lcs("MZJAWXU", "XMJYAUZ"));
    }
}
