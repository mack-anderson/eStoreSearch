package eStoreSearch;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    @Test public void testSetProductID() {
        Book book = new Book();
        try {
            book.setProductID(999999);
            assertTrue(book.getProductID() == 999999);
        } catch (Exception e) {
            fail();
        }
        
    }

    @Test public void testSetDesription() {
        Book book = new Book();
        try {
            book.setDescription("test description");
            assertTrue(book.getDescription() == "test description");
        } catch (Exception e) {
            fail();
        }

    }

    @Test public void testSetPrice() {
        Book book = new Book();
        try {
            book.setPrice(999.99);
            assertTrue(book.getPrice() == 999.99);
        } catch (Exception e) {
            fail();
        }
    }

    @Test public void testSetYear() {
        Book book = new Book();
        try {
            book.setYear(9999);
            assertTrue(book.getYear() == 9999);
        } catch (Exception e) {
            fail();
        }
    }

    @Test public void testSetAuthor() {
        Book book = new Book();
        try {
            book.setAuthor("Author Author");
            assertTrue(book.getAuthor() == "Author Author");
        } catch (Exception e) {
            fail();
        }
    }

    @Test public void testSetPublisher() {
        Book book = new Book();
        try {
            book.setPublisher("Publisher Publisher");
            assertTrue(book.getPublisher() == "Publisher Publisher");
        } catch (Exception e) {
            fail();
        }
    }

    @Test public void testSetBook() {
        Book book = new Book(999999, "test description", 999.99, 9999, "Author Author", "Publisher Publisher");
        assertTrue(book.getProductID() == 999999);
        assertTrue(book.getDescription() == "test description");
        assertTrue(book.getPrice() == 999.99);
        assertTrue(book.getYear() == 9999);
        assertTrue(book.getAuthor() == "Author Author");
        assertTrue(book.getPublisher() == "Publisher Publisher");
    }

    @Test public void testEqualsBook() {
        Book book1 = new Book(999999, "test description", 999.99, 9999, "Author Author", "Publisher Publisher");
        Book book2 = new Book(999999, "test description", 999.99, 9999, "Author Author", "Publisher Publisher");
        assertTrue(book1.equals(book2));
        Book book3 = new Book(999999, "test description", 999.99, 9999, "Author Author", "Publisher Publisher");
        Book book4 = new Book(999999, "test description", 999.99, 9999, "Publisher Publisher", "Author Author");
        assertFalse(book3.equals(book4));
    }

    @Test public void testEqualsProductID() {
        Book book1 = new Book(999999, "somthing", 888.88, 8888, "test", "test");
        Book book2 = new Book(999999, "test description", 999.99, 9999, "Author Author", "Publisher Publisher");
        assertTrue(book1.equalsProductID(book2));
    }

    @Test public void testToString() {
        Book book1 = new Book(000001, "description", 10.99, 1000, "author", "publisher");
        Book book2 = new Book(000001, "description", 10.99, 1000, "author", "publisher");
        Book book3 = new Book(999999, "test description", 999.99, 9999, "Author Author", "Publisher Publisher");
        assertTrue(book1.toString().equals(book2.toString()));
        assertFalse(book1.toString().equals(book3.toString()));
    }
    
} 

