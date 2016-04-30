package Model.GameObjects;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class World {
    private long currentTick = 0;

    private List<IGameObject> entities = new ArrayList<>();
    private List<IGameObject> surfaces = new ArrayList<>();
    private Player player = new Player();
    private List<Living> badGuys = new ArrayList<>();
    private List<Attack> attacks = new ArrayList<>();

    private List<String> messages = new ArrayList<>();

    public World() {
        this.initWorld();
    }

    public void initWorld() {
        currentTick = 0;

        messages.clear();
        attacks.clear();

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

        surfaces.add(floor1);
        surfaces.add(floor2);
        surfaces.add(floor3);
        surfaces.add(floor4);
        surfaces.add(floor5);

        entities.add(player);
        entities.addAll(surfaces);
        //entities.addAll(badGuys);
//
//        entities.addAll(badGuys);
    }

    public void onTick() {
        entities.forEach(e -> e.onTick(this));
//        player.onTick(this);
        badGuys.forEach((b -> b.onTick(this)));
        attacks.forEach(a -> a.onTick(this));

        currentTick++;
    }

    long getCurrentTick(){
        return currentTick;
    }

    public List<IGameObject> getEntities() {
        return entities;
    }

    public Player getPlayer() {
        return player;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    public List<Living> getBadGuys() {
        return badGuys;
    }

    public List<String> getMessages() {
        return messages;
    }

    public List<Attack> getAttacks(){
        return attacks;
    }

    void addAttack(Attack p){
        attacks.add(p);
    }

    public void removeAttack(Attack p){
        attacks.remove(p);
    }

}
