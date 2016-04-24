package View;

import Model.UIObjects.UI;
import javafx.scene.canvas.GraphicsContext;

public class DrawUI {
    public static void draw(UI ui, GraphicsContext gc){
        ui.uiElements.forEach(e -> e.draw(gc));
    }
}
