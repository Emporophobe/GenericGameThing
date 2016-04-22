package Controller;

import Model.World;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyHandler {
    private static ArrayList<KeyCode> inputKeys = new ArrayList<>();

    public static void handleKeyPressed(KeyEvent e) {
        KeyCode code = e.getCode();

        if (!inputKeys.contains(code)) {
            inputKeys.add(code);
        }
    }

    public static void handleKeyReleased(KeyEvent e) {
        KeyCode code = e.getCode();
        inputKeys.remove(code);
    }

    public static void processKeys(World w) {
//        if (inputKeys.contains(KeyCode.SPACE)) {
//            w.LoShapes.forEach(s -> s.setColor(Color.RED));
//        } else {
//            w.LoShapes.forEach(s -> s.setColor(Color.BLACK));
//        }

        w.player.setVelocity((new Point2D(0, 0)));
        if (inputKeys.contains(KeyCode.UP)) {
            w.player.setVelocity(new Point2D(0, -5));
        }
        if (inputKeys.contains(KeyCode.DOWN)) {
            w.player.setVelocity(new Point2D(0, 5));
        }
        if (inputKeys.contains(KeyCode.LEFT)) {
            w.player.setVelocity(new Point2D(-5, 0));
        }
        if (inputKeys.contains(KeyCode.RIGHT)) {
            w.player.setVelocity(new Point2D(5, 0));
        }

        // Alternatively,
        //w.LoShapes.forEach(s -> s.setColor(inputKeys.contains(KeyCode.SPACE) ? Color.RED : Color.BLACK));
    }
}
