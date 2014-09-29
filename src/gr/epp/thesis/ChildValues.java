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
public class ChildValues extends GenericValues {

    /**
     * Values defined for the Adult Player.
     */
    public ChildValues() {
        super(10, 10);

        this.setShipsNumber(9);
        this.setFrameWidth(450);
        this.setFrameHeight(900);
        this.setDecorIcon(new ImageIcon("graphics/childDecorLabel.png"));
        this.setAllyBanner(new ImageIcon("graphics/childMyListMotherShip.png"));
        this.setEnemyBanner(new ImageIcon("graphics/childEnemyListMotherShip.png"));
        this.setAllyShipsIcons(new ArrayList<ImageIcon>());
        this.getAllyShipsIcons().add(new ImageIcon("graphics/childMyListShip.png"));
        this.setEnemyShipsIcons(new ArrayList<ImageIcon>());
        this.getEnemyShipsIcons().add(new ImageIcon("graphics/childEnemyListShip.png"));
        setShipBlocksTotalNumber(9);
        setShipListSize(10);
        setShipListRows(5);
        setShipListColumns(2);
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
        return this.getGridRows() * this.getGridColumns();
    }

    @Override
    public void setGridColumns(int gridColumns) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
