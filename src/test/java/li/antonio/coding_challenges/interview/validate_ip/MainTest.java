package li.antonio.coding_challenges.interview.validate_ip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    public void testAddress() {
        assertFalse(Main.isIP("1"));
        assertTrue(Main.isIP("12.3.4.5"));
        assertFalse(Main.isIP("1.23.5.6.7"));
        assertFalse(Main.isIP("abc.1.2.3"));
    }

    @Test
    public void testMakeAddress() {
        assertTrue(Main.canBecomeIP("172001"));
        assertTrue(Main.canBecomeIP("87878729"));
        assertFalse(Main.canBecomeIP("889889889"));
        assertFalse(Main.canBecomeIP("283123a8"));
    }
}
