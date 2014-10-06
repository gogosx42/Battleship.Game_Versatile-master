package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;
import gr.epp.thesis.api.GenericValues;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @inst. Applied Informatics and Multimedia - TEI of Crete
 */
public class GameControl implements MouseListener, Runnable {

    private GenericValues playerValues;
    private int gridRows = 0;
    private int gridColumns = 0;
    private int coords[] = new int[3];
    private int orientation = 3;
    private int shipBlocksNumber = 0;
    private JPanel enemyBoard = null;
    private JPanel alliesBoard = null;
    private JPanel enemyShipsListPanel = null;
    private boolean horizontal = true;
    private GenericBlock currentWarship;
    private ArrayList<GenericBlock> warshipBlocksList;
    private ArrayList<GenericBlock> hittenBlocks;
    private int shipBlocksTotalNumber = 0;
    private int warshipBlocksHold[];
    private Color seaColor = null;
    private ImageIcon waterIcon = null;
    private ImageIcon successfulShotIcon = null;
    private ImageIcon missedShotIcon = null;
    private Point cursorHotSpot = new Point(10, 10);
    private Socket clientSocket = null;
    private boolean gameStarted = false;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean locked = false;
    private static int successfulHits = 0;
    private static int totalHits = 0;
    private static int l = 0;
    private String currentPlayerType;
    private String fleetCoordinates;

    private int a, b, c, d, tempId2 = 0;

    public GameControl(GenericValues playerValues, Socket clientScoket, String currentPlayerType) {
        this.playerValues = playerValues;               //Current player values.
        this.gridRows = playerValues.getGridRows();
        this.gridColumns = playerValues.getGridColumns();
        this.shipBlocksTotalNumber = playerValues.getShipBlocksTotalNumber();
        this.seaColor = playerValues.getSeaColor();
        this.waterIcon = playerValues.getWaterIcon();
        this.successfulShotIcon = playerValues.getSuccessfulShotIcon();
        this.missedShotIcon = playerValues.getMissedShotIcon();
        this.warshipBlocksList = new ArrayList<>();
        this.hittenBlocks = new ArrayList<>();
        this.clientSocket = clientScoket;
        this.currentPlayerType = currentPlayerType;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("notify:" + currentPlayerType);
    }

    /**
     * Values needed to continue the game.
     */
    public void setLateValues(JPanel enemyBoard, JPanel myBoard, JPanel enemyShipsListPanel) {
        this.enemyBoard = enemyBoard;
        this.alliesBoard = myBoard;
        this.enemyShipsListPanel = enemyShipsListPanel;
        activateBoard(enemyBoard, false);
    }

    /**
     * A method that activates/deactivates enemy's and player's board
     * components, so that you can't fire if it's not your turn, or you haven't
     * place your warships already. If it's your turn to fire, enemy's
     * components reactivating. This method also, sets a disable icon on the
     * blocks, and removes the gameConstrol Listener.
     */
    public void activateBoard(JPanel board, boolean activate) {
        for (int i = 0; i < playerValues.getBlocksTotalNumber(); i++) {
            GenericBlock tempBlock = (GenericBlock) board.getComponent(i);
            tempBlock.setEnabled(activate);
            if (activate) {
                tempBlock.addMouseListener(this);
                tempBlock.setIcon(waterIcon);
            } else {
                tempBlock.removeMouseListener(this);
                tempBlock.setDisabledIcon(waterIcon);
            }
        }
    }

    /**
     * A method that instantly, gives each seaBlock unique coordinates, on the
     * grid.
     */
    public void getBlockPosition(GenericBlock block) {
        JPanel currentParent = (JPanel) block.getParent();
        for (int i = 0; i < currentParent.getComponentCount(); i++) {
            if (currentParent.getComponent(i) == block) {
                coords[0] = i / gridRows; // rows, columns?
                coords[1] = i % gridColumns; // rows, columns?
                coords[2] = i; // BlockPosition
                break;
            }
        }
    }

    /**
     * A method to create the shipBlocks on alliesBoard panel. This method adds
     * icons for every ship in every orientation. Later deactivates the
     * positioned warship on the list.
     */
    public void warshipBlockOnGrid(GenericBlock warshipBlock, int currentBlock) {
        this.l = 0;
        warshipBlock.setIcon(playerValues.getGridPieces(shipBlocksNumber, currentBlock, orientation, false));
        System.out.println("" + warshipBlock.getName());
        System.out.println(warshipBlock.getIndex());
        
        
        warshipBlock.setBackground(seaColor);
        warshipBlock.setWarshipBlockOnGrid(true);
        currentWarship.setEnabled(false);
        warshipBlocksList.add(currentWarship);
        for (int i = 0; i < alliesBoard.getComponentCount(); i++) {
            if (alliesBoard.getComponent(i) == warshipBlock) {
                this.warshipBlocksHold[l] = i;
                l++;
            }
        }
    }

    /**
     * A method for collision detection. This method prevents the installation
     * of another warship, that collides on other ones. Values of 3 & 6, about
     * orientation means orientation of the warship clockwise.
     */
    public boolean checkCollision() {
        boolean freeArea = true;
        switch (orientation) {
            case (3):
                for (int i = coords[2]; i < (coords[2] + shipBlocksNumber); i++) {
                    GenericBlock tempBlock = (GenericBlock) alliesBoard.getComponent(i);
                    if (tempBlock.isWarshipBlockOnGrid()) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
            case (6):
                for (int i = coords[2]; i < (coords[2] + (gridRows * shipBlocksNumber)); i = i + gridRows) {
                    GenericBlock tempBlock = (GenericBlock) alliesBoard.getComponent(i);
                    if (tempBlock.isWarshipBlockOnGrid()) {
                        freeArea = false;
                        break;
                    }
                }
                return freeArea;
        }
        return false;
    }

    /**
     * A method that contains all interactions with the grid. Those are,
     * Hovering on seaBlocks, Exiting from seaBlocks hover, and Capturing. This
     * method needs the orientation of the ship placement, and also two
     * arguments for the behavior of the warship before the capture. If Hovering
     * is true, then a ship is above buttons. If Exiting is true, then the
     * SeaBlock, has to repaint itself because the warship is elsewhere. Values
     * of 3 & 6, about orientation means orientation of the warship clockwise.
     *
     * @param hovering Ship is hovering over blocks
     * @param exiting
     */
    public void battleFormations(boolean hovering, boolean exiting) {
        int currentBlock = 0;
        switch (orientation) {
            case (3):
                for (int i = 0; i < shipBlocksNumber; i++) {
                    GenericBlock tempSeaBlock = (GenericBlock) alliesBoard.getComponent(coords[2] + i);
                    if (hovering) {
                        tempSeaBlock.setIcon(null);
                        tempSeaBlock.setBackground(Color.GREEN);
                    } else if (exiting) {
                        tempSeaBlock.setIcon(waterIcon);
                        tempSeaBlock.setBackground(seaColor);
                    } else {
                        tempSeaBlock.setName("" + shipBlocksNumber + currentBlock + orientation);
                        warshipBlockOnGrid(tempSeaBlock, currentBlock);
                    }
                    currentBlock++;
                }
                break;
            case (6):
                for (int i = 0; i < shipBlocksNumber; i++) {
                    GenericBlock tempSeaBlock = (GenericBlock) alliesBoard.getComponent(coords[2] + (i * gridRows));
                    if (hovering) {
                        tempSeaBlock.setIcon(null);
                        tempSeaBlock.setBackground(Color.GREEN);
                    } else if (exiting) {
                        tempSeaBlock.setIcon(waterIcon);
                        tempSeaBlock.setBackground(seaColor);
                    } else {
                        tempSeaBlock.setName("" + shipBlocksNumber + currentBlock + orientation);
                        warshipBlockOnGrid(tempSeaBlock, currentBlock);
                    }
                    currentBlock++;
                }
                break;
        }
    }

    public void initiateGame() {
        if (warshipBlocksList.size() == playerValues.getShipBlocksTotalNumber()) {
            //Start the game session here...
            scanBoard();
            activateBoard(enemyBoard, true);
            gameStarted = true;
            System.out.println("GAMESTARTED");
        }
    }

    public void setSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * On run method, gameControl class receives enemy's successfulShotIcons,
     * and responds if they were successful or missedShotIconed.
     */
    @Override
    public void run() {
        try {
//            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                String incomingMessage = in.readLine();
                String[] splitMessage = incomingMessage.split(":");
                int hitBlock = Integer.parseInt(splitMessage[1]);

                if (splitMessage[0].equals("hit")) {
                    GenericBlock playerHitBlock = (GenericBlock) alliesBoard.getComponent(hitBlock);
                    if (playerHitBlock.isWarshipBlockOnGrid()) {
                        out.println("success:" + hitBlock);
                        System.out.println(playerHitBlock.getWarshipName());

//                        playerHitBlock.setIcon("Sink"+playerHitBlock.getWarshipName()+".gif");
                        playerHitBlock.setIcon(successfulShotIcon);
//                        checkDestruction(playerHitBlock, hitBlock);
                    } else {
                        out.println("missed:" + hitBlock);
                        playerHitBlock.setIcon(missedShotIcon);
                    }
                    
//                    out.println(refreshObservers:);
                    locked = false;
                } else {
                    GenericBlock enemyHitBlock = (GenericBlock) enemyBoard.getComponent(hitBlock);
                    if (splitMessage[0].equals("missed")) {
                        enemyHitBlock.setIcon(missedShotIcon);
                    } else {
                        enemyHitBlock.setIcon(successfulShotIcon);
                        successfulHits++;
                    }
                    System.out.println("Accuracy: " + successfulHits + "/" + totalHits);
                }

                System.out.println(incomingMessage);
            }
        } catch (IOException ex) {
            Logger.getLogger(GameControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Handles all click interactions. Also changes orientation if right button
     * is clicked.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        GenericBlock clickedBlock = (GenericBlock) e.getSource();
        if (gameStarted && !locked) {
            getBlockPosition(clickedBlock);
            out.println("hit:" + coords[2]);
            totalHits++;
            locked = true;
        }
        if (clickedBlock.isOnShipsList()) {
            currentWarship = clickedBlock;
            shipBlocksNumber = clickedBlock.getTotalBlocks();
            this.warshipBlocksHold = new int[shipBlocksNumber];
        } else {
            if (e.getButton() == MouseEvent.BUTTON3) {
                mouseExited(e);
                horizontal ^= true;
                if (horizontal) {
                    orientation = 3;
                    mouseEntered(e);
                } else {
                    orientation = 6;
                    mouseEntered(e);
                }
                alliesBoard.validate();
            } else {
                switch (orientation) {
                    case (3):
                        if (coords[1] < (gridColumns - (shipBlocksNumber - 1)) && !warshipBlocksList.contains(currentWarship)) {
                            if (checkCollision()) {
                                battleFormations(false, false);
                                initiateGame();
                            }
                        }
                        break;
                    case (6):
                        if (coords[0] < gridRows - (shipBlocksNumber - 1) && !warshipBlocksList.contains(currentWarship)) {
                            if (checkCollision()) {
                                battleFormations(false, false);
                                initiateGame();
                            }
                        }
                        break;
                }
                alliesBoard.validate();
            }
        }

    }

    /**
     * Just for quicker ship selection.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        GenericBlock pressedBlock = (GenericBlock) e.getSource();
        if (pressedBlock.isOnShipsList()) {
            mouseClicked(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Code about the Hover effect. On Mouse Enter, and with the proper
     * orientation, the grid paints so many SeaBlocks as many as the number of
     * ShipsBlocks.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        GenericBlock enteredBlock = (GenericBlock) e.getSource();
        if (!gameStarted) {
            if (!enteredBlock.isOnShipsList()) {
                getBlockPosition((GenericBlock) e.getSource());
                switch (orientation) {
                    case (3):
                        if (coords[1] < (gridColumns - (shipBlocksNumber - 1)) && !warshipBlocksList.contains(currentWarship)) {
                            if (checkCollision()) {
                                battleFormations(true, false);
                            }
                        }
                        break;
                    case (6):
                        if (coords[0] < gridRows - (shipBlocksNumber - 1) && !warshipBlocksList.contains(currentWarship)) {
                            if (checkCollision()) {
                                battleFormations(true, false);
                            }
                        }
                        break;
                }
                alliesBoard.validate();
            }
        }
        if (enteredBlock.getParent().equals(enemyBoard)) {
            Cursor targetCursor = playerValues.getToolkit().createCustomCursor(playerValues.getTargetIcon(), cursorHotSpot, "Cursor");
            enemyBoard.setCursor(targetCursor);
        }
    }

    /**
     * Code about the Exit Hover effect. On Mouse Exited, and with the proper
     * orientation, the grid has to paint each SeaBlock again.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (!gameStarted) {
            GenericBlock exitedBlock = (GenericBlock) e.getSource();
            if (!exitedBlock.isOnShipsList()) {
                getBlockPosition((GenericBlock) e.getSource());
                switch (orientation) {
                    case (3):
                        if (coords[1] < (gridColumns - (shipBlocksNumber - 1))) {
                            if (checkCollision()) {
                                battleFormations(false, true);
                            }
                        }
                        break;
                    case (6):
                        if (coords[0] < gridRows - (shipBlocksNumber - 1)) {
                            if (checkCollision()) {
                                battleFormations(false, true);
                            }
                        }
                        break;
                }
                alliesBoard.validate();
            }
        }
    }

    public void checkDestruction(GenericBlock hittenBlock, int boardIndex) {
        int tempId = Integer.parseInt(hittenBlock.getName());
        int tempOrient = tempId % 10;
        int tempCurrBlock = (tempId % 100) / 10;
        tempId = tempId / 100;

        hittenBlock.setIcon(playerValues.getGridPieces(tempId, tempCurrBlock, tempOrient, true));

        System.out.println(" " + boardIndex);
        JButton tmpb = (JButton) enemyBoard.getComponent(boardIndex);
        String oo;
        oo = tmpb.getName();
        tempId2 = Integer.parseInt(oo);
        System.out.println(" " + tempId2);
        //int tempId2 = Integer.parseInt(enemyBoard.getComponent(boardIndex).getName());
        switch (tempId2) {
            case (1):
                JButton temp = (JButton) enemyShipsListPanel.getComponent(5);
                temp.setIcon(new ImageIcon("graphics/modernWarship" + (tempId2 - 1) + "Sinked.gif"));
                break;
            case (2):
                a++;
                if (a == tempId2) {
                    JButton temp2 = (JButton) enemyShipsListPanel.getComponent(4);
                    temp2.setIcon(new ImageIcon("graphics/modernWarship" + (tempId2 - 1) + "Sinked.gif"));
                }
                break;
            case (3):
                b++;
                if (b == tempId2) {
                    JButton temp3 = (JButton) enemyShipsListPanel.getComponent(3);
                    temp3.setIcon(new ImageIcon("graphics/modernWarship" + (tempId2 - 1) + "Sinked.gif"));
                }
                break;
            case (4):
                c++;
                if (c == tempId2) {
                    JButton temp4 = (JButton) enemyShipsListPanel.getComponent(2);
                    temp4.setIcon(new ImageIcon("graphics/modernWarship" + (tempId2 - 1) + "Sinked.gif"));
                }
                break;
            case (5):
                d++;
                if (d == tempId2) {
                    JButton temp5 = (JButton) enemyShipsListPanel.getComponent(1);
                    temp5.setIcon(new ImageIcon("graphics/modernWarship" + (tempId2 - 1) + "Sinked.gif"));
                }
                break;
        }
        tempId2 = 0;
    }

    public String scanBoard() {
        for (int i = 0; i < alliesBoard.getComponentCount(); i++) {
            GenericBlock tempBlock = (GenericBlock) alliesBoard.getComponent(i);
            if (tempBlock.isWarshipBlockOnGrid()) {
                fleetCoordinates = tempBlock.getName();
                fleetCoordinates += ":" + tempBlock.getIndex();
            }
        }
        System.out.println(fleetCoordinates);
        return fleetCoordinates;
    }
}
