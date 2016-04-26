package Model.GameObjects;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class World {
    public ArrayList<IGameObject> entities = new ArrayList<>();
    public Living player = new Player();
    public ArrayList<Living> badGuys = new ArrayList<>();

    public World() {
        this.initWorld();
    }

    public void initWorld() {
        IGameObject floor1 = new Floor(new Point2D(10, 10), 60, 15);
        IGameObject floor2 = new Floor(new Point2D(50, 50), 100, 100);
        IGameObject floor3 = new Floor(new Point2D(0, 180), 400, 30);
        Floor floor4 = new Floor(new Point2D(350, 100), 10, 70);

        Enemy e1 = new Enemy();
        badGuys.clear();

        badGuys.add(e1);

        player = new Player();

        entities.clear();

        entities.add(floor1);
        entities.add(floor2);
        entities.add(floor3);
        entities.add(floor4);

        entities.add(player);
//
//        entities.addAll(badGuys);
    }

    public void onTick() {
        entities.forEach(e -> e.onTick(this));
//        player.onTick(this);
        badGuys.forEach((b -> b.onTick(this)));
    }
}
