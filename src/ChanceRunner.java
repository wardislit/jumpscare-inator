package src;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.SwingUtilities;

public class ChanceRunner {
    private static double pity = 1;
    private static double CHANCE_PER_SECOND = pity / 600; // 1 chance per 10 minutes
    private static final double MAX_PITY = 10; // Max 10x chance
    private static double pityIncrement = 600; // Increase pity every 10 minutes without event
    private static double seconds = 0;

    private static TrayIcon trayIcon;

    @SuppressWarnings("unused")
    private static FoxyWindow jumpscare;

    public static void main(String[] args) throws Exception {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray not supported");
            return;
        }

        SystemTray tray = SystemTray.getSystemTray();

        // Icon image (use small 16x16 or 32x32 PNG)
        Image image = Toolkit.getDefaultToolkit().getImage("icon.png");

        // Tooltip text
        trayIcon = new TrayIcon(image, "For those who know: " + "Pity is now " + pity + "x");

        trayIcon.setImageAutoSize(true);

        // Popup menu
        PopupMenu menu = new PopupMenu();
        MenuItem exitItem = new MenuItem("Exit");

        menu.add(exitItem);

        trayIcon.setPopupMenu(menu);

        // Add to system tray
        tray.add(trayIcon);

        // Actions
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            float rand = (float) Math.random();
            seconds++;
            if (seconds % pityIncrement == 0 && pity < MAX_PITY) {
                pity++;
                CHANCE_PER_SECOND = pity / 600;
                trayIcon.setToolTip("For those who know: Pity is now " + pity + "x");
            }

            if (rand < CHANCE_PER_SECOND) {
                triggerEvent();
                resetPity();
            }
            System.out.println("Random value: " + rand);
        }, 0, 1, TimeUnit.SECONDS);
        exitItem.addActionListener(e -> System.exit(0));
    }

    private static void triggerEvent() {
        try {
            new SoundPlayer();
            SwingUtilities.invokeLater(FoxyWindow::new);
        } catch (Exception e) {
            System.err.println("Error creating FoxyWindow: " + e.getMessage());
        }

        System.out.println("Event happened!");
    }

    private static void resetPity() {
        pity = 1;
        CHANCE_PER_SECOND = pity / 600;
        seconds = 0;
        System.out.println("Pity reset to 1x, chance per second: " + CHANCE_PER_SECOND);
    }
}
