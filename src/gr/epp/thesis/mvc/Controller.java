package gr.epp.thesis.mvc;

import java.util.ArrayList;

public class Controller {

    private ArrayList views; //Contains all Views (components)
    private Model model;
    private boolean locked = false;

    public Controller(Model model) {
        this.model = model;
        views = new ArrayList();
    }

    /**
     * Sets the model for the controller.
     *
     * @param m
     */
    public void setModel(Model m) {
        this.model = m;
    }

    /**
     * Adds a view(component) in the ArrayList.
     *
     * @param v View
     */
    public void addView(View v) {
        views.add(v);
    }

    /**
     * removes a view (component) from the ArrayList.
     *
     * @param v View
     */
    public void removeView(View v) {
        views.remove(v);
    }

    public void notifyViews(Object obj) {
        int value = (Integer) obj;
        //update model value here
        for (int i = 0; i < views.size(); i++) {
            View v = (View) views.get(i);
            if (v != this) {
                v.stateChanged(value);
            }
        }
    }
}