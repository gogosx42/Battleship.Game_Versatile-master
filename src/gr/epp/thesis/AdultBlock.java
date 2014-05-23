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
        this.values = new AdultValues();
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
                    setIcon(values.getMyWarships().get(4 - index));
                    setTotalBlocks(5);
                    break;
                case (1):
                    setIcon(values.getMyWarships().get(4 - index));
                    setTotalBlocks(4);
                    break;
                case (2):
                    setIcon(values.getMyWarships().get(4 - index));
                    setTotalBlocks(3);
                    break;
                case (3):
                    setIcon(values.getMyWarships().get(4 - index));
                    setTotalBlocks(2);
                    break;
                case (4):
                    setIcon(values.getMyWarships().get(4 - index));
                    setTotalBlocks(1);
                    break;
            }
        } else {
            setBorder(values.getEnemyShipListBorder());
            setBackground(values.getEnemyShipListBackgroundColor());
            setIcon(values.getEnemyShipsIcons().get(4 - index));
        }
    }

    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
