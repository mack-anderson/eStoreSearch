package eStoreSearch;

public class Book extends Product{
    private String author;
    private String publisher;

    
    public Book() {
        
    }

    public Book(Book book){
        super(book.getProductID(), book.getDescription(), book.getPrice(), book.getYear());
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
    }

    /**
     * Method Sets Book values 
     * 
     * @author Mack Anderson
     * @param productID The productID value
     * @param description The description of the book
     * @param price The price of the book
     * @param year The year of the book
     * @param author The author of the book
     * @param publisher The publisher of the book
     */
    public Book(int productID, String description, double price, int year, String author, String publisher){
        super(productID, description, price, year);
        this.author = author;
        this.publisher = publisher;
    }


    //SET METHODS----------------------------------------------------------------------------------------------
    /**
     * Method Sets Book values 
     * 
     * @author Mack Anderson
     * @param productID The productID value
     * @param description The description of the book
     * @param price The price of the book
     * @param year The year of the book
     * @param author The author of the book
     * @param publisher The publisher of the book
     */
    public void set(int productID, String description, int price, int year, String author, String publisher){
        try {
            setProductID(productID);
            setDescription(description);
            setPrice(price);
            setYear(year);
            this.author = author;
            this.publisher = publisher;
        } catch (FormatException e) {
            System.out.println(e.printMessage());
        }
    }

    /**
     * Method Sets author of book
     * 
     * @author Mack Anderson
     * @param author The Author of the product
     */
    public void setAuthor(String author) throws FormatException{
        if (!(author == null || author.isEmpty())) {
            this.author = author;
        }
        else{
            throw new FormatException(9);
        }
    }

    /**
     * Method Sets Publisher of book
     * 
     * @author Mack Anderson
     * @param publisher The Publisher of the product
     */
    public void setPublisher(String publisher) throws FormatException{
        if (!(publisher == null || publisher.isEmpty())) {
            this.publisher = publisher;
        }
        else{
            throw new FormatException(9);
        }
    }
    

    //GET METHODS----------------------------------------------------------------------------------------------
    /**
     * Method gets Author of product
     * 
     * @author Mack Anderson
     */
    public String getAuthor(){
        return author;
    }

    /**
     * Method gets Publisher of product
     * 
     * @author Mack Anderson
     */
    public String getPublisher(){
        return publisher;
    }

//EQUALS METHODS----------------------------------------------------------------------------------------------
    
    /**
     * Method checks for equal object
     * 
     * @author Mack Anderson
     * @param object The object for comparison
     */
    public boolean equals(Object obj){
        // checking if both the object references are  
        // referring to the same object. 
        if(this == obj) 
        {
            return true; 
        }
                
        // it checks if the argument is of the  
        // type Book by comparing the classes  
        // of the passed argument and this object. 
        if(obj == null || obj.getClass()!= getClass()) 
        {
            return false; 
        }
        Book book = (Book) obj;
            
        // compare object instance variables
        return (getProductID() == book.getProductID() && getDescription().equals(book.getDescription()) &&
                getPrice() == book.getPrice() && getYear() == book.getYear() && author.equals(book.author) &&
                publisher.equals(book.publisher));   
    }

    
//toSTRING METHOD----------------------------------------------------------------------------------------------

    
    /**
     * returns string containing information of Book object
     * 
     * @author Mack Anderson
     */
    public String toString(){ //return string containing information of Book object
        String string = "Book--------------------------------------\nProductID: " + String.format("%06d",getProductID()) + "\nDescription: " + getDescription() + "\nYear: " + getYear();
        if (getPrice() != 0) {
            string += "\nPrice: $" + getPrice();
        }
        if (!getAuthor().isEmpty()) {
            string += "\nAuthor: " + getAuthor();
        }
        if (!getPublisher().isEmpty()) {
            string += "\nPublisher: " + getPublisher();
        }
        return string;
    }

    @Override
    public String toOutputString(){ //return string containing information of Book object
        String string = "type = \"book\"\nproductID = " + "\"" + String.format("%06d",getProductID()) + "\"" + "\ndescription = " + "\"" + getDescription()+ "\"" + "\nyear = " + "\"" +getYear() + "\"";
        if (getPrice() != 0) {
            string += "\nprice = " + "\"" + getPrice() + "\"";
        }
        if (!getAuthor().isEmpty()) {
            string += "\nauthors = " + "\"" + getAuthor() + "\"";
        }
        if (!getPublisher().isEmpty()) {
            string += "\npublisher = " + "\"" + getPublisher() + "\"";
        }
        return string;
    }
}
