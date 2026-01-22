package src;

import javax.swing.*;
import java.awt.*;

public class FoxyWindow extends JFrame {
    private FullscreenGifPanel panel;
    private int screenWidth;
    private int screenHeight;

    public FoxyWindow() {
        // Screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;

        // Setup JFrame (use this, not a separate frame)
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // transparent
        setAlwaysOnTop(true);
        setFocusableWindowState(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add GIF panel
        panel = new FullscreenGifPanel("assets/fnaf-foxy.gif");
        add(panel);

        setVisible(true);

        // Close after 870 ms
        Timer timer = new Timer(870, e -> dispose());
        timer.setRepeats(false);
        timer.start();
    }

    public void restartGif() {
        remove(panel);
        panel = new FullscreenGifPanel("assets/fnaf-foxy.gif");
        add(panel);
        revalidate();
        repaint();
    }
}