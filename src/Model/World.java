package Model;

import Model.GameObjects.Floor;
import Model.GameObjects.IGameObject;
import Model.GameObjects.Player;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class World {
    public ArrayList<IGameObject> entities = new ArrayList<>();
    public Player player = new Player();

    public World() {
        this.initWorld();
    }

    public void initWorld() {
        IGameObject floor1 = new Floor(new Point2D(10, 10), 60, 15);
        IGameObject floor2 = new Floor(new Point2D(50, 50), 100, 100);
        IGameObject floor3 = new Floor(new Point2D(0, 180), 400, 15);
        Floor floor4 = new Floor(new Point2D(350, 100), 10, 300);

        player = new Player();

        entities.clear();

        entities.add(floor1);
        entities.add(floor2);
        entities.add(floor3);
        entities.add(floor4);
    }

    public void onTick() {
        entities.forEach(s -> s.onTick(this));
        player.onTick(this);
    }
}
