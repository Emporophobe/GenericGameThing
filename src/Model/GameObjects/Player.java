package Model.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Player extends Living {

    Player() {
        super(10, 10, new Point2D(200, 50), 5, 100);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(collided ? Color.BLACK : Color.GREEN);
        gc.fillOval(position.getX(), position.getY(), width, height);
    }
}
