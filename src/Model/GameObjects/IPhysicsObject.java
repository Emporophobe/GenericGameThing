package Model.GameObjects;

import javafx.scene.canvas.GraphicsContext;

/**
 * An IPhysicsObject is an IGameObject which is affected by physics (i.e. gravity)
 */
public interface IPhysicsObject extends IGameObject {
    @Override
    void draw(GraphicsContext gc);

    @Override
    boolean collidedWith(IGameObject other);

    @Override
    AABB getAABB();

    @Override
    void onTick(World w);

    void gravity();
}
