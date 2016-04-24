package Model.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player implements IPhysicsObject {

    private Point2D velocity;
    private int width;
    private int height;
    private AABB boundingBox;
    private Point2D position;
    private int speed;
    //private boolean isOnGround;
    private boolean collided = false;
    private ArrayList<Directions> validDirections = new ArrayList<>();

    Player() {
        width = 10;
        height = 10;
        position = new Point2D(200, 50);
        boundingBox = new AABB(position, width, height);
        velocity = new Point2D(0, 0);
        speed = 5;
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
        validDirections = getValidDirections(w);
        System.out.print(validDirections);
        gravity();
        move(w);
        System.out.println(velocity);
    }

    @Override
    public void gravity() {
        if(validDirections.contains(Directions.DOWN)) {
            velocity = velocity.add(0, 5);
        }
        else if (velocity.getY() > 0){
            velocity = new Point2D(velocity.getX(), 0);
        }
    }

    public void jump()
    {
        if(!validDirections.contains(Directions.DOWN) &&
                validDirections.contains(Directions.UP)){
            velocity = velocity.add(0, -10);

        }
    }

    public void movePlayer(Point2D direction){
        if (velocity.magnitude() <= speed){
            velocity = direction.multiply(speed);
        }
    }

    private enum Directions{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private ArrayList<Directions> getValidDirections(World w){
        ArrayList<Directions> dirs = new ArrayList<>();
        AABB testAABB = getAABB();

        testAABB.moveBy(0, -1);
        if (!w.entities.stream().anyMatch(this::collidedWith)){
            dirs.add(Directions.UP);
        }
        testAABB.moveBy(0, 2);
        if (!w.entities.stream().anyMatch(this::collidedWith)){
            dirs.add(Directions.DOWN);
        }
        testAABB.moveBy(-1, -1);
        if (!w.entities.stream().anyMatch(this::collidedWith)){
            dirs.add(Directions.LEFT);
        }
        testAABB.moveBy(2, 0);
        if (!w.entities.stream().anyMatch(this::collidedWith)){
            dirs.add(Directions.RIGHT);
        }

        return dirs;
    }

    /**
     * Check if the player is directly above an object
     * @param w The World
     * @return Whether the player is on the ground
     */
    private boolean isTouchingGround(World w){
//        if (w.entities.stream().anyMatch(x -> boundingBox.overlaps(x.getAABB()))){
//            return false;
//        }
        AABB newBoundingBox = getAABB();
        newBoundingBox.moveBy(0, 1);
        return w.entities.stream().anyMatch(x -> newBoundingBox.overlaps(x.getAABB()));
    }

    private void move(World w) {
        Point2D newPosition = position.add(velocity);
        boundingBox.moveTo(newPosition);

        collided = false;
        ArrayList<IGameObject> collisionTargets = new ArrayList<>();
        w.entities.stream().filter(this::collidedWith).forEach(x -> {
            collided = true;
            collisionTargets.add(x);
        });

        if (!collided) {
            position = newPosition;
            boundingBox.moveTo(position);
        } else {
            boundingBox.moveTo(position);
            for (IGameObject x : collisionTargets){
                position = boundingBox.moveTowards(velocity, x.getAABB());
            }
        }
    }
}
