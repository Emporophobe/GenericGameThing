package View;

import Model.GameObjects.World;
import javafx.scene.canvas.GraphicsContext;

public class DrawWorld {
    public static void draw(World w, GraphicsContext gc) {
        w.entities.forEach(e -> e.draw(gc));
        w.badGuys.forEach(b -> b.draw(gc));
        w.player.draw(gc);
    }


}
