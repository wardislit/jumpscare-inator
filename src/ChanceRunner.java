package src;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.SwingUtilities;

public class ChanceRunner {
    private static final double CHANCE_PER_SECOND = 1 / 600; // 1 chance per 10 minutes

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
        TrayIcon trayIcon = new TrayIcon(image, "For those who know:");

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
            if (Math.random() < CHANCE_PER_SECOND) {
                triggerEvent();
            }
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
}
