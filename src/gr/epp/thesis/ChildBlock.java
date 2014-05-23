package gr.epp.thesis;

import gr.epp.thesis.api.GenericBlock;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete
 */
public class ChildBlock extends GenericBlock {

    public ChildBlock() {
        super();
    }

    public ChildBlock(int index, boolean ownShipsList) {
        super(index, ownShipsList);
    }

    /**
     * Initializing Child player's values.
     */
    @Override
    public void initializeValues() {
        this.values = new ChildValues();
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
            setIcon(values.getMyWarships().get(0));
            setTotalBlocks(1);
        } else {
            setBorder(values.getEnemyShipListBorder());
            setBackground(values.getEnemyShipListBackgroundColor());
            setIcon(values.getEnemyShipsIcons().get(0));
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
