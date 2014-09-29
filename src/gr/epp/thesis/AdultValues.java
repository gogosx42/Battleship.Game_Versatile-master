package gr.epp.thesis;

import gr.epp.thesis.api.GenericValues;
import java.util.ArrayList;
import javax.swing.ImageIcon;

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
        super(10, 10);
        this.setShipsNumber(5);
        this.setFrameWidth(450);
        this.setFrameHeight(900);
        this.setDecorIcon(new ImageIcon("graphics/adultDecorLabel.png"));
        this.setAllyBanner(new ImageIcon("graphics/myFleetAdultIcon.png"));
        this.setEnemyBanner(new ImageIcon("graphics/enemyFleetAdultIcon.png"));
        this.setAllyShipsIcons(new ArrayList<ImageIcon>());
        for (int i = 0; i < this.getShipsNumber(); i++) {
            this.getAllyShipsIcons().add(new ImageIcon("graphics/modernWarship" + i + ".gif"));
        }
        this.setEnemyShipsIcons(new ArrayList<ImageIcon>());
        this.setEnemyShipsIcons(this.getAllyShipsIcons());
        setShipBlocksTotalNumber(15);
        setShipListSize(6);
        setShipListRows(6);
        setShipListColumns(1);
    }

    @Override
    public int getBlocksTotalNumber() {
        return this.getGridRows() * this.getGridColumns();
    }

    @Override
    public void setGridColumns(int gridColumns) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
