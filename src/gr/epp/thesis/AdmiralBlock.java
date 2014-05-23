package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete
 */
public class AdmiralBlock extends GenericBlock {

    public AdmiralBlock() {
        super();
    }

    public AdmiralBlock(int index, boolean ownShipsList) {
        super(index, ownShipsList);
    }

    /**
     * Initializing Admiral player's values.
     */
    @Override
    public void initializeValues() {
        this.values = new AdmiralValues();
    }

    /**
     * Initializing the Grid. Preparing seaBlocks. Background Color & sea water
     * Icon.
     */
    @Override
    public void initializeGridBlocks() {
        setBackground(values.getSeaColor());
        setIcon(values.getWaterIcon());
    }

    /**
     * Initializing the ships list. Preparing available warships.
     */
    @Override
    public void initializeShipsList() {
        if (this.player) {
            setBorder(values.getAllyShipListBorder());
            setBackground(values.getAllyShipListBackgroundColor());
            setOnShipsList(true);
            switch (this.index) {
                case (0):
                    setIcon(values.getMyWarships().get(4));
                    setTotalBlocks(5);
                    break;
                case (1):
                    setIcon(values.getMyWarships().get(3));
                    setTotalBlocks(4);
                    break;
                case (2):
                    setIcon(values.getMyWarships().get(3));
                    setTotalBlocks(4);
                    break;
                case (3):
                    setIcon(values.getMyWarships().get(2));
                    setTotalBlocks(3);
                    break;
                case (4):
                    setIcon(values.getMyWarships().get(2));
                    setTotalBlocks(3);
                    break;
                case (5):
                    setIcon(values.getMyWarships().get(2));
                    setTotalBlocks(3);
                    break;
                case (6):
                    setIcon(values.getMyWarships().get(1));
                    setTotalBlocks(2);
                    break;
                case (7):
                    setIcon(values.getMyWarships().get(1));
                    setTotalBlocks(2);
                    break;
                case (8):
                    setIcon(values.getMyWarships().get(0));
                    setTotalBlocks(1);
                    break;
                case (9):
                    setIcon(values.getMyWarships().get(0));
                    setTotalBlocks(1);
                    break;
                case (10):
                    setIcon(values.getMyWarships().get(0));
                    setTotalBlocks(1);
                    break;
            }
        } else {
            setBorder(values.getEnemyShipListBorder());
            setBackground(values.getEnemyShipListBackgroundColor());
            switch (index) {
                case (0):
                    setIcon(values.getEnemyShipsIcons().get(4));
                    break;
                case (1):
                    setIcon(values.getEnemyShipsIcons().get(3));
                    break;
                case (2):
                    setIcon(values.getEnemyShipsIcons().get(3));
                    break;
                case (3):
                    setIcon(values.getEnemyShipsIcons().get(2));
                    break;
                case (4):
                    setIcon(values.getEnemyShipsIcons().get(2));
                    break;
                case (5):
                    setIcon(values.getEnemyShipsIcons().get(2));
                    break;
                case (6):
                    setIcon(values.getEnemyShipsIcons().get(1));
                    break;
                case (7):
                    setIcon(values.getEnemyShipsIcons().get(1));
                    break;
                case (8):
                    setIcon(values.getEnemyShipsIcons().get(0));
                    break;
                case (9):
                    setIcon(values.getEnemyShipsIcons().get(0));
                    break;
                case (10):
                    setIcon(values.getEnemyShipsIcons().get(0));
                    break;
            }
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
