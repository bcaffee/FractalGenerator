import java.awt.*;

/**
 * The requirements for any shape that should be drawn as part of the fractal such as a circle or line.
 *
 * @author Ben Caffee
 * @version 6/10/2021
 */
public interface FractalElement {

    /**
     * Draws the respective shape.
     *
     * @param g      The graphics component for the window.
     * @param width  The given width of the window
     * @param height The given height of the window
     */
    void draw(Graphics g, int width, int height);
}
