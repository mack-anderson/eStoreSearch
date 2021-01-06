package eStoreSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddGUI implements ActionListener{
    private EStoreSearch eStore = new EStoreSearch();
    public static final int ADD_WIDTH = 800, ADD_HEIGHT = 600, NUMBER_OF_CHAR = 30;
    private String productType = "book";
    private JFrame addFrame;
    private JPanel addingProductPanel, messagePanel, typePanel, productIDPanel, descriptionPanel, pricePanel, yearPanel, authorPanel, publisherPanel, makerPanel;
    private JTextField typeText, productIDText, descriptionText, priceText, yearText, authorText, publisherText, makerText;
    private JLabel addingProduct, type, productID, description, price, year, authors, publisher, maker, resetSpacer, addSpacer, message;
    private JTextArea textArea = new JTextArea(10, 71);  
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
     * Method creates Add GUI interface
     * 
     * @author Mack Anderson
     */
    public AddGUI(){
        //CREATE FRAME-------------------------------------------------------------------------------------------------------
            addFrame = new JFrame();
            addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
            addFrame.setVisible(true);
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

            addFrame.setJMenuBar(commandBar);

        
        //CREATE BUTTONS AND ADD ACTION LISTENERS---------------------------------------------------------------------------------------------
        
            JButton resetButton = new JButton("Reset");
            JButton addButton = new JButton("Add");
            //SET BUTTON ACTION LISTENERS
            resetButton.addActionListener(this);
            addButton.addActionListener(this);
            productList.addActionListener(this);
            
        //TEXTBOX PANEL CREATION---------------------------------------------------------------------------------------------

            //SET PANEL LAYOUT
            addingProductPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            productIDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            yearPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            authorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            publisherPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            makerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            //SET TEXTFIELD SIZE
            typeText = new JTextField(NUMBER_OF_CHAR);
            productIDText = new JTextField(NUMBER_OF_CHAR);
            descriptionText = new JTextField(NUMBER_OF_CHAR);
            priceText = new JTextField(NUMBER_OF_CHAR);
            yearText = new JTextField(NUMBER_OF_CHAR);
            authorText = new JTextField(NUMBER_OF_CHAR);
            publisherText = new JTextField(NUMBER_OF_CHAR);
            makerText = new JTextField(NUMBER_OF_CHAR);

            //SET TEXTBOX PANEL PROMPT TEXT
            addingProduct = new JLabel("Adding a product");
            type = new JLabel("Type:       ");
            productID = new JLabel("ProductID:    ");
            description = new JLabel("Description: ");
            price = new JLabel("Price:            ");
            year = new JLabel("Year:             ");
            authors = new JLabel("Authors:       ");
            publisher = new JLabel("Publisher:    ");
            maker = new JLabel("Maker:          ");
            resetSpacer = new JLabel("                              ");
            addSpacer = new JLabel("                                ");
            message = new JLabel("Messages");

            //ADD ELEMENTS TO TEXTBOX PANELS

                //ADDING A PRODUCT LABEL
                addingProductPanel.add(addingProduct);

                //BOOK OR ELECTRONICS COMBOBOX
                typePanel.add(type, 0);
                typePanel.add(productList, 1);
                typePanel.setVisible(true);
        
                //PRODUCT ID PANEL
                productIDPanel.add(productID, 0);
                productIDPanel.add(productIDText, 1);
                productIDPanel.add(resetSpacer);
                productIDPanel.add(resetButton);

                //DESCRIPTION PANEL
                descriptionPanel.add(description, 0);
                descriptionPanel.add(descriptionText, 1);
                descriptionPanel.add(addSpacer);
                descriptionPanel.add(addButton);
        
                //PRICE PANEL
                pricePanel.add(price, 0);
                pricePanel.add(priceText, 1);
        
                //YEAR PANEL
                yearPanel.add(year, 0);
                yearPanel.add(yearText, 1);
        
                //AUTHOR PANEL
                authorPanel.add(authors, 0);
                authorPanel.add(authorText, 1);
        
                //PUBLISHER PANEL
                publisherPanel.add(publisher, 0);
                publisherPanel.add(publisherText, 1);
        
                //MAKER PANEL
                makerPanel.add(maker, 0);
                makerPanel.add(makerText, 1);

        //MESSAGE BOX------------------------------------------------------------------------------------------
            messagePanel.add(message);
            messagePanel.add(messageBox);
        //ADD ELEMENTS TO FRAME---------------------------------------------------------------------------------------------
            addFrame.add(addingProductPanel);
            addFrame.add(typePanel);
            addFrame.add(productIDPanel);
            addFrame.add(descriptionPanel);
            addFrame.add(yearPanel);
            addFrame.add(pricePanel);
            addFrame.add(authorPanel);
            addFrame.add(publisherPanel);
            addFrame.add(makerPanel);
            addFrame.add(messagePanel);

        //SET FRAME OPTIONS---------------------------------------------------------------------------------------------
            addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addFrame.setTitle("eStoreSearch");
            addFrame.setSize(ADD_WIDTH, ADD_HEIGHT);
            addFrame.setLocation(100, 100);
            addFrame.setVisible(true);
            
        addBookGUI();
    }

    /**
     * Method Shows only nessasary text fields for book addition
     * 
     * @author Mack Anderson
     */
    public void addBookGUI(){
        //HIDE THE START MENU
        addFrame.setVisible(true);
        addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
        makerPanel.setVisible(false);
        authorPanel.setVisible(true);
        publisherPanel.setVisible(true);
        productList.setSelectedIndex(0);
    }

    /**
     * Method Shows only nessasary text fields for electronics addition
     * 
     * @author Mack Anderson
     */
    public void addElectronicsGUI(){

        authorPanel.setVisible(false);
        publisherPanel.setVisible(false);
        makerPanel.setVisible(true);

        //SET COMBO BOX
        productList.setSelectedIndex(1);
    }

    /**
     * Method adds product from provided text fields
     * 
     * @author Mack Anderson
     */
    public void addButtonAction(){
        if (productType.equals("book")) {
            try {
                eStore.addBook(productIDText.getText(), descriptionText.getText(), priceText.getText(), yearText.getText(), authorText.getText(), publisherText.getText());
                textArea.setText(textArea.getText() + "Book added successfully\n" );
                typeText.setText("");
                productIDText.setText("");
                descriptionText.setText("");
                priceText.setText("");
                yearText.setText("");
                authorText.setText("");
                publisherText.setText("");
                makerText.setText("");
            } catch (FormatException e) {
                textArea.setText(textArea.getText() + e.printMessage() + "\n" );
            }
        }
        else if (productType.equals("electronics")) {
            try {
                eStore.addElectronics(productIDText.getText(), descriptionText.getText(), priceText.getText(), yearText.getText(), makerText.getText());
                textArea.setText(textArea.getText() + "Electronic added successfully\n" );
                typeText.setText("");
                productIDText.setText("");
                descriptionText.setText("");
                priceText.setText("");
                yearText.setText("");
                authorText.setText("");
                publisherText.setText("");
                makerText.setText("");
            } catch (FormatException e) {
                textArea.setText(textArea.getText() + e.printMessage() + "\n" );
            }
        }
    }

    /**
     * Method resets text fields
     * 
     * @author Mack Anderson
     */
    public void resetButtonAction(){
        typeText.setText("");
        productIDText.setText("");
        descriptionText.setText("");
        priceText.setText("");
        yearText.setText("");
        authorText.setText("");
        publisherText.setText("");
        makerText.setText("");
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
        String comboType = productList.getSelectedItem().toString();
        if (buttonString.equals("search")) {
            SearchGUI sGUI = new SearchGUI();
            sGUI.setEStore(eStore);
            addFrame.dispose();
        }
        else if (buttonString.equals("quit")) {
            System.exit(0);
        }
        else if (buttonString.equals("file")) {
            FileGUI fGui = new FileGUI();
            fGui.setEStore(eStore);
            addFrame.dispose();
        }
        else if (buttonString.equals("Reset")) {
            resetButtonAction();
        }
        else if (buttonString.equals("Add")) {
            addButtonAction();
        }
        else if (comboType.equals("book")) {
            productType = "book";
            addBookGUI();
            productList.setSelectedIndex(0);
        }
        else if (comboType.equals("electronics")) {
            productType = "electronics";
            productList.setSelectedIndex(1);
            addElectronicsGUI();
        }
    }
}
