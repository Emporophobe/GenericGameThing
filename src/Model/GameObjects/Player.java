package Model.GameObjects;

import Model.AABB;
import Model.World;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player implements IGameObject {

    private Point2D velocity;
    private int x;
    private int y;
    private int width;
    private int height;
    private AABB boundingBox;
    private Point2D position;

    public Player() {
        this.width = 10;
        this.height = 10;
        this.position = new Point2D(200, 50);
        this.boundingBox = new AABB(this.position, this.width, this.height);
        this.velocity = new Point2D(0, 0);
    }

    public void setVelocity(Point2D v) {
        this.velocity = v;
    }

    private boolean collided = false;
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(collided? Color.BLACK : Color.RED);
        gc.fillOval(this.position.getX(), this.position.getY(), this.width, this.height);
    }

    @Override
    public boolean collidedWith(IGameObject other) {
        return this.boundingBox.overlaps(other.getAABB());
    }

    @Override
    public AABB getAABB() {
        return boundingBox;
    }

    @Override
    public void onTick(World w) {
        this.move(w);
    }

    private void move(World w) {
        Point2D newPosition = this.position.add(this.velocity);
        AABB oldAABB = boundingBox;
        this.boundingBox.moveTo(newPosition);
        boolean willCollide = w.entities.stream().anyMatch(this::collidedWith);
        collided = willCollide;
        if (!willCollide) {
            this.position = newPosition;
            this.boundingBox.moveTo(newPosition);
        } else {
            this.boundingBox.moveTo(oldAABB.getTopLeft());
        }
    }
}
