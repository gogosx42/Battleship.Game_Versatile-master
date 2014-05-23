package gr.epp.thesis;

import gr.epp.thesis.api.GenericValues;
import java.awt.Color;
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
public class ChildValues extends GenericValues {

    /**
     * Values defined for the Adult Player.
     */
    public ChildValues() {
        this.gridRows = 10;
        this.gridColumns = 10;
        this.frameWidth = 450;
        this.frameHeight = 900;
        this.shipsNumber = 9;
        this.seaColor = Color.CYAN;
        this.allyShipListBackgroundColor = Color.LIGHT_GRAY;
        this.enemyShipListBackgroundColor = Color.DARK_GRAY;
        this.allyShipListBorder = new LineBorder(Color.GREEN, 1, false);
        this.enemyShipListBorder = new LineBorder(Color.RED, 1, false);
        this.waterIcon = new ImageIcon("graphics/water.gif");
        this.decorIcon = new ImageIcon("graphics/childDecorLabel.png");
        this.allyBanner = new ImageIcon("graphics/childMyListMotherShip.png");
        this.enemyBanner = new ImageIcon("graphics/childEnemyListMotherShip.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.targetIcon = toolkit.getImage("graphics/target.gif");
        this.successfulShotIcon = new ImageIcon("graphics/fire.gif");
        this.missedShotIcon = new ImageIcon("graphics/miss.gif");
        this.allyShipsIcons = new ArrayList<>();
        this.allyShipsIcons.add(new ImageIcon("graphics/childMyListShip.png"));
        this.enemyShipsIcons = new ArrayList<>();
        this.enemyShipsIcons.add(new ImageIcon("graphics/childEnemyListShip.png"));
        this.shipBlocksTotalNumber = 9;
        this.shipListSize = 10;
        this.shipListRows = 5;
        this.shipListColumns = 2;
    }

    @Override
    public ImageIcon getGridPieces(int shipBlocks, int currentBlock, int orientation, boolean sinked) {
        if (sinked) {
            return (new ImageIcon("graphics/gridPieces/childMySinked.png"));
        } else {
            return (new ImageIcon("graphics/gridPieces/childGridShip.png"));
        }
    }

    @Override
    public int getBlocksTotalNumber() {
        return this.gridRows * this.gridColumns;
    }
}