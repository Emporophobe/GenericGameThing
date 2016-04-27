package View;

import Model.UIObjects.UI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawBackground {
    public static void draw(GraphicsContext gc){
        gc.setFill(Color.color(0.16, 0.16, 0.18));
        gc.fillRect(0, 0, UI.getScreenWidth(), UI.getScreenHeight());
    }
}
