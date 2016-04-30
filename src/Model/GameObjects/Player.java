package Model.GameObjects;

import Model.Directions;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Living {

    Player() {
        super(10, 10, new Point2D(200, 50), 5, 100);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(collided ? Color.BLACK : Color.GREEN);
        gc.fillOval(position.getX(), position.getY(), width, height);
    }

    private long lastAttack1 = 0;
    private int attack1CoolDown = 10;
    public void attack1(World w) {
        if (w.getCurrentTick() - lastAttack1 >= attack1CoolDown) {
            lastAttack1 = w.getCurrentTick();

            if (facing == Directions.RIGHT) {
                w.addAttack(new Attack(
                        10,
                        true,
                        new Point2D(position.getX() + width, position.getY()),
                        5,
                        3,
                        new Point2D(10, 0),
                        false));
            } else {
                w.addAttack(new Attack(
                        10,
                        true,
                        new Point2D(position.getX() - 5, position.getY()),
                        5,
                        3,
                        new Point2D(-10, 0),
                        false));
            }

            consoleMessages.add("Pew Pew Pew");
        }
    }

    public long getLastAttack1() {
        return lastAttack1;
    }

    public int getAttack1CoolDown() {
        return attack1CoolDown;
    }
}
