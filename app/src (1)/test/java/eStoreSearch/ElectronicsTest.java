package eStoreSearch;
import org.junit.Test;
import static org.junit.Assert.*;

public class ElectronicsTest {

    @Test public void testSetProductID() {
        Electronics electronic = new Electronics();
        try {
            electronic.setProductID(999999);
            assertTrue(electronic.getProductID() == 999999);
        } catch (Exception e) {
            fail();
        }
    }

    @Test public void testSetDesription() {
        Electronics electronic = new Electronics();
        try {
            electronic.setDescription("test description");
            assertTrue(electronic.getDescription() == "test description");
        } catch (Exception e) {
            fail();
        }
    }

    @Test public void testSetPrice() {
        Electronics electronic = new Electronics();
        try {
            electronic.setPrice(999.99);
            assertTrue(electronic.getPrice() == 999.99);
        } catch (Exception e) {
            fail();
        }
    }

    @Test public void testSetYear() {
        Electronics electronic = new Electronics();
        try {
            electronic.setYear(9998);
            assertTrue(electronic.getYear() == 9998);
        } catch (Exception e) {
            fail();
        }
    }

    @Test public void testSetAuthor() {
        Electronics electronic = new Electronics();
        try {
            electronic.setMaker("Maker");
            assertTrue(electronic.getMaker() == "Maker");
        } catch (Exception e) {
            fail();
        }
    }
    @Test public void testSettersAndGetters() {
        Electronics electronics = new Electronics();
        try {
            electronics.setProductID(999999);
            electronics.setDescription("test description");
            electronics.setPrice(999.99);
            electronics.setYear(9999);
            electronics.setMaker("Author Author");
            assertTrue(electronics.getProductID() == 999999);
            assertTrue(electronics.getDescription() == "test description");
            assertTrue(electronics.getPrice() == 999.99);
            assertTrue(electronics.getYear() == 9999);
            assertTrue(electronics.getMaker() == "Author Author");
        } catch (Exception e) {
            fail();
        }
    }
    @Test public void testSetElectronics() {
        Electronics electronics = new Electronics(999999, "test description", 999.99, 9999, "Author Author");
        assertTrue(electronics.getProductID() == 999999);
        assertTrue(electronics.getDescription() == "test description");
        assertTrue(electronics.getPrice() == 999.99);
        assertTrue(electronics.getYear() == 9999);
        assertTrue(electronics.getMaker() == "Author Author");
    }

    @Test public void testEqualsElectronics() {
        Electronics electronics1 = new Electronics(999999, "test description", 999.99, 9999, "Maker");
        Electronics electronics2 = new Electronics(999999, "test description", 999.99, 9999, "Maker");
        assertTrue(electronics1.equals(electronics2));
    }

    @Test public void testEqualsProductID() {
        Electronics electronics1 = new Electronics(999999, "somthing", 888.88, 8888, "test");
        Electronics electronics2 = new Electronics(999999, "test description", 999.99, 9999, "Author Author");
        assertTrue(electronics1.equalsProductID(electronics2));
    }

    @Test public void testToString() {
        Electronics electronics1 = new Electronics(000001, "description", 10.99, 1000, "maker");
        Electronics electronics2 = new Electronics(000001, "description", 10.99, 1000, "maker");
        Electronics electronics3 = new Electronics(999999, "test description", 999.99, 9999, "somebody");
        assertTrue(electronics1.toString().equals(electronics2.toString()));
        assertFalse(electronics1.toString().equals(electronics3.toString()));
    }
    
}
