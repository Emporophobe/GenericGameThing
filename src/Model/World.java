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

        entities.add(floor1);
        entities.add(floor2);
    }

    public void onTick() {
        entities.forEach(s -> s.onTick(this));
        player.onTick(this);
    }
}
