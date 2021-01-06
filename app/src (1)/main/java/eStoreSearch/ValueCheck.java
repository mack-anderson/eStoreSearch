package eStoreSearch;

public class ValueCheck {
    /**
     * Method checks if string is an int
     * @param string string containing value to check for int
     * @return return true if string is an int and false otherwise
     */
    public boolean isInt(String string) { // check for empty string 
        if (string == null) {
            return false;
        }
        try { //try to convert string to integer
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Method checks if string is a double
     * @param string string containing value to check for double
     * @return return true if string is a double and false otherwise
     */
    public boolean isDouble(String string) { // check for empty string 
        if (string == null) {
            return false;
        }
        try { //try to convert string to integer
            Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    
}
