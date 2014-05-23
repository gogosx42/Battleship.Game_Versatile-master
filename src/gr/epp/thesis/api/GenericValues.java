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
    protected int gridRows;
    protected int gridColumns;
    protected int frameWidth;
    protected int frameHeight;
    protected int BlocksTotalNumber; // Number of blocks in the grid available for ship placement
    protected int shipsNumber;  // Number of ships available
    protected Color seaColor;
    protected Color alliesShipListBackgroundColor;
    protected Color enemyShipListBackgroundColor;
    protected LineBorder alliesShipListBorder;
    protected LineBorder enemyShipListBorder;
    protected ImageIcon waterIcon;
    protected ImageIcon decorIcon;
    protected ImageIcon alliesBanner;
    protected ImageIcon enemyBanner;
    protected Toolkit toolkit;
    protected Image targetIcon;
    protected ImageIcon successfulShotIcon;
    protected ImageIcon missedShotIcon;
    protected ArrayList<ImageIcon> alliesShipsIcons;
    protected ArrayList<ImageIcon> enemyShipsIcons;
    protected int shipBlocksTotalNumber;
    protected int shipListSize;
    protected int shipListRows;
    protected int shipListColumns;

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

    public void setGridColumns(int gridColumns) {
        this.gridColumns = gridColumns;
    }

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

    public Color getAlliesShipListBackgroundColor() {
        return alliesShipListBackgroundColor;
    }

    public void setAlliesShipListBackgroundColor(Color alliesShipListBackgroundColor) {
        this.alliesShipListBackgroundColor = alliesShipListBackgroundColor;
    }

    public Color getEnemyShipListBackgroundColor() {
        return enemyShipListBackgroundColor;
    }

    public void setEnemyShipListBackgroundColor(Color enemyShipListBackgroundColor) {
        this.enemyShipListBackgroundColor = enemyShipListBackgroundColor;
    }

    public LineBorder getAlliesShipListBorder() {
        return alliesShipListBorder;
    }

    public void setAlliesShipListBorder(LineBorder alliesShipListBorder) {
        this.alliesShipListBorder = alliesShipListBorder;
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

    public ImageIcon getAlliesBanner() {
        return alliesBanner;
    }

    public void setAlliesBanner(ImageIcon alliesBanner) {
        this.alliesBanner = alliesBanner;
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

    public Image getTarget() {
        return targetIcon;
    }

    public void setTarget(Image target) {
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

    public ArrayList<ImageIcon> getMyWarships() {
        return alliesShipsIcons;
    }

    public void setMyWarships(ArrayList<ImageIcon> myWarships) {
        this.alliesShipsIcons = myWarships;
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
