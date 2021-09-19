import java.awt.*;

/**
 * Represents a circle to be drawn as part of the tethered radial fractal.
 *
 * @author Ben Caffee
 * @version 6/10/2021
 */
public class Circle implements FractalElement {

    /** The color of the line */
    private final Color color;

    /** The x-position of the center of the circle */
    private final double x;

    /** The y-position of the center of the circle */
    private final double y;

    /** The radius of the circle */
    private final double radius;

    /**
     * Constructs a circle with a specific color, center position, and radius.
     *
     * @param color  The given color
     * @param x      The given x-position of the center
     * @param y      The given y-position of the center
     * @param radius The given radius
     */
    public Circle(Color color, double x, double y, double radius) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics graphics, int width, int height) {
        graphics.setColor(color);
        double[] boundingBoxPosition = new double[]{x - radius, y + radius};
        int[] point = new int[]{(int) (boundingBoxPosition[0] + (width / 2)), (int) ((height / 2) - boundingBoxPosition[1])};
        graphics.drawOval(point[0], point[1], (int) radius * 2, (int) radius * 2);
    }
}
