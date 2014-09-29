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
        this.setValues(new AdmiralValues());
    }

    /**
     * Initializing the Grid. Preparing seaBlocks. Background Color & sea water
     * Icon.
     */
    @Override
    public void initializeGridBlocks() {
        setBackground(getValues().getSeaColor());
        setIcon(getValues().getWaterIcon());
    }

    /**
     * Initializing the ships list. Preparing available warships.
     */
    @Override
    public void initializeShipsList() {
        if (this.isPlayer()) {
            setBorder(getValues().getAllyShipListBorder());
            setBackground(getValues().getAllyShipListBackgroundColor());
            setOnShipsList(true);
            switch (this.getIndex()) {
                case (0):
                    setIcon(getValues().getAllyShipsIcons().get(4));
                    setTotalBlocks(5);
                    break;
                case (1):
                    setIcon(getValues().getAllyShipsIcons().get(3));
                    setTotalBlocks(4);
                    break;
                case (2):
                    setIcon(getValues().getAllyShipsIcons().get(3));
                    setTotalBlocks(4);
                    break;
                case (3):
                    setIcon(getValues().getAllyShipsIcons().get(2));
                    setTotalBlocks(3);
                    break;
                case (4):
                    setIcon(getValues().getAllyShipsIcons().get(2));
                    setTotalBlocks(3);
                    break;
                case (5):
                    setIcon(getValues().getAllyShipsIcons().get(2));
                    setTotalBlocks(3);
                    break;
                case (6):
                    setIcon(getValues().getAllyShipsIcons().get(1));
                    setTotalBlocks(2);
                    break;
                case (7):
                    setIcon(getValues().getAllyShipsIcons().get(1));
                    setTotalBlocks(2);
                    break;
                case (8):
                    setIcon(getValues().getAllyShipsIcons().get(0));
                    setTotalBlocks(1);
                    break;
                case (9):
                    setIcon(getValues().getAllyShipsIcons().get(0));
                    setTotalBlocks(1);
                    break;
                case (10):
                    setIcon(getValues().getAllyShipsIcons().get(0));
                    setTotalBlocks(1);
                    break;
            }
        } else {
            setBorder(getValues().getEnemyShipListBorder());
            setBackground(getValues().getEnemyShipListBackgroundColor());
            switch (getIndex()) {
                case (0):
                    setIcon(getValues().getEnemyShipsIcons().get(4));
                    break;
                case (1):
                    setIcon(getValues().getEnemyShipsIcons().get(3));
                    break;
                case (2):
                    setIcon(getValues().getEnemyShipsIcons().get(3));
                    break;
                case (3):
                    setIcon(getValues().getEnemyShipsIcons().get(2));
                    break;
                case (4):
                    setIcon(getValues().getEnemyShipsIcons().get(2));
                    break;
                case (5):
                    setIcon(getValues().getEnemyShipsIcons().get(2));
                    break;
                case (6):
                    setIcon(getValues().getEnemyShipsIcons().get(1));
                    break;
                case (7):
                    setIcon(getValues().getEnemyShipsIcons().get(1));
                    break;
                case (8):
                    setIcon(getValues().getEnemyShipsIcons().get(0));
                    break;
                case (9):
                    setIcon(getValues().getEnemyShipsIcons().get(0));
                    break;
                case (10):
                    setIcon(getValues().getEnemyShipsIcons().get(0));
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
