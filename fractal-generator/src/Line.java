import java.awt.*;

/**
 * Represents a line to be drawn as part of the tethered radial fractal.
 *
 * @author Ben Caffee
 * @version 6/10/2021
 */
public class Line implements FractalElement {

    /** The color of the line */
    private final Color color;

    /** The starting x-position of the line */
    private final double xStart;

    /** The starting y-position of the line */
    private final double yStart;

    /** The ending x-position of the line */
    private final double xEnd;

    /** The ending y-position of the line */
    private final double yEnd;

    /**
     * Constructs a line with a specific color, starting, and ending position.
     *
     * @param color  The given color
     * @param xStart The given starting x-position
     * @param yStart The given starting y-position
     * @param xEnd   The given ending x-position
     * @param yEnd   The given ending y-position
     */
    public Line(Color color, double xStart, double yStart, double xEnd, double yEnd) {
        this.color = color;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics graphics, int width, int height) {
        graphics.setColor(color);
        int[] startPoint = new int[]{(int) (xStart + (width / 2)), (int) ((height / 2) - yStart)};
        int[] endPoint = new int[]{(int) (xEnd + (width / 2)), (int) ((height / 2) - yEnd)};
        graphics.drawLine(startPoint[0], startPoint[1], endPoint[0], endPoint[1]);
    }
}
