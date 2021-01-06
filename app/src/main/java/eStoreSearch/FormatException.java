package eStoreSearch;

public class FormatException extends Exception{
    /**
     *
     */
    private int code;
    public FormatException(int code){
        this.code = code;
    }

    /**
     * Prints error messages to System.out
     * @return string containing desired error message
     */
    public String printMessage(){
        String output = " ";

        if(code == 1){
            output = "ERROR: Not a valid Product ID (must be 000000-999999)";
        }
        if(code == 2){
            output = "ERROR: Not a valid Year (must be 1000-9999)";
        }
        if(code == 3){
            output = "ERROR: productID, Year, and Description must be included. ProductID(6-digits), Year(4-digit), and Price must be numbers";
        }
        if(code == 4){
            output = "ERROR: A product with desired productID already exists therefor product will not be added";
        }
        if(code == 5){
            output = "Not a valid productID (must be a 6-digit numberic value)";
        }
        if(code == 6){
            output = "ERROR: File not found";
        }
        if(code == 7){
            output = "ERROR: Description is empty";
        }
        if(code == 8){
            output = "ERROR: Price can't be negative";
        }
        if(code == 9){
            output = "ERROR: Maker is empty";
        }
        if(code == 10){
            output = "ERROR: Could not open file";
        }

        return output;
    }
}
