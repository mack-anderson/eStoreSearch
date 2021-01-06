package eStoreSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchGUI implements ActionListener{
    private EStoreSearch eStore = new EStoreSearch();
    public static final int SEARCH_WIDTH = 800, SEARCH_HEIGHT = 800, NUMBER_OF_CHAR = 30;
    private JFrame searchFrame;
    private JPanel searchProductPanel, messagePanel, productIDPanel, descriptionPanel, yearStartPanel, yearEndPanel;
    private JTextField productIDText, descriptionText, startYearText, endYearText;
    private JLabel addingProduct,productID, description, startYear, endYear, resetSpacer, addSpacer, message;
    private JTextArea textArea = new JTextArea(25, 71);  
    private JScrollPane messageBox = new JScrollPane(textArea);
    private JMenuBar commandBar;
    private JMenu commands;
    private String[] productOption = {"book","electronics"};  
    private JComboBox<String> productList = new JComboBox<>(productOption);

    /**
     * Method sets eStore for gui
     * 
     * @author Mack Anderson
     * @param eStore EStoreSearch object
     */
    public void setEStore(EStoreSearch eStore){
        this.eStore = eStore;
    }

    /**
     * Method creates search GUI interface
     * 
     * @author Mack Anderson
     */
    public SearchGUI(){
        //CREATE FRAME-------------------------------------------------------------------------------------------------------
            searchFrame = new JFrame();
            searchFrame.setLayout(new BoxLayout(searchFrame.getContentPane(), BoxLayout.Y_AXIS));
            searchFrame.setVisible(true);
        //CREATE COMMAND BAR-------------------------------------------------------------------------------------------------------
            commandBar = new JMenuBar();
            commands = new JMenu("Commands");
            commandBar.add(commands);
            commands.addActionListener(this);

            JMenuItem start = new JMenuItem("start");
            JMenuItem add = new JMenuItem("add");
            JMenuItem search = new JMenuItem("search");
            JMenuItem quit = new JMenuItem("quit");
            JMenuItem file = new JMenuItem("file");

            commands.add(start);
            start.addActionListener(this);
            commands.add(add);
            add.addActionListener(this);
            commands.add(search);
            search.addActionListener(this);
            commands.add(file);
            file.addActionListener(this);
            commands.add(quit);
            quit.addActionListener(this);

            searchFrame.setJMenuBar(commandBar);

        
        //CREATE BUTTONS AND ADD ACTION LISTENERS---------------------------------------------------------------------------------------------
        
            JButton resetButton = new JButton("Reset");
            JButton searchButton = new JButton("Search");
            //SET BUTTON ACTION LISTENERS
            resetButton.addActionListener(this);
            searchButton.addActionListener(this);
            productList.addActionListener(this);
            
        //TEXTBOX PANEL CREATION---------------------------------------------------------------------------------------------

            //SET PANEL LAYOUT
            searchProductPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            productIDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            yearStartPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            yearEndPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            //SET TEXTFIELD SIZE
            productIDText = new JTextField(NUMBER_OF_CHAR);
            descriptionText = new JTextField(NUMBER_OF_CHAR);
            startYearText = new JTextField(NUMBER_OF_CHAR);
            endYearText = new JTextField(NUMBER_OF_CHAR);

            //SET TEXTBOX PANEL PROMPT TEXT
            addingProduct = new JLabel("Searching Products");
            productID = new JLabel("ProductID: ");
            description = new JLabel("<html>Description<br/>Keywords</html>");
            startYear = new JLabel("Start Year: ");
            endYear = new JLabel("End Year:   ");
            resetSpacer = new JLabel("                                 ");
            addSpacer = new JLabel("                                ");
            message = new JLabel("Search Results");

            //ADD ELEMENTS TO TEXTBOX PANELS

                //ADDING A PRODUCT LABEL
                searchProductPanel.add(addingProduct);
        
                //PRODUCT ID PANEL
                productIDPanel.add(productID, 0);
                productIDPanel.add(productIDText, 1);
                productIDPanel.add(resetSpacer);
                productIDPanel.add(resetButton);

                //DESCRIPTION PANEL
                descriptionPanel.add(description, 0);
                descriptionPanel.add(descriptionText, 1);
                descriptionPanel.add(addSpacer);
                descriptionPanel.add(searchButton);

                //YEAR PANEL
                yearStartPanel.add(startYear, 0);
                yearStartPanel.add(startYearText, 1);
        
                //AUTHOR PANEL
                yearEndPanel.add(endYear, 0);
                yearEndPanel.add(endYearText, 1);

        //MESSAGE BOX------------------------------------------------------------------------------------------
            messagePanel.add(message);
            messagePanel.add(messageBox);
        //ADD ELEMENTS TO FRAME---------------------------------------------------------------------------------------------
            searchFrame.add(searchProductPanel);
            searchFrame.add(productIDPanel);
            searchFrame.add(descriptionPanel);
            searchFrame.add(yearStartPanel);
            searchFrame.add(yearEndPanel);
            searchFrame.add(messagePanel);

        //SET FRAME OPTIONS---------------------------------------------------------------------------------------------
            searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            searchFrame.setTitle("eStoreSearch");
            searchFrame.setSize(SEARCH_WIDTH, SEARCH_HEIGHT);
            searchFrame.setLocation(100, 100);
            searchFrame.setVisible(true);
            
    }

    /**
     * Method searchs based on text field input
     * 
     * @author Mack Anderson
     */
    public void searchButtonAction(){
        String tempYear;
        if (startYearText.getText().isEmpty() && endYearText.getText().isEmpty()) {
            tempYear = "";
        }
        else if(!startYearText.getText().isEmpty() && !endYearText.getText().isEmpty()){
            tempYear = startYearText.getText() + "-" + endYearText.getText();
            textArea.setText(textArea.getText() + "Year input interpreted as " + tempYear + "\n" );
        }
        else if(!startYearText.getText().isEmpty() && endYearText.getText().isEmpty()){
            tempYear = startYearText.getText() + "-";
            textArea.setText(textArea.getText() + "Year input interpreted as " + tempYear + "\n" );
        }
        else{
            tempYear = "-" + endYearText.getText();
            textArea.setText(textArea.getText() + "Year input interpreted as " + tempYear + "\n" );
        }
        try {
            if (productIDText.getText().isEmpty() && descriptionText.getText().isEmpty() && startYearText.getText().isEmpty() && endYearText.getText().isEmpty() && eStore.productListSize() != 0) {
                textArea.setText(textArea.getText() + "SEARCH RESULTS:****************************************************************************\n" + eStore.allString());
            }
            else if (eStore.search(productIDText.getText(), descriptionText.getText(), tempYear) == 0) {
                textArea.setText(textArea.getText() + "No Match Found\n");
            }
            else{
                textArea.setText(textArea.getText() + "SEARCH RESULTS:****************************************************************************\n" + eStore.matchString());
            }
            
        } catch (FormatException e) {
            textArea.setText(textArea.getText() + e.printMessage() + "\n" );
        }
    }

    /**
     * Method resets text fields
     * 
     * @author Mack Anderson
     */
    public void resetButtonAction(){
        productIDText.setText("");
        descriptionText.setText("");
        startYearText.setText("");
        endYearText.setText("");
        textArea.setText("");
    }

    /**
     * Method handles ui interactions
     * 
     * @author Mack Anderson
     * @param e Action event
     */
    public void actionPerformed(ActionEvent e){
        String buttonString = e.getActionCommand();
        if (buttonString.equals("start")) {
            StartGUI sGUI = new StartGUI("");
            sGUI.setEStore(eStore);
            searchFrame.dispose();
        }
        else if (buttonString.equals("add")) {
            AddGUI aGUI = new AddGUI();
            aGUI.setEStore(eStore);
            searchFrame.dispose();
        }
        else if (buttonString.equals("quit")) {
            System.exit(0);
        }
        else if (buttonString.equals("file")) {
            FileGUI fGui = new FileGUI();
            fGui.setEStore(eStore);
            searchFrame.dispose();
        }
        else if (buttonString.equals("Reset")) {
            resetButtonAction();
        }
        else if (buttonString.equals("Search")) {
            searchButtonAction();
        }
    }
}
