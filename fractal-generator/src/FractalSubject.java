import java.awt.*;
import java.util.ArrayList;

/**
 * The requirements for the Generator class.
 *
 * @author Ben Caffee
 * @version 6/10/2021
 */
public interface FractalSubject {

    /**
     * Registers a given observer.
     *
     * @param observer The given observer
     */
    void register(FractalObserver observer);

    /**
     * Notifies all registered observers to be updated.
     */
    void notifyObservers();

    /**
     * Sends the specified data to the observers
     *
     * @param color            The given color of the fractal
     * @param childCount       The given child count for the fractal
     * @param darkMode         Whether or not the fractal background is black or not
     * @param childParentRatio The given radius ratio between a parent circle's and its child's
     * @param recursionDepth   The given recursion depth
     */
    void sendData(Color color, int childCount, boolean darkMode, int childParentRatio, int recursionDepth);
    /* TODO: Note to self: If doing "warning" extra credit, sendData() would need to return a boolean
    Check shape count with the following: 2 * (childCount ^ (recursion depth at that layer)) and then you have to add up
    all the layers (eg. recursion depth 4 means 4 layers)
    For the above example, shape count = 1 + (2 * (childCount ^ 1)) + (2 * (childCount ^ 2)) + (2 * (childCount ^ 3))
    when your childCount is 10 the number of elements should be (depth - 1) 2s followed by a 1. (10 children and depth 3 has 221 elements)
     */

    /**
     * Gets the fractal elements to be drawn for the observer
     *
     * @return The list of fractal elements to be drawn
     */
    ArrayList<FractalElement> getData();

    /**
     * Gets whether or not the fractal should have a dark background or not.
     *
     * @return If the fractal should have a dark background or not.
     */
    boolean getIfDarkMode();
}
