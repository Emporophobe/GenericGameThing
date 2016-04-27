package Model.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Enemy extends Living {

    Enemy() {
        super(10, 10, new Point2D(150, 70), 3, 50);
    }

    @Override
    void aiTurn(World w) {
        int sightDistance = 100;

        Point2D playerPos = w.getPlayer().getAABB().getTopLeft();

        if (position.distance(playerPos) <= sightDistance) {
            if (position.getX() < playerPos.getX()) {
                setImpulse(new Point2D(1, 0));
            } else if (position.getX() > playerPos.getX()) {
                setImpulse(new Point2D(-1, 0));
            }
        } else {
            setImpulse(new Point2D(0, 0));
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(position.getX(), position.getY(), width, height);
    }
}
