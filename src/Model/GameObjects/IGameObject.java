package Model.GameObjects;

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
     * @param other The IGameObject to test against
     * @return Whether the two are colliding
     */
    boolean collidedWith(IGameObject other);

    /**
     * Get the bounding box for the entity
     *
     * @return The AABB for this object
     */
    AABB getAABB();

    /**
     * What to do each tick
     */
    void onTick(World w);
}
