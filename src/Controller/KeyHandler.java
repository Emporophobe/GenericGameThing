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

        Point2D newVelocity = new Point2D(0, 0);
        if (inputKeys.contains(KeyCode.UP)) {
            newVelocity = newVelocity.add(0, -5);
        }
        if (inputKeys.contains(KeyCode.DOWN)) {
            newVelocity = newVelocity.add(0, 5);
        }
        if (inputKeys.contains(KeyCode.LEFT)) {
            newVelocity = newVelocity.add(-5, 0);
        }
        if (inputKeys.contains(KeyCode.RIGHT)) {
            newVelocity = newVelocity.add(5, 0);
        }
        w.player.setVelocity(newVelocity);

        // Alternatively,
        //w.LoShapes.forEach(s -> s.setColor(inputKeys.contains(KeyCode.SPACE) ? Color.RED : Color.BLACK));
    }
}
