package Model.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Attack implements IGameObject {
    private AABB boundingBox;
    private int rawDamage;
    private int width;
    private int height;
    private Point2D velocity;
    private boolean affectedByGravity;
    private Point2D position;
    private boolean piercing;

    Attack(int rawDamage, boolean piercing, Point2D topLeft, int width, int height, Point2D velocity, boolean affectedByGravity) {
        this.rawDamage = rawDamage;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.affectedByGravity = affectedByGravity;
        this.boundingBox = new AABB(topLeft, width, height);
        this.position = topLeft;
        this.piercing = piercing;
    }

    Attack(){

    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.AZURE);
        gc.fillRect(position.getX(), position.getY(), width, height);
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
        Point2D newPosition = position.add(velocity);
        position = newPosition;
        boundingBox.moveTo(newPosition);

    }


    int getRawDamage() {
        return rawDamage;
    }

    boolean isPiercing() {
        return piercing;
    }
}
