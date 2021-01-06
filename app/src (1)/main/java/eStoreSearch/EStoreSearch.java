package eStoreSearch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EStoreSearch {
    private ArrayList <Product> productList = new ArrayList<Product> ();
    private ArrayList <Product> matchList = new ArrayList<Product> ();
    HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();

    /**
     * Constructor Method for EStore Search
     * @author Mack Anderson
     */    
    public EStoreSearch(){
    }

    /**
     * Set index
     * @param index hashIndex
     */
    public void setIndex( HashMap<String, ArrayList<Integer>> index){
        this.index = index;
    }

    /**
     * Get index
     * @return hashIndex
     */
    public HashMap<String, ArrayList<Integer>> getIndex(){
        return index;
    }

//PRINT FUNCTIONS-------------------------------------------------------------------------------------------------

    /**
     * Creates hashMap containing the indexes of keywords contained within products and saves hashMap to EStoreSearch Class instance variable index
     */
    public void hashIndex(){
        Product product = productList.get(productList.size()-1); //newest product in list
        String keywords[] = product.getDescription().split(" ");
        ArrayList <Integer> val = new ArrayList <Integer>();
        for (int i = 0; i < keywords.length; i++) { //parse keywords and check if entry already exists in hashmap
            if (index.get(keywords[i].toLowerCase()) != null) { //when hashmap doesnt contain keyword add new entry with accompanying index
                val = new ArrayList <Integer>(index.get(keywords[i].toLowerCase()));
                val.add(productList.size()-1);
                index.put(keywords[i].toLowerCase(), val);
            }
            else{ // keyword already in hashMap so just add index to key value
                val = new ArrayList <Integer>();
                val.add(productList.size()-1);
                index.put(keywords[i].toLowerCase(), val);
            }
        }
        Set <HashMap.Entry<String,ArrayList<Integer>>> entrySet = index.entrySet();
        Iterator<HashMap.Entry<String,ArrayList<Integer>>> it = entrySet.iterator();
        while (it.hasNext()) { // Remove duplicate index entries from hashmap
            Map.Entry<String, ArrayList<Integer>> me = it.next();
            me.setValue(removeDuplicateIntegers(me.getValue()));
        }
    }

    /**Finds intersection of index's of matching keywords
     * 
     * @param description String containing search keywords
     * @return arraylist of int's containing index of matching items
     */
    public ArrayList <Integer> hashIndexIntersection(String description){
        String keywords[] = description.split(" ");
        ArrayList <Integer> temp= new ArrayList <Integer>();
        ArrayList <Integer> val= new ArrayList <Integer>();
        ArrayList <Integer> intersection = new ArrayList <Integer>();
        for (int i = 0; i < keywords.length; i++) { // find intersection of index's of matching keywords
            if (intersection.isEmpty()) { // intersection is empty to make intersection the index of key value
                intersection = index.get(keywords[i].toLowerCase());
            }
            else{ // search for matching index's and only add matches to new arraylist of integers
                temp = index.get(keywords[i].toLowerCase());
                val = new ArrayList <Integer>(intersection);
                intersection = new ArrayList <Integer>();
                for (int j = 0; j < temp.size(); j++) {
                    for (int j2 = 0; j2 < val.size(); j2++) {
                        if (temp.get(j).equals(val.get(j2))) {
                            intersection.add(temp.get(j));
                        }  
                    }

                }
            }
        }
        if (intersection != null) { //remove duplicate index entries
            removeDuplicateIntegers(intersection);
        }
        return intersection;
    }

    /**
     * Removes Duplicate index's from the arraylist
     * @param list Arraylist of integers 
     * @return Arraylist of integers with duplicate integers removed
     */
    public ArrayList <Integer> removeDuplicateIntegers(ArrayList <Integer> list){
        ArrayList <Integer> temp = new ArrayList <Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (!temp.contains(list.get(i))) { //Only add index if not already in arraylist
                temp.add(list.get(i));
            }
        }
        return temp; //new arraylist
    }


//ADD TO LIST-------------------------------------------------------------------------------------------------

    /**
     * Method adds new book to bookList by taking in string inputs
     * 
     * @param productID the productID #
     * @param description description of the product
     * @param price the product price
     * @param year the product year
     * @param author author of the book
     * @param publisher publisher of the book
     * @author Mack Anderson
     */    
    public void addBook(String productID, String description, String price, String year, String author, String publisher ) throws FormatException{
        ValueCheck check = new ValueCheck();
        if (price.isEmpty()) { //check if list is empty and set price to 0 if so
            price = "0";
        }
        if(check.isInt(productID) && check.isInt(year) && check.isDouble(price)){ //check for valid input
            int id = Integer.parseInt(productID);
            int y = Integer.parseInt(year);
            double cost = Double.parseDouble(price);
            if (!(id >= 0 && id <= 999999)) {
                throw new FormatException(1);
            }
            if (!(y >= 1000 && y <= 9999)) {
                throw new FormatException(2);
            }
            try{ //if input is valid add book to book list 
                checkID(id);
                productList.add(new Book(id, description, cost, y, author, publisher));
            }catch(FormatException e){ //if add fails print error message
                throw new FormatException(4);
            }
            hashIndex(); 
        }
        else{
            throw new FormatException(3); //input is invalid
        }
    }
    
    /**
     * Method adds new book to electronics list by taking in string input
     * 
     * @param productID the productID #
     * @param description description of the prouduct
     * @param price the product price
     * @param year the product year
     * @param maker maker of the book
     * @author Mack Anderson
     */  
    public void addElectronics(String productID, String description, String price, String year, String maker) throws FormatException{
        ValueCheck check = new ValueCheck();
        if (price.isEmpty()) { //check if list is empty and set price to 0 if so
            price = "0";
        }
        if(check.isInt(productID) && check.isInt(year) && check.isDouble(price)){ //check for valid input
            int id = Integer.parseInt(productID);
            int y = Integer.parseInt(year);
            double cost = Double.parseDouble(price);
            if (!(id >= 0 && id <= 999999)) {
                throw new FormatException(1);
            }
            if (!(y >= 1000 && y <= 9999)) {
                throw new FormatException(2);
            }
            try{ //if input is valid add book to book list 
                checkID(id);
                productList.add(new Electronics(id, description, cost, y, maker)); 
            }catch(FormatException e){
                throw new FormatException(4);
            }   
            hashIndex(); 
        }
        else{ //input is invalid
            throw new FormatException(3); 
        }
    }


//COMPARE METHODS-------------------------------------------------------------------------------------------------
    /**
     * Method checks for products of types book and electronic for matching productIDs
     * @param productID integer of productID
     * @throws FormatException check for format errors
     */
    public void checkID(int productID) throws FormatException{
        for (int i = 0; i < productList.size(); i++) { //check bookList for matching ID
            if (productID == (productList.get(i)).getProductID()) {
                throw new FormatException(4);
            }
        }
    }

    /**
     * Method checks if input book shares productID with another book in book list
     * @param book book for comparison
     * @return returns 1 if a match is found and 0 if not
     */
    public int checkMatchProductID(Product product){ 

        for (int i = 0; i < matchList.size(); i++) { //check for matching book ID
            if (product.getProductID() == (matchList.get(i)).getProductID()) {
                return 1;  //found a match
            }
        }
        return 0; //no match
    }
//HASH SEARCH METHODS--------------------------------------------------------------------------------------------
    /**
     * Searches book list for matching keywords and stores matches in arraylist of same type
     * @param string string for comparisons
     */
    public ArrayList <Product> searchKeywords(String string){
        ArrayList <Product> keyMatches = new ArrayList<Product> (); //stores matches
        ArrayList <Integer> matchIndex = hashIndexIntersection(string);
        if (matchIndex != null) {
            for (int i = 0; i < matchIndex.size(); i++) {
                keyMatches.add(productList.get(matchIndex.get(i)));
            } 
        }
        return keyMatches;
    }

    /**
     * Search for productId using index matching keywords
     * @param id
     * @param description
     * @throws FormatException
     */
    public void hashSearchProductID(String id, String description) throws FormatException{
        ValueCheck check = new ValueCheck();
        ArrayList <Integer> matchIndex = hashIndexIntersection(description);
        if (check.isInt(id)) { //check for valid input
            int value = Integer.parseInt(id);
            if (!(value >= 0 && value <= 999999)) {
                throw new FormatException(5);
            }
            for (int i = 0; i < matchIndex.size(); i++) { //check for matching productID
                if (value == productList.get(matchIndex.get(i)).getProductID()) {
                    if (checkMatchProductID(productList.get(matchIndex.get(i))) == 0) {
                        this.matchList.add(productList.get(matchIndex.get(i)));
                    }
                }
            }  
        }
        else{
            throw new FormatException(5);
        }
    }

        /**
     * Method searches for books of desired year range
     * 
     * @param string user input for year range to search
     * @author Mack Anderson
     */    
    public ArrayList <Product> hashSearchYears(String string, String description) throws FormatException{
        ArrayList <Product> yearMatches = new ArrayList<Product> (); //stores matches
        ArrayList <Integer> matchIndex = hashIndexIntersection(description);
        UserInput check = new UserInput();
        int yearOptions[] = check.inputYear(string); //user input for year range
        int toOrFrom; int y1; int y2;
        y1 = yearOptions[0];
        y2 = yearOptions[1];
        toOrFrom = yearOptions[2];
        if (y2 > 0 && toOrFrom == 0 ) { //range is between two years
            for (int i = 0; i < matchIndex.size(); i++) {
                if ((productList.get(matchIndex.get(i))).getYear() >= y1 && (productList.get(matchIndex.get(i))).getYear() <= y2) {
                    yearMatches.add(productList.get(matchIndex.get(i)));
                }
            }
        }
        else if(y2 == 0 && toOrFrom == 0){ //range is one year
            for (int i = 0; i < matchIndex.size(); i++) {
                if ((productList.get(matchIndex.get(i))).getYear() == y1) {
                    yearMatches.add(productList.get(matchIndex.get(i)));
                }
            }
        }
        else if(toOrFrom == 1 && y2 == 0){ //range is from y1 to infinity
            for (int i = 0; i < matchIndex.size(); i++) {
                if ((productList.get(matchIndex.get(i))).getYear() <= y1) {
                    yearMatches.add(productList.get(matchIndex.get(i)));
                }
            }
        }
        else if(toOrFrom == 2){
            for (int i = 0; i < matchIndex.size(); i++) { //range is from 0 to y1
                if ((productList.get(matchIndex.get(i))).getYear() >= y1) {
                    yearMatches.add(productList.get(matchIndex.get(i)));
                }
            }
        }
        return yearMatches;
    }
//SEARCH METHODS-------------------------------------------------------------------------------------------------    
    /**
     * Method searches for products with matching productIDs among book list and adds matches to an arraylist of matching type
     * @param id String containing comparison ID
     * @throws FormatException checks for improper input
     */
    public void searchProductID(String id) throws FormatException{
        ValueCheck check = new ValueCheck();
        if (check.isInt(id)) { //check for valid input
            int value = Integer.parseInt(id);
            if (!(value >= 0 && value <= 999999)) {
                throw new FormatException(5);
            }
            else{
                for (int i = 0; i < productList.size(); i++) { //check for matching productID
                    if (value == (productList.get(i)).getProductID()) {
                        if (checkMatchProductID(productList.get(i)) == 0) {
                            this.matchList.add(productList.get(i));
                        }
                    }
                } 
            } 
        }
        else{
            throw new FormatException(5);
        }
    }



    /**
     * Method searches for books of desired year range
     * 
     * @param string user input for year range to search
     * @author Mack Anderson
     */    
    public ArrayList <Product> searchYears(String string) throws FormatException{
        ArrayList <Product> yearMatches = new ArrayList<Product> (); //stores matches
        UserInput check = new UserInput();
        int yearOptions[] = check.inputYear(string); //user input for year range
        int toOrFrom; int y1; int y2;
        y1 = yearOptions[0];
        y2 = yearOptions[1];
        toOrFrom = yearOptions[2];
        if (y2 > 0 && toOrFrom == 0 ) { //range is between two years
            for (int i = 0; i < productList.size(); i++) {
                if ((productList.get(i)).getYear() >= y1 && (productList.get(i)).getYear() <= y2) {
                    yearMatches.add(productList.get(i));
                }
            }
        }
        else if(y2 == 0 && toOrFrom == 0){ //range is one year
            for (int i = 0; i < productList.size(); i++) {
                if ((productList.get(i)).getYear() == y1) {
                    yearMatches.add(productList.get(i));
                }
            }
        }
        else if(toOrFrom == 1 && y2 == 0){ //range is from y1 to infinity
            for (int i = 0; i < productList.size(); i++) {
                if ((productList.get(i)).getYear() <= y1) {
                    yearMatches.add(productList.get(i));
                }
            }
        }
        else if(toOrFrom == 2){
            for (int i = 0; i < productList.size(); i++) { //range is from 0 to y1
                if ((productList.get(i)).getYear() >= y1) {
                    yearMatches.add(productList.get(i));
                }
            }
        }
        return yearMatches;
    }

    public ArrayList <Product> compareLists(ArrayList <Product> keyMatches, ArrayList <Product> yearMatches){
        ArrayList <Product> matches = new ArrayList <Product> ();
        for (int i = 0; i < keyMatches.size(); i++) {
            for (int j = 0; j < yearMatches.size(); j++) {
                if (keyMatches.get(i).equals(yearMatches.get(j))) {
                    matches.add(keyMatches.get(i));
                }  
            }
        }
        return matches;
    }

    /**
     * Method searches for matches to the given parameters and prints matches to System.out
     * @param productID string containing productID to search for
     * @param description string containing keywords to search for
     * @param year string containing user input for year range to search
     * @throws FormatException checks for input errors
     * @return The number of matches
     */
    public int search(String productID, String description, String year) throws FormatException{
        matchList.clear(); //clear matches for future usage
        if (productID.isEmpty() && description.isEmpty() && year.isEmpty()) { //if user selection is empty for all search fields
            printAll(); //print all products
            return (productListSize());
        }
        else if(!productID.isEmpty() && !description.isEmpty() && !year.isEmpty()){
            hashSearchProductID(productID, description);
            this.matchList = compareLists(hashSearchYears(year, description), matchList);
        }
        else if(productID.isEmpty() && !description.isEmpty() && !year.isEmpty()){
            this.matchList = hashSearchYears(year, description);
        }
        else if(productID.isEmpty() && !description.isEmpty() && year.isEmpty()){
            this.matchList = searchKeywords(description);
        }
        else if(!productID.isEmpty() && !description.isEmpty() && year.isEmpty()){
            hashSearchProductID(productID, description);
        }
        else if(!productID.isEmpty() && description.isEmpty() && year.isEmpty()){
            searchProductID(productID);
        }
        else if(!productID.isEmpty() && description.isEmpty() && !year.isEmpty()){
            searchProductID(productID);
            this.matchList = compareLists(searchYears(year),matchList);
        }
        else if(productID.isEmpty() && description.isEmpty() && !year.isEmpty()){
            this.matchList = searchYears(year);
        }
        return (matchSize());
    }
    
//SIZE FUNCTIONS--------------------------------------------------------------------------------------------------
    /**
     * 
     * @return Size of bookList
     */
    public int productListSize(){
        return productList.size();
    }

    /**
     * 
     * @return Size of bookMatches
     */
    public int matchSize(){
        return this.matchList.size();
    }
//PRINT FUNCTIONS-------------------------------------------------------------------------------------------------

    /**
     * prints all products
     * @throws FormatException checks for input errors
     */
    public void printAll(){ //print all products
        for (int i = 0; i < productList.size(); i++) { //print products of type electronics
            System.out.println(productList.get(i).toString());
            System.out.println("");
        }
    }

    /**
     * prints all matching products
     * @throws FormatException checks for input errors
     */
    public void printMatches(){ //print all matching products
        for (int i = 0; i < matchList.size(); i++) { //print matching products of type electronics
            System.out.println(matchList.get(i).toString());
        }
    }

    /**
     * prints all products
     * @throws FormatException checks for input errors
     */
    public String allString(){ //print all products
        String returnString = "";
        for (int i = 0; i < productList.size(); i++) { //print products of type electronics
            returnString += productList.get(i).toString() + "\n";
        }
        return returnString;
    }

    /**
     * prints all matching products
     * @throws FormatException checks for input errors
     */
    public String matchString(){ //print all matching products
        String returnString = "";
        for (int i = 0; i < matchList.size(); i++) { //print matching products of type electronics
            returnString += matchList.get(i).toString() + "\n";
        }
        return returnString;
    }

    public String toString(){ //print all matching products
        String output = "";
        for (int i = 0; i < productList.size(); i++) { //print matching products of type electronics
            output += productList.get(i).toString();
        }
        return output;
    }

    /**
     * Output string for file output
     * @return Output string for file output
     */
    public ArrayList <String> toStringOutputStrings(){ //print all matching products
        ArrayList <String> output = new ArrayList <String>();
        for (int i = 0; i < productList.size(); i++) { //print matching products of type electronics
            output.add(productList.get(i).toOutputString());
        }
        return output;
    }

}
