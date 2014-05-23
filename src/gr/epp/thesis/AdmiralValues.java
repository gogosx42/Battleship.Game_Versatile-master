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
 * @institute Applied Informatics and Multimedia - TEI of Crete
 */
public class AdmiralValues extends GenericValues {

    /**
     * Values defined for the Adult Player.
     */
    public AdmiralValues() {
        this.gridRows = 15;
        this.gridColumns = 15;
        this.frameWidth = 525;
        this.frameHeight = 1050;
        this.shipsNumber = 10;
        this.seaColor = Color.CYAN.darker();
        this.alliesShipListBackgroundColor = Color.LIGHT_GRAY.darker();
        this.enemyShipListBackgroundColor = Color.DARK_GRAY.darker();
        this.alliesShipListBorder = new LineBorder(Color.GREEN.darker(), 1, false);
        this.enemyShipListBorder = new LineBorder(Color.RED.darker(), 1, false);
        this.waterIcon = new ImageIcon("graphics/water.gif");
        this.decorIcon = new ImageIcon("graphics/admiralDecorLabel.png");
        this.alliesBanner = new ImageIcon("graphics/myFleetAdmiralIcon.png");
        this.enemyBanner = new ImageIcon("graphics/enemyFleetAdmiralIcon.png");
        this.toolkit = Toolkit.getDefaultToolkit();
        this.targetIcon = toolkit.getImage("graphics/sword.gif");
        this.successfulShotIcon = new ImageIcon("graphics/fire.gif");
        this.missedShotIcon = new ImageIcon("graphics/miss.gif");
        this.alliesShipsIcons = new ArrayList<>();
        for (int i = 0; i < shipsNumber; i++) {
            this.alliesShipsIcons.add(new ImageIcon("graphics/modernWarship" + i + ".gif"));
        }
        this.enemyShipsIcons = new ArrayList<>();
        this.enemyShipsIcons = alliesShipsIcons;
        this.shipBlocksTotalNumber = 28;
        this.shipListSize = 11;
        this.shipListRows = 11;
        this.shipListColumns = 1;
    }

    @Override
    public int getBlocksTotalNumber() {
        return this.gridRows * this.gridColumns;
    }
}