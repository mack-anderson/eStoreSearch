package eStoreSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StartGUI implements ActionListener{
    
    public static final int START_WIDTH = 700;
    public static final int START_HEIGHT = 300;
    private EStoreSearch eStore = new EStoreSearch();
    private JFrame startFrame;
    private JPanel startPanel1, startPanel2;
    private JMenuBar commandBar;
    private JMenu commands;


    public void setEStore(EStoreSearch eStore){
        this.eStore = eStore;
    }

    public StartGUI(String startFile){
        String fileName = "";
        if(startFile != null){
            if(!startFile.isEmpty()){
                fileName = "The file " + startFile + " imported successfully";
            }
        }
        //CREATE FRAMES------------------------------------------------------------------------------------------------
        startFrame = new JFrame();
        startFrame.setLayout(new GridLayout(2,1));

        //CREATE START PANELS-----------------------------------------------------------------------------------------
        JLabel startLabel1 = new JLabel("Welcome to eStoreSearch");
        JLabel startLabel2 = new JLabel("<html>Choose a command from the “Commands” menu above for adding a product, searching products,<br/> inputting and outputting files, or quitting the program.</html>          ");
        JLabel startLabel3 = new JLabel(fileName);
        startPanel1 = new JPanel();
        startPanel2 = new JPanel();
        startPanel1.add(startLabel1);
        startPanel2.add(startLabel2);
        startPanel2.add(startLabel3);
        startPanel2.setVisible(true);

        //ADD ELEMENTS TO START FRAME---------------------------------------------------------------------------------------
        startFrame.add(startPanel1);
        startFrame.add(startPanel2);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setTitle("eStoreSearch");
        startFrame.pack();
        startFrame.setSize(START_WIDTH, START_HEIGHT);
        startFrame.setLocation(100, 100);
        startFrame.setVisible(true);

        //CREATE MENU--------------------------------------------------------------------------------------------------
        commandBar = new JMenuBar();
        commands = new JMenu("Commands");
        commandBar.add(commands);
        commands.addActionListener(this);

        JMenuItem add = new JMenuItem("add");
        JMenuItem search = new JMenuItem("search");
        JMenuItem quit = new JMenuItem("quit");
        JMenuItem file = new JMenuItem("file");

        commands.add(add);
        add.addActionListener(this);
        commands.add(search);
        search.addActionListener(this);
        commands.add(file);
        file.addActionListener(this);
        commands.add(quit);
        quit.addActionListener(this);

        startFrame.setJMenuBar(commandBar);
        
        startFrame.setVisible(true);
    }

    /**
     * Method handles ui interactions
     * 
     * @author Mack Anderson
     * @param e Action event
     */
    public void actionPerformed(ActionEvent e){
        String buttonString = e.getActionCommand();
        if (buttonString.equals("add")) {
            AddGUI aGui = new AddGUI();
            aGui.setEStore(eStore);
            startFrame.dispose();
        }
        else if (buttonString.equals("search")) {
            SearchGUI sGUI = new SearchGUI();
            sGUI.setEStore(this.eStore);
            startFrame.dispose();
        }
        else if (buttonString.equals("quit")) {
            System.exit(0);
        }
        else if (buttonString.equals("file")) {
            FileGUI fGui = new FileGUI();
            fGui.setEStore(eStore);
            startFrame.dispose();
        }
    }
}
