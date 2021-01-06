package eStoreSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FileGUI implements ActionListener{
    private EStoreSearch eStore = new EStoreSearch();
    public static final int FILE_WIDTH = 800, FILE_HEIGHT = 300, NUMBER_OF_CHAR = 30;
    private JFrame fileFrame;
    private JPanel importMessagePanel, messagePanel, fileInputPanel, fileExportPanel, exportMessagePanel;
    private JTextField importFileText, exportFileText;
    private JLabel importFile, importFileMessage, exportFile, importSpacer, exportSpacer, message, resetSpacer;
    private JTextArea textArea = new JTextArea(5, 71);  
    private JScrollPane messageBox = new JScrollPane(textArea);
    private JMenuBar commandBar;
    private JMenu commands;

    /**
     * Method sets eStore for gui
     * 
     * @author Mack Anderson
     * @param eStore EStoreSearch object
     */
    public void setEStore(EStoreSearch eStore){
        this.eStore = eStore;
    }

    public FileGUI(){
        //CREATE FRAME-------------------------------------------------------------------------------------------------------------
            fileFrame = new JFrame();
            fileFrame.setLayout(new BoxLayout(fileFrame.getContentPane(), BoxLayout.Y_AXIS));
            fileFrame.setVisible(true);
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

            fileFrame.setJMenuBar(commandBar);

        
        //CREATE BUTTONS AND ADD ACTION LISTENERS---------------------------------------------------------------------------------------------
        
            JButton importFileButton = new JButton("Import File");
            JButton exportFileButton = new JButton("Export File");
            JButton resetFileButton = new JButton("Reset");

            //SET BUTTON ACTION LISTENERS
            importFileButton.addActionListener(this);
            exportFileButton.addActionListener(this);
            resetFileButton.addActionListener(this);
            
        //TEXTBOX PANEL CREATION---------------------------------------------------------------------------------------------

            //SET PANEL LAYOUT
            importMessagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            fileInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            fileExportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            exportMessagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            //SET TEXTFIELD SIZE
            importFileText = new JTextField(NUMBER_OF_CHAR);
            exportFileText = new JTextField(NUMBER_OF_CHAR);

            //SET TEXTBOX PANEL PROMPT TEXT
            importFileMessage = new JLabel("Import and Export files");
            importFile = new JLabel("File to load: ");
            exportFile = new JLabel("File to save to: ");
            importSpacer = new JLabel("                                     ");
            exportSpacer = new JLabel("                                ");
            resetSpacer = new JLabel("                                                                                                            ");
            message = new JLabel("Messages");

            //ADD ELEMENTS TO TEXTBOX PANELS

                //ADDING A PRODUCT LABEL
                importMessagePanel.add(importFileMessage);
                importMessagePanel.add(resetSpacer);
                importMessagePanel.add(resetFileButton);
        
                //PRODUCT ID PANEL
                fileInputPanel.add(importFile, 0);
                fileInputPanel.add(importFileText, 1);
                fileInputPanel.add(importSpacer);
                fileInputPanel.add(importFileButton);

                //DESCRIPTION PANEL
                fileExportPanel.add(exportFile, 0);
                fileExportPanel.add(exportFileText, 1);
                fileExportPanel.add(exportSpacer);
                fileExportPanel.add(exportFileButton);

        //MESSAGE BOX------------------------------------------------------------------------------------------
            messagePanel.add(message);
            messagePanel.add(messageBox);
        //ADD ELEMENTS TO FRAME---------------------------------------------------------------------------------------------
            fileFrame.add(importMessagePanel);
            fileFrame.add(fileInputPanel);
            fileFrame.add(exportMessagePanel);
            fileFrame.add(fileExportPanel);
            fileFrame.add(messagePanel);

        //SET FRAME OPTIONS---------------------------------------------------------------------------------------------
            fileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fileFrame.setTitle("eStoreSearch");
            fileFrame.setSize(FILE_WIDTH, FILE_HEIGHT);
            fileFrame.setLocation(100, 100);
            fileFrame.setVisible(true);
            
    }

    public void importFileButtonAction(){
        UserInput input = new UserInput();
        input.setEStore(eStore);
        if (!input.fileInput(importFileText.getText())) {
            textArea.setText(textArea.getText() + "Could not open the file " + importFileText.getText() + "\n" );
        }
        else{
            textArea.setText(textArea.getText() + "The file " + importFileText.getText() + " was imported successfully\n");
            importFileText.setText("");
        }
    }

    
    public void exportFileButtonAction(){
        UserInput input = new UserInput();
        input.setEStore(eStore);
        if (!input.fileOutput(exportFileText.getText())) {
            textArea.setText(textArea.getText() + "Could not save to the file " + exportFileText.getText() + "\n" );
        }
        else{
            textArea.setText(textArea.getText() + "The file " + exportFileText.getText() + " was saved successfully\n");
            exportFileText.setText("");
        }
    }

    public void resetButtonAction(){
        importFileText.setText("");
        exportFileText.setText("");
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
            fileFrame.dispose();
        }
        else if (buttonString.equals("add")) {
            AddGUI aGUI = new AddGUI();
            aGUI.setEStore(eStore);
            fileFrame.dispose();
        }
        else if (buttonString.equals("search")) {
            SearchGUI sGUI = new SearchGUI();
            sGUI.setEStore(eStore);
            fileFrame.dispose();
        }
        else if (buttonString.equals("quit")) {
            System.exit(0);
        }
        else if (buttonString.equals("Import File")) {
            importFileButtonAction();
        }
        else if (buttonString.equals("Export File")) {
            exportFileButtonAction();
        }
        else if (buttonString.equals("Reset")) {
            resetButtonAction();
        }

    }
}
