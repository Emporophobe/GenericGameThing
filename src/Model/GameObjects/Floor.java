package Model.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Floor implements IGameObject {

    private AABB boundingBox;
    private int width;
    private int height;

    Floor(Point2D topLeft, int width, int height) {
        this.boundingBox = new AABB(topLeft, width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(this.boundingBox.getTopLeft().getX(),
                this.boundingBox.getTopLeft().getY(),
                this.width,
                this.height);
    }

    @Override
    public boolean collidedWith(IGameObject other) {
        return this.boundingBox.overlaps(other.getAABB());
    }

    @Override
    public AABB getAABB() {
        return this.boundingBox;
    }

    @Override
    public void onTick(World w) {
    }
}
