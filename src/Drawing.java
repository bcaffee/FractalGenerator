import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the drawing window for the tethered radial fractal.
 *
 * @author Ben Caffee
 * @version 6/10/2021
 */
public class Drawing extends JFrame implements FractalObserver {

    /** The width of the drawing window */
    private static final int WIDTH = 800;

    /** The height of the drawing window */
    private static final int HEIGHT = 800;

    /** The subject connected with this drawing window */
    private final FractalSubject subject;

    /** The fractal elements to be drawn */
    private ArrayList<FractalElement> fractalElements;

    /**
     * Constructs a drawing window connected to a given subject.
     *
     * @param subject The given subject
     */
    public Drawing(FractalSubject subject) {
        this.subject = subject;
        subject.register(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        fractalElements = subject.getData();
        setSize(WIDTH, HEIGHT);
        setTitle("Drawing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation((screenSize.width - WIDTH) / 2, (screenSize.height - HEIGHT) / 2);
        JPanel panel = new DrawPanel();
        setResizable(false);
        panel.setLayout(null);
        Color backgroundColor = Color.white;
        if (subject.getIfDarkMode()) {
            backgroundColor = Color.black;
        }
        panel.setBackground(backgroundColor);
        getContentPane().add(panel);
        setVisible(true);
    }

    /**
     * The drawing panel on which the tethered radial fractal is drawn.
     */
    private class DrawPanel extends JPanel {

        /**
         * Paints the fractal elements on the drawing window.
         *
         * @param graphics The graphics component for the window
         */
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            for (FractalElement fractalElement : fractalElements) {
                fractalElement.draw(graphics, Drawing.WIDTH, Drawing.HEIGHT);
            }
        }
    }
}
