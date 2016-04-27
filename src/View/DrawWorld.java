package View;

import Model.GameObjects.World;
import javafx.scene.canvas.GraphicsContext;

public class DrawWorld {
    public static void draw(World w, GraphicsContext gc) {
        w.getEntities().forEach(e -> e.draw(gc));
        w.getBadGuys().forEach(b -> b.draw(gc));
        w.getPlayer().draw(gc);
    }


}
