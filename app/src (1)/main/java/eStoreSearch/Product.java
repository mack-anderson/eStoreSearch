package eStoreSearch;

abstract class Product {
    private int productID;
    private String description;
    private double price;
    private int year;

    /**Product Constructor */
    public Product(){

    }

    /**
     * Product Constructor
     * @param productID
     * @param description
     * @param price
     * @param year
     */
    public Product(int productID, String description, double price, int year){
        try {
            setProductID(productID);
            setDescription(description);
            setPrice(price);
            setYear(year);
        }catch(FormatException e){
            System.out.println(e.printMessage());
        }  
    }
    
//SET METHODS----------------------------------------------------------------------------------------------
    /**
     * Method Sets productID value 
     * 
     * @author Mack Anderson
     * @param productID The productID value
     */
    public void setProductID(int productID) throws FormatException{
        if (productID >= 0 && productID <= 999999) {
            this.productID = productID;
        }
        else{
            throw new FormatException(5);
        } 
    }

    /**
     * Method Sets Description of product
     * 
     * @author Mack Anderson
     * @param description The description of the product
     */
    public void setDescription(String description) throws FormatException{
        if (!(description == null || description.isEmpty())) {
            this.description = description;
        }
        else{
            throw new FormatException(7);
        }
    }
        

    /**
     * Method Sets value of Price
     * 
     * @author Mack Anderson
     * @param price The price of the product
     */
    public void setPrice(double price) throws FormatException{
        if(price >= 0){
            this.price = price;
        }
        else{
            throw new FormatException(8);
        }
    }

    /**
     * Method Sets Year of product
     * 
     * @author Mack Anderson
     * @param year The year of the product
     */
    public void setYear(int year) throws FormatException{
        if (year >= 1000 && year <= 9999) {
            this.year = year;
        }
        else{
            throw new FormatException(2);
        }
    }


//GET METHODS----------------------------------------------------------------------------------------------
    /**
     * Method Gets productID value 
     * 
     * @author Mack Anderson
     */
    public int getProductID(){
        return productID;
    }

    /**
     * Method gets Description of product
     * 
     * @author Mack Anderson
     */
    public String getDescription(){
        return description;
    }

    /**
     * Method gets value of Price
     * 
     * @author Mack Anderson
     */
    public double getPrice(){
        return price;
    }

    /**
     * Method gets Year of product
     * 
     * @author Mack Anderson
     */
    public int getYear(){
        return year;
    }

//EQUALS METHODS-------------------------------------------------------------------------------------------
    public abstract boolean equals(Object obj);
    public abstract String toOutputString();

    public boolean equalsProductID(Object obj){
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
        Product product = (Product) obj;
            
        // compare object instance variables
        return (product.getProductID() == productID);   
    }
}
