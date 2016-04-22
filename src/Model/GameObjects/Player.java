package Model.GameObjects;

import Model.AABB;
import Model.World;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player implements IGameObject {

    public Point2D velocity;
    int x;
    int y;
    int width;
    int height;
    AABB boundingBox;
    private Point2D position;

    public Player() {
        this.width = 10;
        this.height = 10;
        this.boundingBox = new AABB(new Point2D(0, 0), this.width, this.height);
        this.position = new Point2D(100, 50);
        this.velocity = new Point2D(0, 0);
    }

    public void setVelocity(Point2D v) {
        this.velocity = v;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
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

    void move(World w) {
        Point2D newPosition = this.position.add(this.velocity);
        AABB oldAABB = boundingBox;
        this.boundingBox.moveTo(newPosition);
        boolean willCollide = w.entities.stream().anyMatch(this::collidedWith);

        if (!willCollide) {
            this.position = newPosition;
            this.boundingBox.moveTo(newPosition);
        } else {
            this.boundingBox.moveTo(oldAABB.getTopLeft());
        }
    }
}
