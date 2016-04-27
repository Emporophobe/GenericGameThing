package Model.GameObjects;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<IGameObject> entities = new ArrayList<>();
    private Living player = new Player();
    private List<Living> badGuys = new ArrayList<>();

    private List<String> messages = new ArrayList<>();

    public World() {
        this.initWorld();
    }

    public void initWorld() {
        getMessages().clear();

        Floor floor1 = new Floor(new Point2D(10, 10), 60, 15);
        Floor floor2 = new Floor(new Point2D(50, 50), 100, 100);
        Floor floor3 = new Floor(new Point2D(0, 180), 400, 30);
        Floor floor4 = new Floor(new Point2D(350, 100), 10, 70);
        Floor floor5 = new Floor(new Point2D(400, 150), 300, 10);

        Enemy e1 = new Enemy();
        getBadGuys().clear();

        getBadGuys().add(e1);

        setPlayer(new Player());

        entities.clear();

        entities.add(floor1);
        entities.add(floor2);
        entities.add(floor3);
        entities.add(floor4);
        entities.add(floor5);

        getEntities().add(getPlayer());
//
//        entities.addAll(badGuys);
    }

    public void onTick() {
        getEntities().forEach(e -> e.onTick(this));
//        player.onTick(this);
        getBadGuys().forEach((b -> b.onTick(this)));
    }

    public List<IGameObject> getEntities() {
        return entities;
    }

    public Living getPlayer() {
        return player;
    }

    private void setPlayer(Living player) {
        this.player = player;
    }

    public List<Living> getBadGuys() {
        return badGuys;
    }

    public List<String> getMessages() {
        return messages;
    }

}
