package eStoreSearch;

public class Electronics extends Product{
    private String maker;

    public Electronics(){
    }

    public Electronics(Electronics electronics){
        super(electronics.getProductID(), electronics.getDescription(), electronics.getPrice(), electronics.getYear());
        this.maker = electronics.getMaker();
    }

    /**
     * Method Sets Book values 
     * 
     * @author Mack Anderson
     * @param productID The productID value
     * @param description The description of the product
     * @param price The price of the product
     * @param year The year of the product
     * @param maker The maker of the product
     */
    public Electronics(int productID, String description, double price, int year, String maker){
        super(productID, description, price, year);
        this.maker = maker;
    }
    //SET METHODS----------------------------------------------------------------------------------------------
    /**
     * Method Sets Electronics values 
     * 
     * @author Mack Anderson
     * @param productID The productID value
     * @param des The description of the product
     * @param cost The price of the product
     * @param yyyy The year of the product
     * @param make The maker of the product
     */
    public void set(int productID, String description, int price, int year, String maker){
        try {
            setProductID(productID);
            setDescription(description);
            setPrice(price);
            setYear(year);
            this.maker = maker;
        } catch (FormatException e) {
            System.out.println(e.printMessage());
        }

    }

    /**
     * Method Sets Maker of product
     * 
     * @author Mack Anderson
     * @param maker The Maker of the product
     */
    public void setMaker(String maker) throws FormatException{
        if (!(maker == null || maker.isEmpty())) {
            this.maker = maker;
        }
        else{
            throw new FormatException(9);
        }
    }


    //GET METHODS----------------------------------------------------------------------------------------------
    /**
     * Method gets Maker of product
     * 
     * @author Mack Anderson
     */
    public String getMaker(){
        return maker;
    }


//EQUALS METHODS----------------------------------------------------------------------------------------------
    @Override
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
        Electronics electronics = (Electronics) obj;
            
        // compare object instance variables
        return (electronics.getProductID() == getProductID() && getDescription().equals(electronics.getDescription()) &&
                electronics.getPrice() == getPrice() && electronics.getYear() == getYear() && maker.equals(electronics.maker));   
    }

//toSTRING METHOD----------------------------------------------------------------------------------------------

    
    /**
     * returns string containing information of Electronics object
     * 
     * @author Mack Anderson
     */
    public String toString() //return string containing information of electronics object
    {
        String string = "Electronics----------------------------------\nProductID: " + String.format("%06d",getProductID()) + "\nDescription: " + getDescription() + "\nYear: " + getYear();
        if (getPrice() != 0) {
            string += "\nPrice: $" + getPrice();
        }
        if (!getMaker().isEmpty()) {
            string += "\nMaker: " + getMaker();
        }

        return string;
    }
    
    
    public String toOutputString(){ //return string containing information of Book object
        String string = "type = \"electronics\"\nproductID = " + "\"" + String.format("%06d",getProductID()) + "\"" + "\ndescription = " + "\"" + getDescription()+ "\"" + "\nyear = " + "\"" +getYear() + "\"";
        if (getPrice() != 0) {
            string += "\nprice = " + "\"" + getPrice() + "\"";
        }
        if (!getMaker().isEmpty()) {
            string += "\nauthors = " + "\"" + getMaker() + "\"";
        }
        return string;
    }
}
