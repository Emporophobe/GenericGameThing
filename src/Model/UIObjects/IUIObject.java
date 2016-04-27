package Model.UIObjects;

import Model.GameObjects.World;
import javafx.scene.canvas.GraphicsContext;

public interface IUIObject {
    void draw(GraphicsContext gc);
    void onTick(World w);
}
