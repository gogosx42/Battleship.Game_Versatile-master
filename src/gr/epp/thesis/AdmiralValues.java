package gr.epp.thesis;

import gr.epp.thesis.api.GenericValues;
import java.util.ArrayList;
import javax.swing.ImageIcon;

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
        super(10, 10);
        this.setShipsNumber(10);
        this.setFrameHeight(1050);
        this.setFrameWidth(525);
        
        this.setAllyShipsIcons(new ArrayList<ImageIcon>());
        for (int i = 0; i < this.getShipsNumber(); i++) {
            getAllyShipsIcons().add(new ImageIcon("graphics/modernWarship" + i + ".gif"));
        }
        this.setDecorIcon(new ImageIcon("graphics/admiralDecorLabel.png"));
        this.setAllyBanner(new ImageIcon("graphics/myFleetAdmiralIcon.png"));
        this.setEnemyBanner(new ImageIcon("graphics/enemyFleetAdmiralIcon.png"));
        setEnemyShipsIcons(new ArrayList<ImageIcon>());
        setEnemyShipsIcons(this.getAllyShipsIcons());
        setShipBlocksTotalNumber(28);
        setShipListSize(11);
        setShipListRows(11);
        setShipListColumns(1);
    }

    @Override
    public int getBlocksTotalNumber() {
        return this.getGridRows() * this.getGridColumns();
    }

    @Override
    public void setGridColumns(int gridColumns) {
        this.setGridColumns(15);
    }
}
