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
        int playerX = (int) playerPos.getX();
        int thisX = (int) position.getX();


        if (position.distance(playerPos) <= sightDistance &&
                position.distance(playerPos) > speed) {
            if (playerX > thisX && // We are left of the player
                    !AABB.openPoint(position.add(width + speed, height + 1), w)) {
                setImpulse(new Point2D(1, 0));
            } else if (playerX < thisX && // We are right of player
                    !AABB.openPoint(position.add(-speed, height + 1), w)) {
                setImpulse(new Point2D(-1, 0));
            }
            else{
                setImpulse(new Point2D(0, 0));
            }
        }
        else if (position.subtract(playerPos).getX() <= speed){
            position = boundingBox.moveTowards(velocity, w.getPlayer().getAABB());
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(position.getX(), position.getY(), width, height);
    }
}
