package Model.GameObjects;

import Model.Directions;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Living implements IPhysicsObject {

    private Point2D velocity = new Point2D(0, 0);
    int width;
    int height;
    private AABB boundingBox;
    Point2D position;
    int speed;
    boolean collided = false;
    private List<Directions> validDirections = new ArrayList<>();
    Directions facing;

    private int maxHealth;
    private int health;

    boolean isDead = false;

    ArrayList<String> consoleMessages = new ArrayList<>();

    Living(int width, int height, Point2D position, int speed, int maxHealth){
        this.width = width;
        this.height = height;
        this.position = position;
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.health = maxHealth;

        boundingBox = new AABB(position, width, height);
    }

    /**
     * Draw the alive
     * @param gc The GraphicsContext to draw on
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(position.getX(), position.getY(), width, height);
    }

    /**
     * Check if the alive is colliding with a different object
     * @param other The object to check against
     * @return Whether the two are colliding
     */
    @Override
    public boolean collidedWith(IGameObject other) {
        return other != this && boundingBox.overlaps(other.getAABB());
    }

    /**
     * Get the AABB for the Living
     * @return The bounding box
     */
    @Override
    public AABB getAABB() {
        return boundingBox;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public int getHealth(){
        return health;
    }

    public void damage(int amount){
        health -= amount;
        if (health <= 0){
            consoleMessages.add("> Process \"User\" terminated with exit code 1");
        }
    }


    private Point2D impulse = new Point2D(0, 0);

    public void setImpulse(Point2D impulse) {
        this.impulse = impulse;
    }

    @Override
    public void onTick(World w) {
        if (health > 0) {
            validDirections = getValidDirections(w);
            aiTurn(w);
            setDirection(impulse);
            gravity();
            move(w);
            resolveAttacks(w);

            w.getMessages().addAll(consoleMessages);
            consoleMessages.clear();
        }
    }

    void aiTurn(World w) {

    }

    @Override
    public void gravity() {
        int g = 2;
        if(validDirections.contains(Directions.DOWN)) {
            velocity = velocity.add(0, g);
        }
        else if (velocity.getY() > 0){
            velocity = new Point2D(velocity.getX(), 0);
        }
    }

    public void jump()
    {
        if(!validDirections.contains(Directions.DOWN) &&
                validDirections.contains(Directions.UP)){
            velocity = velocity.add(0, -10);
            //consoleMessages.add("> Jump()");
        }
    }

    private void setDirection(Point2D direction){
        if (direction.magnitude() == 0){
            velocity = new Point2D(0, velocity.getY());
        }
        else if (velocity.magnitude() <= speed){
            velocity = direction.multiply(speed);
            if (velocity.getX() < 0){
                facing = Directions.LEFT;
            }
            else{
                facing = Directions.RIGHT;
            }
        }
    }



    private List<Directions> getValidDirections(World w){
        List<Directions> dirs = new ArrayList<>();
        AABB testAABB = getAABB();

        testAABB.moveBy(0, -1);
        if (!w.getEntities().stream().anyMatch(this::collidedWith)){
            dirs.add(Directions.UP);
        }
        testAABB.moveBy(0, 2);
        if (!w.getEntities().stream().anyMatch(this::collidedWith)){
            dirs.add(Directions.DOWN);
        }
        testAABB.moveBy(-1, -1);
        if (!w.getEntities().stream().anyMatch(this::collidedWith)){
            dirs.add(Directions.LEFT);
        }
        testAABB.moveBy(2, 0);
        if (!w.getEntities().stream().anyMatch(this::collidedWith)){
            dirs.add(Directions.RIGHT);
        }

        return dirs;
    }

    private void move(World w) {

        // make sure we don't try to move into walls
        if(!validDirections.contains(Directions.LEFT) &&
                velocity.getX() < 0){
            velocity = new Point2D(0, velocity.getY());
        }
        else if(!validDirections.contains(Directions.RIGHT) &&
                velocity.getX() > 0){
            velocity = new Point2D(0, velocity.getY());
        }
        if (!validDirections.contains(Directions.UP) &&
                velocity.getY() < 0){
            velocity = new Point2D(velocity.getX(), 0);
        }
        else if(!validDirections.contains(Directions.DOWN) &&
                velocity.getY() > 0){
            velocity = new Point2D(velocity.getX(), 0);
        }

        Point2D newPosition = position.add(velocity);
        boundingBox.moveTo(newPosition);

        collided = false;
        ArrayList<IGameObject> collisionTargets = new ArrayList<>();
        w.getEntities().stream().filter(this::collidedWith).forEach(x -> {
            collided = true;
            collisionTargets.add(x);
        });

        if (!collided) {
            position = newPosition;
            boundingBox.moveTo(position);
        } else {
            boundingBox.moveTo(position);
            for (IGameObject x : collisionTargets){
                position = boundingBox.moveTowards(velocity, x.getAABB());
            }
        }
    }

    /**
     * Get damage from any attacks overlapping us and remove non-piercing attacks
     * @param w The world
     */
    private void resolveAttacks(World w){
        for (Iterator<Attack> i = w.getAttacks().iterator(); i.hasNext();){
            Attack a = i.next();

            if (a.collidedWith(this)) {
                health -= a.getRawDamage();

                if (a.isPiercing()) {
                    i.remove();
                }
            }
        }
    }

}
