package gr.epp.thesis.api;

import gr.epp.thesis.mvc.View;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @institute Applied Informatics and Multimedia - TEI of Crete
 */
public abstract class GenericLabel extends JLabel implements View {

    protected GenericValues values;
    protected ImageIcon playerLabel;
    protected boolean player;
    protected Border border;

    /**
     * Constructor for the decor panel of a specific player.
     */
    public GenericLabel() {
        initializeValues();
    }

    /**
     * Constructor for the player labels above ships list of a specific player.
     */
    public GenericLabel(boolean player) {
        initializeValues();
        this.player = player;
        initializePlayerLabel();
    }

    /**
     * Initializing current player's values.
     */
    public abstract void initializeValues();

    /**
     * Initializing current player's label.
     */
    public abstract void initializePlayerLabel();
}
