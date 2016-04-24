package Model.GameObjects;

import Model.AABB;
import Model.World;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player implements IPhysicsObject {

    private Point2D velocity;
    private int width;
    private int height;
    private AABB boundingBox;
    private Point2D position;
    private int speed;
    private boolean isOnGround;
    private boolean collided = false;

    public Player() {
        width = 10;
        height = 10;
        position = new Point2D(200, 50);
        boundingBox = new AABB(position, width, height);
        velocity = new Point2D(0, 0);
        speed = 10;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(collided ? Color.BLACK : Color.RED);
        gc.fillOval(position.getX(), position.getY(), width, height);
    }

    @Override
    public boolean collidedWith(IGameObject other) {
        return boundingBox.overlaps(other.getAABB());
    }

    @Override
    public AABB getAABB() {
        return boundingBox;
    }

    @Override
    public void onTick(World w) {
        isOnGround = isTouchingGround(w);
        gravity();
        move(w);
    }

    @Override
    public void gravity() {
        if(!isOnGround) {
            velocity = velocity.add(0, 5);
        }
    }

    public void jump()
    {
        if(isOnGround) {
            velocity = velocity.add(0, -10);
        }
    }

    public void movePlayer(Point2D direction){
        if (velocity.magnitude() <= speed){
            velocity = direction.multiply(speed);
        }
    }

    /**
     * Check if the player is directly above an object
     * @param w The World
     * @return Whether the player is on the ground
     */
    private boolean isTouchingGround(World w){
        AABB boundingBox = getAABB();
        boundingBox.moveBy(0, 1);
        return w.entities.stream().anyMatch(x -> boundingBox.overlaps(x.getAABB()));
    }

    private void move(World w) {
        Point2D newPosition = position.add(velocity);
        boundingBox.moveTo(newPosition);

        collided = false;
        IGameObject collisionTarget = this;
        for (IGameObject x : w.entities) {
            if (collidedWith(x)){
                collided = true;
                collisionTarget = x;
            }
        }

        if (!collided) {
            position = newPosition;
            boundingBox.moveTo(position);
        } else {
            boundingBox.moveTo(position);
            position = boundingBox.moveTowards(velocity, collisionTarget.getAABB());
            //velocity = boundingBox.distanceTo(collisionTarget.getAABB());
        }
    }
}
