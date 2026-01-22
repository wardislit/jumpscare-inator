
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import src.FoxyWindow;
import src.SoundPlayer;

public class ChanceRunner {
    private static final double CHANCE_PER_SECOND = 0.001; // 0.1% chance per second

    @SuppressWarnings("unused")
    private static FoxyWindow jumpscare;

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            if (Math.random() < CHANCE_PER_SECOND) {
                triggerEvent();
            }
        }, 0, 1, TimeUnit.SECONDS);
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
