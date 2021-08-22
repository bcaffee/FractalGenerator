import javax.swing.*;
import java.awt.*;

/**
 * Represents a GUI for the player to apply the settings needed to draw a tethered radial fractal.
 *
 * @author Ben Caffee
 * @version 6/10/2021
 */
public class GUI extends JFrame {

    /** The width of the GUI window */
    private static final int WIDTH = 500;

    /** The height of the GUI window */
    private static final int HEIGHT = 500;

    /** The minimum child count that can be chosen */
    private static final int MIN_CHILD_COUNT = 1;

    /** The maximum child count that can be chosen */
    private static final int MAX_CHILD_COUNT = 13;

    /** The minimum percentage parent-child ratio that can be chosen */
    private static final int MIN_RATIO = 20;

    /** The maximum percentage parent-child ratio that can be chosen */
    private static final int MAX_RATIO = 70;

    /** The minimum (recursion) child depth that can be chosen */
    private static final int MIN_DEPTH = 2;

    /** The maximum (recursion) child depth that can be chosen */
    private static final int MAX_DEPTH = 10;

    /** The default child count */
    private static final int DEFAULT_CHILD_COUNT = 8;

    /** The default child recursion depth */
    private static final int DEFAULT_DEPTH = 5;

    /** The default child count */
    private static final int DEFAULT_CHILD_PARENT_RATIO = 42;

    /** The major tick default for sliders */
    private static final int MAJOR_TICK_DEFAULT = 4;

    /** The minor tick default for sliders */
    private static final int MINOR_TICK_DEFAULT = 1;

    /** The subject connected with this GUI */
    private final FractalSubject subject;

    /** The button to draw a tethered radial fractal */
    private JButton drawButton;

    /** The button to bring up a color picker */
    private JButton colorPicker;

    /** The slider for the number of children for the fractal */
    private JSlider childrenCountSlider;

    /** The slider for the radius ratio between the parent and child */
    private JSlider radiusRatioSlider;

    /** The slider for the recursion depth for the fractal */
    private JSlider depthSlider;

    /** The minimum child depth that can be chosen */
    private JCheckBox backgroundColorToggle;

    /**
     * Constructs a GUI connected to a given subject.
     *
     * @param subject The given subject
     */
    public GUI(FractalSubject subject) {
        this.subject = subject;
        setUpFrame();
        setUpPanelWidgets();
        setVisible(true);
    }

    /**
     * Sets up the frame.
     */
    private void setUpFrame() {
        drawButton = new JButton("Draw");
        colorPicker = new JButton("Pick fractal color");
        childrenCountSlider = new JSlider(MIN_CHILD_COUNT, MAX_CHILD_COUNT);
        radiusRatioSlider = new JSlider(MIN_RATIO, MAX_RATIO);
        depthSlider = new JSlider(MIN_DEPTH, MAX_DEPTH);
        backgroundColorToggle = new JCheckBox();

        setSize(WIDTH, HEIGHT);
        setTitle("Tethered Radial Fractal Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation((screenSize.width - WIDTH) / 2, (screenSize.height - HEIGHT) / 2);
    }

    /**
     * Sets up the panel widgets.
     */
    private void setUpPanelWidgets() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(3, 3));

        final Color[] fractalColor = new Color[1];
        fractalColor[0] = Color.cyan;

        colorPicker.addActionListener(e -> {
            fractalColor[0] = JColorChooser.showDialog(panel, "Choose Color", Color.white);
            colorPicker.setBackground(fractalColor[0]);
        });
        panel.add(colorPicker);

        backgroundColorToggle.setText("Background: White");
        backgroundColorToggle.setBackground(Color.white);
        backgroundColorToggle.addActionListener(e -> {
            if (backgroundColorToggle.isSelected()) {
                backgroundColorToggle.setText("Background: Black");
                backgroundColorToggle.setBackground(Color.black);
            } else {
                backgroundColorToggle.setText("Background: White");
                backgroundColorToggle.setBackground(Color.white);
            }
        });
        panel.add(backgroundColorToggle);

        drawButton.addActionListener(e -> subject.sendData(fractalColor[0], childrenCountSlider.getValue(),
                backgroundColorToggle.isSelected(), radiusRatioSlider.getValue(), depthSlider.getValue()));
        drawButton.setBackground(Color.pink);
        panel.add(drawButton);

        setUpSliders();
        JLabel childrenCountLabel = new JLabel("       Pick children count");
        panel.add(childrenCountLabel);
        JLabel ratio = new JLabel("    Pick child-parent ratio    ");
        panel.add(childrenCountLabel);
        JLabel depth = new JLabel("        Pick child depth");
        panel.add(childrenCountLabel);
        panel.add(ratio);
        panel.add(depth);

        panel.add(childrenCountSlider);
        panel.add(radiusRatioSlider);
        panel.add(depthSlider);
    }

    /**
     * Sets up the various sliders for the GUI.
     */
    private void setUpSliders() {
        childrenCountSlider.setValue(DEFAULT_CHILD_COUNT);
        childrenCountSlider.setMajorTickSpacing(MAJOR_TICK_DEFAULT);
        childrenCountSlider.setMinorTickSpacing(MIN_CHILD_COUNT);
        childrenCountSlider.setPaintTicks(true);
        childrenCountSlider.setPaintLabels(true);

        radiusRatioSlider.setValue(DEFAULT_CHILD_PARENT_RATIO);
        radiusRatioSlider.setMajorTickSpacing(MAX_DEPTH);
        radiusRatioSlider.setMinorTickSpacing(MIN_DEPTH);
        radiusRatioSlider.setPaintTicks(true);
        radiusRatioSlider.setPaintLabels(true);

        depthSlider.setValue(DEFAULT_DEPTH);
        depthSlider.setMajorTickSpacing(MAJOR_TICK_DEFAULT);
        depthSlider.setMinorTickSpacing(MINOR_TICK_DEFAULT);
        depthSlider.setPaintTicks(true);
        depthSlider.setPaintLabels(true);
    }

    //Note to self: If doing "real time" extra credit, GUI will require more action listeners (one for each widget),
    //and call sendData for each update (to redraw with each change)
}
