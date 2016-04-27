package Controller;

import Model.GameObjects.World;
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

        // Handle the arrow keys by combining them into a single vector
        Point2D moveDirection = new Point2D(0, 0);
//        if (inputKeys.contains(KeyCode.UP)) {
//            moveDirection = moveDirection.add(0, -1);
//        }
//        if (inputKeys.contains(KeyCode.DOWN)) {
//            moveDirection = moveDirection.add(0, 1);
//        }
        if (inputKeys.contains(KeyCode.LEFT)) {
            moveDirection = moveDirection.add(-1, 0);
        }
        if (inputKeys.contains(KeyCode.RIGHT)) {
            moveDirection = moveDirection.add(1, 0);
        }
        w.getPlayer().setImpulse(moveDirection.normalize());

        // Jump
        if (inputKeys.contains(KeyCode.SPACE)){
            w.getPlayer().jump();
        }

        // Restart
        if (inputKeys.contains(KeyCode.R)){
            w.initWorld();
        }

    }
}
