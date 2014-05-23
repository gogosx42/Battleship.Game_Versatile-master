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
public class AdultValues extends GenericValues {

    /**
     * Values defined for the Adult Player.
     */
    public AdultValues() {
        this.gridRows = 10;
        this.gridColumns = 10;
        this.frameWidth = 450;
        this.frameHeight = 900;
        this.shipsNumber = 5;
        this.seaColor = Color.CYAN;
        this.alliesShipListBackgroundColor = Color.LIGHT_GRAY;
        this.enemyShipListBackgroundColor = Color.DARK_GRAY;
        this.alliesShipListBorder = new LineBorder(Color.GREEN.darker(), 1, false);
        this.enemyShipListBorder = new LineBorder(Color.RED.darker(), 1, false);
        this.waterIcon = new ImageIcon("graphics/water.gif");
        this.decorIcon = new ImageIcon("graphics/adultDecorLabel.png");
        this.alliesBanner = new ImageIcon("graphics/myFleetAdultIcon.png");
        this.enemyBanner = new ImageIcon("graphics/enemyFleetAdultIcon.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.targetIcon = toolkit.getImage("graphics/target.gif");
        this.successfulShotIcon = new ImageIcon("graphics/fire.gif");
        this.missedShotIcon = new ImageIcon("graphics/miss.gif");
        this.alliesShipsIcons = new ArrayList<>();
        for (int i = 0; i < shipsNumber; i++) {
            this.alliesShipsIcons.add(new ImageIcon("graphics/modernWarship" + i + ".gif"));
        }
        this.enemyShipsIcons = new ArrayList<>();
        this.enemyShipsIcons = alliesShipsIcons;
        this.shipBlocksTotalNumber = 15;
        this.shipListSize = 6;
        this.shipListRows = 6;
        this.shipListColumns = 1;
    }

    @Override
    public int getBlocksTotalNumber() {
        return this.gridRows * this.gridColumns;
    }
}