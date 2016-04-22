package Model.GameObjects;

import Model.AABB;
import Model.World;
import javafx.scene.canvas.GraphicsContext;

public interface IGameObject {
    /**
     * Draw the entity to the gc
     *
     * @param gc The graphics context
     */
    void draw(GraphicsContext gc);

    /**
     * Tell if this entity is colliding with another
     *
     * @param other
     * @return
     */
    boolean collidedWith(IGameObject other);

    /**
     * Get the bounding box for the entity
     *
     * @return
     */
    AABB getAABB();

    /**
     * What to do each tick
     */
    void onTick(World w);
}
