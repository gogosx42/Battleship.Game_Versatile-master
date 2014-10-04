package gr.epp.thesis.api;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete *
 */
public abstract class GenericValues {

    /**
     * Values defined for a Player.
     */
    private int gridRows;
    private int gridColumns;
    private int frameWidth;
    private int frameHeight;
    private int BlocksTotalNumber; // Number of blocks in the grid available for ship placement
    private int shipsNumber;  // Number of ships available
    private Color seaColor;
    private Color allyShipListBackgroundColor;
    private Color enemyShipListBackgroundColor;
    private LineBorder allyShipListBorder;
    private LineBorder enemyShipListBorder;
    private ImageIcon waterIcon;
    private ImageIcon decorIcon;
    private ImageIcon allyBanner;
    private ImageIcon enemyBanner;
    private Toolkit toolkit;
    private Image targetIcon;
    private ImageIcon successfulShotIcon;
    private ImageIcon missedShotIcon;
    private ArrayList<ImageIcon> allyShipsIcons;
    private ArrayList<ImageIcon> enemyShipsIcons;
    private int shipBlocksTotalNumber;
    private int shipListSize;
    private int shipListRows;
    private int shipListColumns;

    public GenericValues(int gridRows, int gridColumns) {
        this.frameWidth = 525;
        this.frameHeight = 1050;
        this.toolkit = Toolkit.getDefaultToolkit();
        this.gridRows = gridRows;
        this.gridColumns = gridColumns;
        this.seaColor = Color.CYAN.darker();
        this.allyShipListBackgroundColor = Color.LIGHT_GRAY.darker();
        this.enemyShipListBackgroundColor = Color.DARK_GRAY.darker();
        this.allyShipListBorder = new LineBorder(Color.GREEN.darker(), 1, false);
        this.enemyShipListBorder = new LineBorder(Color.RED.darker(), 1, false);
        this.waterIcon = new ImageIcon("graphics/water.gif");
        this.targetIcon = this.toolkit.getImage("graphics/sword.gif");
        this.successfulShotIcon = new ImageIcon("graphics/fire.gif");
        this.missedShotIcon = new ImageIcon("graphics/miss.gif");
    }

    /**
     * Method needed for the placement of warships on the grid. Every
     * orientation.
     *
     * @param shipBlocksNumber Number of blocks of the current ship
     * @param currentBlock
     * @param orientation 3 - horizontal, 6 - Vertical
     * @param sinked
     * @return
     */
    public ImageIcon getGridPieces(int shipBlocksNumber, int currentBlock, int orientation, boolean sinked) {
        if (sinked) {
            System.out.println("SINK!");
            return (new ImageIcon("graphics/gridPieces/Sink_" + shipBlocksNumber + "_" + currentBlock + "_" + orientation + ".gif"));
        } else {
            return (new ImageIcon("graphics/gridPieces/" + shipBlocksNumber + "_" + currentBlock + "_" + orientation + ".gif"));
        }
    }

    //Getters & Setters: 
    public int getGridRows() {
        return gridRows;
    }

    public void setGridRows(int gridRows) {
        this.gridRows = gridRows;
    }

    public int getGridColumns() {
        return gridColumns;
    }

    public abstract void setGridColumns(int gridColumns);

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public int getBlocksTotalNumber() {
        return BlocksTotalNumber;
    }

    public void setBlocksTotalNumber(int BlocksTotalNumber) {
        this.BlocksTotalNumber = BlocksTotalNumber;
    }

    public int getShipsNumber() {
        return shipsNumber;
    }

    public void setShipsNumber(int ShipNumber) {
        this.shipsNumber = ShipNumber;
    }

    public Color getSeaColor() {
        return seaColor;
    }

    public void setSeaColor(Color seaColor) {
        this.seaColor = seaColor;
    }

    public Color getAllyShipListBackgroundColor() {
        return allyShipListBackgroundColor;
    }

    public void setAllyShipListBackgroundColor(Color allyShipListBackgroundColor) {
        this.allyShipListBackgroundColor = allyShipListBackgroundColor;
    }

    public Color getEnemyShipListBackgroundColor() {
        return enemyShipListBackgroundColor;
    }

    public void setEnemyShipListBackgroundColor(Color enemyShipListBackgroundColor) {
        this.enemyShipListBackgroundColor = enemyShipListBackgroundColor;
    }

    public LineBorder getAllyShipListBorder() {
        return allyShipListBorder;
    }

    public void setAllyShipListBorder(LineBorder allyShipListBorder) {
        this.allyShipListBorder = allyShipListBorder;
    }

    public LineBorder getEnemyShipListBorder() {
        return enemyShipListBorder;
    }

    public void setEnemyShipListBorder(LineBorder enemyShipListBorder) {
        this.enemyShipListBorder = enemyShipListBorder;
    }

    public ImageIcon getWaterIcon() {
        return waterIcon;
    }

    public void setWaterIcon(ImageIcon waterIcon) {
        this.waterIcon = waterIcon;
    }

    public ImageIcon getDecorIcon() {
        return decorIcon;
    }

    public void setDecorIcon(ImageIcon decorIcon) {
        this.decorIcon = decorIcon;
    }

    public ImageIcon getAllyBanner() {
        return allyBanner;
    }

    public void setAllyBanner(ImageIcon allyBanner) {
        this.allyBanner = allyBanner;
    }

    public ImageIcon getEnemyBanner() {
        return enemyBanner;
    }

    public void setEnemyBanner(ImageIcon enemyBanner) {
        this.enemyBanner = enemyBanner;
    }

    public Toolkit getToolkit() {
        return toolkit;
    }

    public void setToolkit(Toolkit toolkit) {
        this.toolkit = toolkit;
    }

    public Image getTargetIcon() {
        return targetIcon;
    }

    public void setTargetIcon(Image target) {
        this.targetIcon = target;
    }

    public ImageIcon getSuccessfulShotIcon() {
        return successfulShotIcon;
    }

    public void setSuccessfulShotIcon(ImageIcon successfulShotIcon) {
        this.successfulShotIcon = successfulShotIcon;
    }

    public ImageIcon getMissedShotIcon() {
        return missedShotIcon;
    }

    public void setMissedShotIcon(ImageIcon missedShotIcon) {
        this.missedShotIcon = missedShotIcon;
    }

    public ArrayList<ImageIcon> getAllyShipsIcons() {
        return allyShipsIcons;
    }

    public void setAllyShipsIcons(ArrayList<ImageIcon> myWarships) {
        this.allyShipsIcons = myWarships;
    }

    public ArrayList<ImageIcon> getEnemyShipsIcons() {
        return enemyShipsIcons;
    }

    public void setEnemyShipsIcons(ArrayList<ImageIcon> enemyShipsIcons) {
        this.enemyShipsIcons = enemyShipsIcons;
    }

    public int getShipBlocksTotalNumber() {
        return shipBlocksTotalNumber;
    }

    public void setShipBlocksTotalNumber(int shipBlocksTotalNumber) {
        this.shipBlocksTotalNumber = shipBlocksTotalNumber;
    }

    public int getShipListSize() {
        return shipListSize;
    }

    public void setShipListSize(int shipListSize) {
        this.shipListSize = shipListSize;
    }

    public int getShipListRows() {
        return shipListRows;
    }

    public void setShipListRows(int shipListRows) {
        this.shipListRows = shipListRows;
    }

    public int getShipListColumns() {
        return shipListColumns;
    }

    public void setShipListColumns(int shipListColumns) {
        this.shipListColumns = shipListColumns;
    }
}
