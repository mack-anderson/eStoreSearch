/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package eStoreSearch;

public class App {
    public static void main(String[] args) {
        UserInput input = new UserInput();
        if (args.length == 1) { //If there is a command line entry for file attempt to load file
            if(input.fileInput((args[0]))){
                StartGUI gui = new StartGUI(args[0] + " was ");
                gui.setEStore(input.getEStore());
            }
            else{
                StartGUI gui = new StartGUI(args[0] + " was not ");
                gui.setEStore(input.getEStore());
            }
        }
        else{
            StartGUI gui = new StartGUI("");
            gui.setEStore(input.getEStore());
        }
    }
}
