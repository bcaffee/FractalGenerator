import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the tethered radial fractal generator.
 *
 * @author Ben Caffee
 * @version 6/10/2021
 */
public class Generator implements FractalSubject {

    /** The radius for the most center circle in the fractal */
    private static final int CENTER_RADIUS = 100;

    /** The minimum radius at which drawing is continued */
    private static final int MINIMUM_DRAW_RADIUS = 3;

    /** The observers connected with this generator */
    private final ArrayList<FractalObserver> observers;

    /** The color of the fractal */
    private Color color;

    /** The number of children in the fractal */
    private int childCount;

    /** The depth of recursion of each child */
    private int recursionDepth;

    /** The ratio between a parent circle's radius and its child circle's radius */
    private int childParentRatio;

    /** If the background color of the fractal window should be black or white */
    private boolean darkMode;

    /**
     * Constructs a fractal generator.
     */
    public Generator() {
        observers = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(FractalObserver observer) {
        observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers() {
        //(only one observer)
        for (FractalObserver observer : observers) {
            observer.update();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendData(Color color, int childCount, boolean darkMode, int childParentRatio, int recursionDepth) {
        this.color = color;
        this.childCount = childCount;
        this.darkMode = darkMode;
        this.childParentRatio = childParentRatio;
        this.recursionDepth = recursionDepth;
        notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<FractalElement> getData() {
        ArrayList<FractalElement> fractalElements = new ArrayList<>();
        generateChildren(fractalElements, CENTER_RADIUS, recursionDepth, 0, 0);
        return fractalElements;
    }

    /**
     * Recursively calculates and fills a list of fractal elements to be drawn.
     *
     * @param fractalElements The temporary given fractal elements list
     * @param radius          The radius of the parent circle
     * @param recursionDepth  The current depth of recursion for each child
     * @param xCenter         The temporary given x-position of the center of the parent circle
     * @param yCenter         The temporary given y-position of the center of the parent circle
     */
    private void generateChildren(ArrayList<FractalElement> fractalElements, double radius, int recursionDepth, double xCenter, double yCenter) {
        //base case
        if (radius >= MINIMUM_DRAW_RADIUS && recursionDepth >= 1) {
            Circle parent = new Circle(color, xCenter, yCenter, radius);
            fractalElements.add(parent);
            recursionDepth--;

            double angleOffset = Math.PI / 2;
            double parentRadius = radius;
            radius *= childParentRatio * .01;

            double xChildCenter, yChildCenter;
            double xTetherStart, yTetherStart;
            double xTetherEnd, yTetherEnd;

            /*
            The tether's distance between the intersection point on the parent circle to the center of the child
            circle is equal to the parent radius. The distance between the parent's center point and the child's center
            point is equal to the parent's diameter.
            */

            //Generates spokes and calculates where children go
            for (int currentChild = 1; currentChild <= this.childCount; currentChild++) {
                xChildCenter = xCenter + (Math.cos(angleOffset) * parentRadius * 2);
                yChildCenter = yCenter + (Math.sin(angleOffset) * parentRadius * 2);
                generateChildren(fractalElements, radius, recursionDepth, xChildCenter, yChildCenter);

                if (recursionDepth >= 1 && radius >= MINIMUM_DRAW_RADIUS) {
                    xTetherStart = xCenter + (Math.cos(angleOffset) * parentRadius);
                    yTetherStart = yCenter + (Math.sin(angleOffset) * parentRadius);
                    xTetherEnd = xChildCenter - (Math.cos(angleOffset) * radius);
                    yTetherEnd = yChildCenter - (Math.sin(angleOffset) * radius);
                    Line tether = new Line(color, xTetherStart, yTetherStart, xTetherEnd, yTetherEnd);
                    fractalElements.add(tether);
                }
                angleOffset += (2 * Math.PI / this.childCount);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getIfDarkMode() {
        return darkMode;
    }
}
