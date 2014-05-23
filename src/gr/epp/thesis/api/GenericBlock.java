package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import javax.swing.JButton;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete
 */
public abstract class GenericBlock extends JButton implements View {

    protected GenericValues values;
    protected boolean player; //True - Player, False - Enemy
    protected int index;
    protected int totalBlocks;
    protected boolean warshipBlockOnGrid = false;
    protected boolean onShipsList = false;

    /**
     * Constructor needed for the seaBlocks Components.
     */
    public GenericBlock() {
        initializeValues();
        initializeGridBlocks();
    }

    /**
     * Constructor needed for the list of available warships.
     */
    public GenericBlock(int index, boolean player) {
        initializeValues();
        this.index = index;
        this.player = player;
        initializeShipsList();
    }

    /**
     * Initializing current player's values.
     */
    public abstract void initializeValues();

    /**
     * Initializing the Grid. Preparing seaBlocks. Background Color & sea water
     * Icon.
     */
    public abstract void initializeGridBlocks();

    /**
     * Initializing the ships list. Preparing available warships.
     */
    public abstract void initializeShipsList();

    // Getters & Setters:
    public int getTotalBlocks() {
        return totalBlocks;
    }

    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }

    public boolean isWarshipBlockOnGrid() {
        return warshipBlockOnGrid;
    }

    public void setWarshipBlockOnGrid(boolean warshipBlockOnGrid) {
        this.warshipBlockOnGrid = warshipBlockOnGrid;
    }

    public boolean isOnShipsList() {
        return onShipsList;
    }

    public void setOnShipsList(boolean onShipsList) {
        this.onShipsList = onShipsList;
    }
}
