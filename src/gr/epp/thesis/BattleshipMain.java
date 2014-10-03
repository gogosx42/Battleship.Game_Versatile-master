package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import gr.epp.thesis.api.GenericLabel;
import gr.epp.thesis.api.GenericValues;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @@institute Applied Informatics and Multimedia - TEI of Crete
 */
public class BattleshipMain implements ActionListener, Runnable {

    private GenericValues playerValues;
    private JFrame startingFrame = new JFrame("Battleship Game");
    private JLabel welcomeImage = new JLabel(new ImageIcon("graphics/admiralDecorLabel.png"));
    //private JPanel startingPanel = new JPanel(new GridLayout(2, 2));
    private JLabel playerTitle = new JLabel("Conquer the seas as..", SwingConstants.CENTER);
    private JLabel observerTitle = new JLabel("Observe an ongoing battle!", SwingConstants.CENTER);
    private String[] playerType = {"Adult", "Child", "Admiral"};
    private JButton observerButton = new JButton("Observer");
    private JComboBox playerTypeBox = new JComboBox(playerType);
    private String currentPlayer = null;
    private static JFrame masterFrame = new JFrame("Battleship Game");
    private JPanel upPanel = new JPanel();
    private JPanel decorPanel = new JPanel();
    private JPanel downPanel = new JPanel();
    private JPanel allyBoard = new JPanel();
    private JPanel enemyBoard = new JPanel();
    private static Class tempClass;
    private GameControl gameControl;
    private JPanel allyShipsListPanel;
    private JPanel enemyShipsListPanel;
    private PrintWriter out = null;
    private BufferedReader in = null;
    //private DataInputStream in2 = null;
    //private DataOutputStream out2 = null;
    private static Socket clientSocket = null;

    /*
     * Player Selection
     */
    public BattleshipMain() {
        startingFrame.setVisible(true);
        startingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startingFrame.setLocationRelativeTo(null);
        startingFrame.setSize(525, 350);
        startingFrame.setResizable(true);
        startingFrame.setLayout(new BorderLayout());
        startingFrame.add(welcomeImage);

        welcomeImage.setLayout(new GridLayout(2, 2, 20, 20));
        welcomeImage.add(playerTitle);
        welcomeImage.add(observerTitle);
        welcomeImage.add(playerTypeBox);
        welcomeImage.add(observerButton);

        playerTitle.setFont(new Font("Verdana", Font.BOLD, 14));
        playerTitle.setForeground(Color.WHITE);

        observerTitle.setFont(new Font("Verdana", Font.BOLD, 14));
        observerTitle.setForeground(Color.WHITE);
        //selectObserverTitle.setLayout(new FlowLayout());
        //selectObserverTitle.add(new JButton("VS"));

        playerTypeBox.setSize(50, 50);
        playerTypeBox.setSelectedIndex(0);
        playerTypeBox.addActionListener(this);
        playerTypeBox.setOpaque(false);
        playerTypeBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                JComponent result = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                result.setOpaque(false);
                return result;
            }
        });
        playerTypeBox.setFont(new Font("Verdana", Font.BOLD, 14));
        playerTypeBox.setForeground(Color.red);

        observerButton.setFont(new Font("Verdana", Font.BOLD, 14));
        observerButton.setForeground(Color.WHITE);
        observerButton.setEnabled(false);
        observerButton.setOpaque(false);
        observerButton.setContentAreaFilled(false);
        observerButton.setBorderPainted(true);
    }

    /**
     * Main Game:
     */
    public BattleshipMain(String currentPlayer, GenericValues playerValues) {
        this.playerValues = playerValues;
        masterFrame.setSize(this.playerValues.getFrameWidth(), this.playerValues.getFrameHeight());
        masterFrame.setResizable(false);
        masterFrame.setVisible(true);
        masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        masterFrame.setLocationRelativeTo(null);
        masterFrame.setLayout(new GridLayout(3, 1, 0, 5));
        masterFrame.setBackground(Color.WHITE);
        masterFrame.add(upPanel);
        upPanel.setBackground(Color.WHITE);
        masterFrame.add(decorPanel);
        decorPanel.setBackground(Color.WHITE);
        masterFrame.add(downPanel);
        downPanel.setBackground(Color.WHITE);
        upPanel.setLayout(new BorderLayout(10, 0));
        upPanel.add(enemyBoard, BorderLayout.CENTER);
        enemyBoard.setLayout(new GridLayout(this.playerValues.getGridRows(), this.playerValues.getGridColumns()));
        downPanel.setLayout(new BorderLayout(10, 0));
        downPanel.add(allyBoard, BorderLayout.CENTER);
        allyBoard.setLayout(new GridLayout(this.playerValues.getGridRows(), this.playerValues.getGridColumns()));
        allyShipsListPanel = new JPanel(new GridLayout(playerValues.getShipListRows(), playerValues.getShipListColumns()));
        enemyShipsListPanel = new JPanel(new GridLayout(playerValues.getShipListRows(), playerValues.getShipListColumns()));
        downPanel.add(allyShipsListPanel, BorderLayout.EAST);
        upPanel.add(enemyShipsListPanel, BorderLayout.WEST);
        masterFrame.validate();

        gameControl = new GameControl(playerValues);

        try {
            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
            for (int i = 0; i < this.playerValues.getGridRows() * this.playerValues.getGridColumns(); i++) {
                GenericBlock enemySeaBlock = (GenericBlock) tempClass.newInstance();
                enemySeaBlock.addMouseListener(gameControl);
                enemyBoard.add(enemySeaBlock);
                GenericBlock mySeaBlock = (GenericBlock) tempClass.newInstance();
                mySeaBlock.addMouseListener(gameControl);
                allyBoard.add(mySeaBlock);
            }

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Label");
            GenericLabel decorLabel = (GenericLabel) tempClass.newInstance();
            decorPanel.add(decorLabel);
            Constructor playerLabelsConstructor = tempClass.getConstructor(boolean.class);
            GenericLabel enemyPlayerLabel = (GenericLabel) playerLabelsConstructor.newInstance(false);
            GenericLabel playerLabel = (GenericLabel) playerLabelsConstructor.newInstance(true);

            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Block");
            Constructor tempShipConstructor = tempClass.getConstructor(int.class, boolean.class);
            enemyShipsListPanel.add(enemyPlayerLabel);
            allyShipsListPanel.add(playerLabel);
            for (int i = 0; i < playerValues.getShipListSize() - 1; i++) {
                GenericBlock enemyWarship = (GenericBlock) tempShipConstructor.newInstance(i, false);
                enemyWarship.addMouseListener(gameControl);
                enemyShipsListPanel.add(enemyWarship);
                GenericBlock myWarship = (GenericBlock) tempShipConstructor.newInstance(i, true);
                myWarship.addMouseListener(gameControl);
                allyShipsListPanel.add(myWarship);
            }

            gameControl.setLateValues(enemyBoard, allyBoard);

            int portNumber = 1501;
            String host = "localhost";
            System.out.print("Connect with " + host + " in port " + portNumber + ": ");
            try {
                clientSocket = new Socket(host, portNumber);
                System.out.println("Connected");
                gameControl.setSocket(clientSocket);
                (new Thread(gameControl)).start();
            } catch (UnknownHostException ex) {
                Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
        }

        masterFrame.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox playerTypeBox = (JComboBox) e.getSource();
        currentPlayer = (String) playerTypeBox.getSelectedItem();
        startingFrame.dispose();
        try {
            tempClass = Class.forName("gr.epp.thesis." + currentPlayer + "Values");
            playerValues = (GenericValues) tempClass.newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChildBlock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread thread = new Thread(new BattleshipMain(currentPlayer, playerValues));
        thread.start();
    }

    @Override
    public void run() {
        while (!this.gameControl.isGameStarted()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(BattleshipMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Temporary
        for (int i = 0; i < allyBoard.getComponentCount(); i++) {
            allyBoard.getComponent(i).removeMouseListener(gameControl);
        }
        for (int i = 0; i < allyShipsListPanel.getComponentCount(); i++) {
            //myShipsList.getComponent(i).removeMouseListener(gameControl);
        }
        System.out.println("Starting Game...");
    }

    public static void main(String[] args) {
        BattleshipMain entryPoint = new BattleshipMain();
    }
}
