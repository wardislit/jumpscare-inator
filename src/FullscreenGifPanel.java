package src;

import javax.swing.*;
import java.awt.*;

public class FullscreenGifPanel extends JPanel {
    private ImageIcon gif;
    private int screenWidth;
    private int screenHeight;

    public FullscreenGifPanel(String gifPath) {
        gif = new ImageIcon(gifPath);
        setOpaque(false); // essential for transparency

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;

        // Timer for GIF animation
        Timer timer = new Timer(40, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gif.getImage(), 0, 0, screenWidth, screenHeight, this);
    }
}