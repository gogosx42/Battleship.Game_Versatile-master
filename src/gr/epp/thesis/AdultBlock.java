package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete
 */
public class AdultBlock extends GenericBlock {

    public AdultBlock() {
        super();
    }

    public AdultBlock(int blockIndex, boolean ownShipsList) {
        super(blockIndex, ownShipsList);
    }

    /**
     * Initializing Adult player's values.
     */
    @Override
    public void initializeValues() {
        setValues(new AdultValues());
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
                    setIcon(getValues().getAllyShipsIcons().get(4 - getIndex()));
                    setTotalBlocks(5);
                    break;
                case (1):
                    setIcon(getValues().getAllyShipsIcons().get(4 - getIndex()));
                    setTotalBlocks(4);
                    break;
                case (2):
                    setIcon(getValues().getAllyShipsIcons().get(4 - getIndex()));
                    setTotalBlocks(3);
                    break;
                case (3):
                    setIcon(getValues().getAllyShipsIcons().get(4 - getIndex()));
                    setTotalBlocks(2);
                    break;
                case (4):
                    setIcon(getValues().getAllyShipsIcons().get(4 - getIndex()));
                    setTotalBlocks(1);
                    break;
            }
        } else {
            setBorder(getValues().getEnemyShipListBorder());
            setBackground(getValues().getEnemyShipListBackgroundColor());
            setIcon(getValues().getEnemyShipsIcons().get(4 - getIndex()));
        }
    }

    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
