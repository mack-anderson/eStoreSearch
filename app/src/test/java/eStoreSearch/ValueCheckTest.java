package eStoreSearch;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValueCheckTest {
    @Test public void testIsInt() {
        ValueCheck check = new ValueCheck();
        assertEquals(true, check.isInt("999999"));
        assertEquals(false, check.isInt("mack"));
        assertEquals(false, check.isInt("0.0001"));
    }

    @Test public void testIsDouble() {
        ValueCheck check = new ValueCheck();
        assertEquals(true, check.isDouble("999999"));
        assertEquals(true, check.isDouble("999999.99"));
        assertEquals(false, check.isDouble("mack"));
    }
}
