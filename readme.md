##### ASSIGNMENT 3 README
* Name: Mack Anderson
* Student ID: 1099411
* Email: mander39@uoguelph.ca

#### Functionality 
The project eStoreSearch allows products of type Book and Electronic to be added to a database of products. Both types of products have a productID, description, and year. User can also perform searches on the database. Users can search for a productID, description, year, or a mix of these values. If a file is entered on the command line initially, the products of said file will be loaded into the program. All of these functions can be performed through a user interface which is described below.

#### Usage
1. to run program enter $gradle run or $gradle run --args="myFile.txt"

2. in the case of command line argument, entered file will be loaded otherwise this step will be skipped

3. Program will then open up to the start menu GUI with commands in the top left corner for the options described below.

    ## Start
        #If a user selects the start option the program will return to the start menu.

    ### Add Menu (User can select Book or Electronics from the drop down menu)
        # Book: User will be provided with text fields to provide information for each field of Book [productID(required), description(required), year(required), price, author, and publisher]
        # Electronic: User will be provided with text fields to provide information for each field of Electronic [productID(required), description(required), year(required), price, maker]

        ## Add Button: once information is entered for book or electronic, the add button can be clicked to attempt to add the desired product. If product can't be added an error message will be printed.

        ## Reset Button: when the user clicks the reset button, all text fields will be cleared.

    ### Search
        # user will be provided with text fields for productID, keywords, start year, and end year

        ## Search Button: when the user clicks the search button, products will be searched from the provided fields. Matching products will be returned to the output box.

        ## Reset Button: when the user clicks the reset button, all text fields will be cleared.

    ## File
        # User is provided with text fields for input or output file

        ## Reset Button: when the user clicks the reset button, all text fields will be cleared.

        ## Import File Button: when the user clicks the import file button, filename in file input textfield will attempt to be loaded. If unsuccessful, an error message will be printed to message box.

        ## Export File Button: when the user clicks the export file button, filename in file to save to input textfield will attempt to be saved to. If unsuccessful, an error message will be printed to message box.

    ## Quit
        #If a user selects the quit option the program will exit.

#### Testing Plan

### GUI classes (StartGUI, AddGUI, SearchGUI, FileGUI)

1. GUI elements are not able to be tested by JUnit tests so all backend functionality is tested through JUnit while interface will be tested manually.

2. Each of the test cases below will be tested through the GUI when applicable to ensure functionality is as expected based on backend JUnit tests

3. Test Reset button functionality in add, search, and file by filling text fields and clicking reset button to ensure all text fields are properly cleared

4. Test add functionality by entering various info from junit tests for book and electronics then printing all products to check for correctness

5. Test file input and output button functionality by attempting to add a file then checking if all products were imported correctly

### Product

1. Setters and Getters: Testing is done in the Book and Electronics text classes as Product is an abstract class

### Book

1. Testing setters and getters for all instance variables by setting their values and using an assertTrue as well as the getter to compare

2. Testing constructor by calling contructor Book with input values then using assertTrue as well as getters to check for success

3. Test equals method by creating two new books with equal values and using assertTrue with equals method to check for success. Test equals method by creating two new books with different values and using assertFalse with equals method to check for failiure

4. Test equalsProductID by creating two books with the same productID and using assertTrue to check for success

5. Test toString by creating 3 books, 2 with matching values and 1 with different values. Use assertTrue to check that toStrings of matching books are equal and assertFalse to check that toStrings of unmatching books are not equal

### Electronics

1. Testing setters and getters for all instance variables by setting their values and using an assertTrue as well as the getter to compare

2. Testing constructor by calling contructor Electronics with input values then using assertTrue as well as getters to check for success

3. Test equals method by creating two new Electronics with equal values and using assertTrue with equals method to check for success. Test equals method by creating two new Electronics with different values and using assertFalse with equals method to check for failiure

4. Test equalsProductID by creating two Electronics with the same productID and using assertTrue to check for success

5. Test toString by creating 3 Electronics, 2 with matching values and 1 with different values. Use assertTrue to check that toStrings of matching Electronics are equal and assertFalse to check that toStrings of unmatching Electronics are not equal

### EStoreSearch

1. Test addBook and addElectronics by creating new Books and Electronics with the same productID and checking that product with duplicate productID does not get added by checking arraylist sizes with with assertTrue to test that duplicate ID was not added

2. Test searchProductID by adding electronics to list and using assertTrue to evaluate if searchProductID returned the correct match

3. Test search keywords functionality by using assertTrue to check if search returns correct number of matches

4. Test search keywords functionality by using assertTrue to check if search returns correct number of matches regardless of case sensitivity

5. Test search keywords functionality by using assertTrue to check if search returns correct number of matches when multiple keywords are provided

6. Test year search functionality by using assertTrue to check if search returns correct number of matches for various year inputs including all year ranges, upto and from years, and single year searches. Use assertTrue to evaluate whether search returns proper number of matches for each case.

7. Test search functionality for multi-input combinations including all three fields provided, ID and description, ID and year, description and keyword, and all fields empty. Use assertTrue to evaluate whether search returns proper number of matches for each case.

8. Test ability of search to reject invalid user input by creating a case for each type of invalid input in try statement then use assertTrue in catch to test for proper error detection.

### EStoreSearch - HashMap Tests

1. Initial HashMap upon file upload: Input a file then check created hashmap with desired hashmap using assertTrue and toString method for HashMap

2. Updated Keywords: Input a file then add a product then check created hashmap with desired hashmap using assertTrue and toString method for HashMap


### UserInput

1. inputYear: Test if inputYear provides correct year condition when given string input using assertTrue. Conditions include upto a year, beyond a year, a single year, and range between two years. 

2. fileInput and fileOutput: 
    * Test fileInput and fileOutput by inputing a file then outputting it. I will then return the string of each file and use assertTrue to confirm that files match. The previous test is also done but with the addition of adding products to the input file and outputting the result. 
    * Check the case of a non existing input file for failiure using method above but with comparison file being nonexistent and using assertFalse 

3. Setters and Getters: Test setters and getters by setting values and then comparing output using getters and assertTrue. 
