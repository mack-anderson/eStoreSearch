package eStoreSearch;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserInput {
    private Scanner scannerObject = new Scanner(System.in);    
    private EStoreSearch eStore = new EStoreSearch();
    private String file;

    /**
     * UserInput constructor
     */
    public UserInput(){
        this.file = "";
    }

    /**
     * Store value of last file name used in program
     * @param file String of last file used in program
     */
    public void setFile(String file){
        this.file = file;
    }

    /**
     * Set eStore
     * @param eStore eStore Object
     */
    public void setEStore(EStoreSearch eStore){
        this.eStore = eStore;
    }

    /**
     * 
     * @return last used file name in program
     */
    public String getFile(){
        return file;
    }

    /**
     * 
     * @return the eStore in UserInput class
     */
    public EStoreSearch getEStore(){
        return eStore;
    }

    /**Input File products into program
     * 
     * @param file String of file to input
     * @return True if input is successful, False otherwise
     */
    public boolean fileInput(String file){
        Integer check = 1;
        String productID = ""; String description = ""; String price = ""; String year = ""; String authors = ""; String publisher = ""; String maker = "";
        String temp = "";
        String[] wordSplit;
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            check = 0;
            return false;
        }
        if (check == 1) {
            setFile(file);
            if (!inputStream.hasNextLine()) {
            }
            else{
                temp = inputStream.nextLine();
            }
            while (inputStream.hasNextLine()) {
                productID = ""; description = ""; price = ""; year = ""; authors = ""; publisher = ""; maker = "";
                wordSplit = temp.split(" = ");
                if (wordSplit[0].contains("type") && wordSplit[1].contains("book")) {
                    wordSplit = "".split(" ");
                    while (!wordSplit[0].contains("type") && inputStream.hasNextLine()) {
                        temp = inputStream.nextLine();
                        wordSplit = temp.split(" = ");
                        if (wordSplit[0].contains("productID")) {
                            productID = wordSplit[1].replace("\"", "");
                        }
                        else if (wordSplit[0].contains("description")) {
                            description = wordSplit[1].replace("\"", "");
                        }
                        else if (wordSplit[0].contains("price")) {
                            price = wordSplit[1].replace("\"", "");
                        }
                        else if (wordSplit[0].contains("year")) {
                            year = wordSplit[1].replace("\"", "");
                        }
                        else if (wordSplit[0].contains("authors")) {
                            authors = wordSplit[1].replace("\"", "");
                        }
                        else if (wordSplit[0].contains("publisher")) {
                            publisher = wordSplit[1].replace("\"", "");
                        }
                    }
                    try {
                        eStore.addBook(productID, description, price, year, authors, publisher);
                    } catch (Exception e) {
                        return false;
                    }
                }
                else if (wordSplit[0].contains("type") && wordSplit[1].contains("electronics")) {
                    wordSplit = "".split(" ");
                    while (!wordSplit[0].contains("type") && inputStream.hasNextLine()) {
                        temp = inputStream.nextLine();
                        wordSplit = temp.split(" = ");
                        if (wordSplit[0].contains("productID")) {
                            productID = wordSplit[1].replace("\"", "");
                        }
                        else if (wordSplit[0].contains("description")) {
                            description = wordSplit[1].replace("\"", "");
                        }
                        else if (wordSplit[0].contains("price")) {
                            price = wordSplit[1].replace("\"", "");
                        }
                        else if (wordSplit[0].contains("year")) {
                            year = wordSplit[1].replace("\"", "");
                        }
                        else if (wordSplit[0].contains("maker")) {
                            maker = wordSplit[1].replace("\"", "");
                        }
                    }
                    try {
                        eStore.addElectronics(productID, description, price, year,maker);
                    } catch (Exception e) {
                        return false;
                    }
                }
            }
            inputStream.close();
        }
        return true;
    }

    /**Output products from program into a text file
     * 
     * @param file String name of file to output to
     * @return True if file write is succesful, False otherwise
     */
    public boolean fileOutput(String filestring){
        Integer check = 1;
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream(filestring));
        } catch (FileNotFoundException e) {
            check = 0;
            return false;
        }
        if (check == 1) {
            setFile(filestring);
            ArrayList <String> output = new ArrayList <String>();
            output = eStore.toStringOutputStrings();
            for (int i = 0; i < output.size(); i++) {
                outputStream.println(output.get(i));
            }
            outputStream.close();
        }
        return true;
    }

    /**Ask user for what file they would like to output to and try to output to said file. Upon failiure, user will again be prompted to enter a new name for file output
     * 
     * @return Returns 1 if successful
     */
    public int inputFileOutput(){
        Integer ret = 1;
        String temp;
        String[] wordSplit;
        System.out.println("What file would you like to save to?");
        temp = scannerObject.nextLine(); 
        wordSplit = temp.split(" ");
        if (!fileOutput(wordSplit[0])) {
            inputFileOutput();
        }
        return ret;
    }

    /**Output products to last used file
     * 
     * @return True if successful, False otherwise
     */
    public boolean output(){
        if (!fileOutput(getFile())) {
            return false;
        }
        return true;
    }

    /**
     * Ask user for what file they would like to input to and try to output to said file. Upon failiure, user will again be prompted to enter a new name for file input
     * 
     * @return Returns 1 if successful
     */
    public int inputFileInput(){
        String options = "What file would you like to load?"; //options for user
        String temp;
        Integer ret = 1;
        System.out.println(options);
        temp = scannerObject.nextLine();
        String[] wordSplit = temp.split(" ");
        if (!fileInput(wordSplit[0])) {
            inputFileInput();
        }
        return ret;
    }

    /**
     * Asks user for input on whether they want to add, search, or quit
     * @return returns 0 if user doesn't select, 1 if user selects search, 2 if user selects add and 3 if user selects quit
     */
    public int userInput(){
        String options = "Please select an option: \n(1)Add \n(2)Search \n(3)Load File \n(4)Save to File \n(5)Open GUI \n(6)Quit(Saves data to last used file)"; //options for user
        String temp;
        System.out.println(options);
        temp = scannerObject.nextLine(); // ask user for input
        if (temp.toLowerCase().equals("search") || temp.toLowerCase().equals("s") || temp.toLowerCase().equals("2")) { //look for search input
            return 1;
        }
        else if (temp.toLowerCase().equals("add") || temp.toLowerCase().equals("a")|| temp.toLowerCase().equals("1")) { //look for add user input
            return 2;
        }
        else if (temp.toLowerCase().equals("load") || temp.toLowerCase().equals("l")|| temp.toLowerCase().equals("3")) { //look for add user input
            return 3;
        }
        else if (temp.toLowerCase().equals("save") || temp.toLowerCase().equals("4")) { //look for add user input
            return 4;
        }
        else if (temp.toLowerCase().equals("gui") || temp.toLowerCase().equals("g")|| temp.toLowerCase().equals("5")) { //look for quit user input
            return 5;
        }
        else if (temp.toLowerCase().equals("quit") || temp.toLowerCase().equals("q")|| temp.toLowerCase().equals("6")) { //look for quit user input
            return 6;
        }
        return 0; //improper input
        
    }

    /**
     * Ask user what type of product to add, Book or Electronic
     * 
     * @return returns 0 if no user input, 1 if user selects Book, and 2 if user selects Electronic
     */
    public int typeInput(){
        String options = "(1)Book or (2)Electronic?"; //options for user
        String temp;
        System.out.println(options);
        temp = scannerObject.nextLine();
        if (temp.toLowerCase().equals("book") || temp.toLowerCase().equals("b") || temp.toLowerCase().equals("1")) { //compare user input for choice of book
            return 1;
        }
        else if (temp.toLowerCase().equals("electronic") || temp.toLowerCase().equals("e") || temp.toLowerCase().equals("2")) { //compare user input for choice of electronic
            return 2;
        }
        return 0;
        
    }
    
    /**
     * Ask user for input on what information the book should have and add book to list
     * 
     */
    public void bookInput(){
        String productID, description, price, author, publisher, year;
        System.out.println("Enter ProductID (required):"); 
        productID = scannerObject.nextLine(); //user input
        System.out.println("Enter Description (required):");
        description = scannerObject.nextLine(); //user input
        System.out.println("Enter Year (required):");
        year = scannerObject.nextLine(); //user input
        System.out.println("Enter Price:");
        price = scannerObject.nextLine(); //user input
        System.out.println("Enter Author:");
        author = scannerObject.nextLine(); //user input
        System.out.println("Enter Publisher:");
        publisher = scannerObject.nextLine(); //user input

        try{ //check if book can be added from user input
            eStore.addBook(productID, description, price, year, author, publisher);
        }catch(FormatException e){
            System.out.println(e.printMessage());
            bookInput();
        }
    }

    /**
     * Ask user for input on what information the electronic should have and add electronic to list
     * 
     */
    public void electronicsInput(){
        String productID, description, price, year, maker;
        System.out.println("Enter ProductID (required):");
        productID = scannerObject.nextLine(); //user input
        System.out.println("Enter Description (required):");
        description = scannerObject.nextLine(); //user input
        System.out.println("Enter Year (required):");
        year = scannerObject.nextLine(); //user input
        System.out.println("Enter Price:");
        price = scannerObject.nextLine(); //user input
        System.out.println("Enter maker:");
        maker = scannerObject.nextLine(); //user input

        try{ //check if electronic can be added from user input
            eStore.addElectronics(productID, description, price, year, maker);
        }catch(FormatException e){
            System.out.println(e.printMessage());
            electronicsInput();
        }
    }

    /**
     * Splits year range given by user into proper format for year search method
     * @param string string containing user provided range for year search
     * @return returns array with parameters of year range array[0] = y1, array[1] = y2, array[2] = 0 for y1-y2 and y1, 1 for 0-y1, 2 for y1-999
     * @throws FormatException check for proper user input
     */
    public int[] inputYear(String string) throws FormatException{
        int toOrFrom = 0; int y1 = 0; int y2 = 0;
        int array[] = new int[3]; //return array
        ValueCheck check = new ValueCheck();
        if (string.length() != 4 && string.length() != 5 && string.length() != 9) { //check if string is right size
            throw new FormatException(2);
        }
        if (string.length() == 5 && string.charAt(0) == '-') { //check if range is 0-y1
            toOrFrom = 1;
        }
        else if (string.length() == 5 && string.charAt(4) == '-') { //check if range is y1-9999
            toOrFrom = 2;
        }

        String years[] = string.split("-");
        
        if (years.length < 1 || years.length > 2) { //check if split method was successful
            throw new FormatException(2);
        }
        if(years.length==2){
            if (check.isInt(years[0]) && check.isInt(years[1])) { //check if values are numbers
                if (check.isInt(years[1]) && check.isInt(years[0])) {
                    y1 = Integer.parseInt(years[0]);
                    y2 = Integer.parseInt(years[1]);
                }
                if (!(y1 >= 1000 && y1 <= 9999) && !(y2 >= 1000 && y2 <= 9999)) { //check range of years
                    throw new FormatException(2);
                }

            }
            else if (toOrFrom == 1) {
                if (check.isInt(years[1])) { //check if value is a number
                    y1 = Integer.parseInt(years[1]);
                }
            }
        }
        else if (check.isInt(years[0])) {
            if (check.isInt(years[0])) {
                y1 = Integer.parseInt(years[0]);
            }
            if (!(y1 >= 1000 && y1 <= 9999)) { //check for range of year
                throw new FormatException(2);
            }
        }
        else{
            throw new FormatException(2);
        }
        array[0]=y1;//set options in return array
        array[1]=y2;
        array[2]=toOrFrom;
        return array;
    }

    /**
     * Gets input from user on what search parameters they want then prints matches to System.out
     */
    public void searchInput(){
        String productID, description, year;
        System.out.println("Enter ProductID:"); //user input
        productID = scannerObject.nextLine();
        System.out.println("Enter Description:");
        description = scannerObject.nextLine();
        System.out.println("Enter time period:");
        year = scannerObject.nextLine();

        try{ //try to search given user parameters
            eStore.search(productID, description, year);
            eStore.printMatches(); //print the found matches
        }catch(FormatException e){
            System.out.println(e.printMessage());
            searchInput();
        }
    }


}
