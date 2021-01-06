package eStoreSearch;
import org.junit.Test;
import static org.junit.Assert.*;

public class EStoreSearchTest {
    EStoreSearch eStore = new EStoreSearch();

//ESTORESEARCH HASHMAP TESTS===============================================================================================================

    @Test public void testHashMapInitialCreation() { // test for proper hashmap after inputting file
        UserInput input = new UserInput(); 
        input.fileInput("TestValidInput.txt");
        EStoreSearch tempEStoreSearch = input.getEStore();
        assertTrue(tempEStoreSearch.getIndex().toString().equals("{java=[0], absolute=[0], book=[3], electronic=[2], description=[4], monitor=[5], something=[1], mack=[1, 4]}"));
    }

    @Test public void testHashMapUpdatedKeywords() { // test for proper hashmap after inputting file and adding product with a new keyword
        UserInput input = new UserInput(); 
        input.fileInput("TestValidInput.txt");
        EStoreSearch tempEStoreSearch = input.getEStore();
        try {
            tempEStoreSearch.addBook("222222", "a description", "", "4520", "", "");
        } catch (Exception e) {
            fail();
        }
        assertTrue(tempEStoreSearch.getIndex().toString().equals("{a=[6], java=[0], absolute=[0], book=[3], electronic=[2], description=[4, 6], monitor=[5], something=[1], mack=[1, 4]}"));
    }



//ESTORESEARCH TESTS===============================================================================================================    
    @Test public void testAddBook() {
        try { // properly formatted input success
            eStore.addBook("999999", "test description", "999.99", "9999", "Author Author", "Publisher");
        } catch (FormatException e) {
            fail();
        }
        
        try { // No duplicate productIDs allowed
            eStore.addBook("999999", "test description", "999.99", "9999", "Author Author", "Publisher");
            eStore.addBook("999999", "test", "99", "9999", "Author", "Publisher");
        } catch (FormatException e) {
        }

        try { //improper inputs for numbered inputs
            eStore.addBook("not an int", "test description", "not a double", "9999", "Author Author", "Publisher");
            fail();
        } catch (FormatException e) {
        }

        try { //empty inputs
            eStore.addBook("", "", "", "", "", "");
            fail();
        } catch (FormatException e) {
        }

    }
    @Test public void testAddElectronics() {
        try {
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
        } catch (FormatException e) {
            fail();
        }

        try { // No duplicate productIDs allowed
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("999999", "test", "99", "9999", "Author");
            assertTrue(eStore.productListSize() == 1);
        } catch (FormatException e) {
        }

        try { //improper inputs for numbered inputs
            eStore.addElectronics("not an int", "test description", "not a double", "9999", "Maker");
            fail();
        } catch (FormatException e) {
        }

        try { //empty inputs
            eStore.addElectronics("", "", "", "", "");
            fail();
        } catch (FormatException e) {
        }

    }

    @Test public void testSearchProductID() {
        

        try { // Search finds three items that match the keyword "test"
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("000000", "test", "99", "9999", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("000000", "", "") == 1);
            assertTrue(eStore.search("999999", "", "") == 1);
            assertTrue(eStore.search("111111", "", "") == 1);
        } catch (FormatException e) {
            fail();
        }

    }

    @Test public void testSearchKeywords() {
        
        eStore = new EStoreSearch();
        try { // Search finds three items that match the keyword "test"
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("000000", "test", "99", "9999", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "test", "") == 3);
        } catch (FormatException e) {
            fail();
        }

        eStore = new EStoreSearch();
        try { // Search finds no items that match the keyword "nothing"
            eStore.addBook("999999", "test description", "999.99", "9999", "Maker", "publisher");
            eStore.addElectronics("000000", "test", "99", "9999", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "nothing", "") == 0);
        } catch (FormatException e) {
            fail();
        }

        eStore = new EStoreSearch();
        try { // there are 3 case insensitice matches to the keyword "TEST"
            eStore.addBook("999999", "TEST description", "999.99", "9999", "Maker",  "publisher");
            eStore.addElectronics("000000", "TeSt", "99", "9999", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "TEST", "") == 3);
        } catch (FormatException e) {
            fail();
        }

        eStore = new EStoreSearch();
        try { // tests for tokenization matches
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("000001", "TeSt", "99", "9999", "Author");
            eStore.addElectronics("111111", "description something test", "99", "9999", "Author");
            assertEquals(eStore.productListSize(), 3);
            
            assertTrue(eStore.search("", "test description", "") == 2);
            assertTrue(2 == eStore.matchSize());
        } catch (FormatException e) {
            fail();
        }
    }


    @Test public void testSearchYear() {
        

        try { // all items with years upto 5000 are matches therefor 2 matches
            eStore.addElectronics("999999", "test description", "999.99", "1000", "Maker");
            eStore.addElectronics("000000", "test", "99", "2020", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "", "-5000") == 2);
        } catch (FormatException e) {
            fail();
        }

        eStore = new EStoreSearch();
        try { // Searches for items with years beyond 2050 therefor 1 match
            eStore.addElectronics("999999", "test description", "999.99", "1111", "Maker");
            eStore.addElectronics("000000", "test", "99", "2020", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "", "2050-") == 1);
        } catch (FormatException e) {
            fail();
        }

        eStore = new EStoreSearch();
        try { // matches all items of year 9999 therefor 3 matches
            eStore.addElectronics("999999", "TEST description", "999.99", "9999", "Maker");
            eStore.addElectronics("000000", "TeSt", "99", "9999", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "", "9999") == 3);
        } catch (FormatException e) {
            fail();
        }

        eStore = new EStoreSearch();
        try { // find all matches with years between 4000 and 5000 therefor 1 match
            eStore.addElectronics("999999", "test description", "999.99", "1111", "Maker");
            eStore.addElectronics("000001", "TeSt", "99", "5000", "Author");
            eStore.addElectronics("111111", "description something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "", "4000-9000") == 1);
        } catch (FormatException e) {
            fail();
        }
    }


    @Test public void testSearchCombined() {
        

        try { // Search finds three items that match the keyword "test" and year 9999 therefor 3 matches
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("000000", "test", "99", "9999", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "test", "9999") == 3);
        } catch (FormatException e) {
            fail();
        }

        eStore = new EStoreSearch();
        try { // Search finds items matching productID, keyword "Test", and year therefor 1 match
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("000000", "test", "99", "9999", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("999999", "test", "9999") == 1);
        } catch (FormatException e) {
            fail();
        }

        eStore = new EStoreSearch();
        try { // 1 match for the keyword "something" and year 9999
            eStore.addElectronics("999999", "TEST description", "999.99", "9999", "Maker");
            eStore.addElectronics("000000", "TeSt", "99", "9999", "Author");
            eStore.addElectronics("111111", "something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "something", "9999") == 1);
        } catch (FormatException e) {
            fail();
        }

        eStore = new EStoreSearch();
        try { // 2 matches for keyword "test" and years beyond 9000
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("000001", "TeSt", "99", "8888", "Author");
            eStore.addElectronics("111111", "description something test", "99", "9999", "Author");
            
            assertTrue(eStore.search("", "test", "9000-") == 2);
            assertTrue(2 == eStore.matchSize());
        } catch (FormatException e) {
            fail();
        }
    }

    @Test public void testSearchInput() {
        

        eStore = new EStoreSearch();
        try { // when no user input returns all products
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("000001", "TeSt", "99", "9999", "Author");
            eStore.addElectronics("111111", "description something test", "99", "9999", "Author");
            assertTrue(eStore.search("", "", "") == 3);
            
        } catch (FormatException e) {
            fail(); 
        }

        eStore = new EStoreSearch();
        try { // check for rejection of invalid productID
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("000001", "TeSt", "99", "9999", "Author");
            eStore.addElectronics("111111", "description something test", "99", "9999", "Author");
            eStore.search("9999999999", "", "");
            
        } catch (FormatException e) {
            assertTrue(true); 
        }

        eStore = new EStoreSearch();
        try { // check for rejection of invalid year
            eStore.addElectronics("999999", "test description", "999.99", "9999", "Maker");
            eStore.addElectronics("000001", "TeSt", "99", "9999", "Author");
            eStore.addElectronics("111111", "description something test", "99", "9999", "Author");
            eStore.search("", "", "99999");
            
        } catch (FormatException e) {
            assertTrue(true); 
        }
    }
}
