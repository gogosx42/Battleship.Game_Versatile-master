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
        this.setValues(new ChildValues());
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
            setIcon(getValues().getAllyShipsIcons().get(0));
            setTotalBlocks(1);
        } else {
            setBorder(getValues().getEnemyShipListBorder());
            setBackground(getValues().getEnemyShipListBackgroundColor());
            setIcon(getValues().getEnemyShipsIcons().get(0));
        }
    }

    //To change body of generated methods, choose Tools | Templates.
    @Override
    public void stateChanged(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
