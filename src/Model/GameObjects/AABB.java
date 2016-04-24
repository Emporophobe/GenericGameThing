package Model.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents an Aligned-Axis Bounding Box, used to represent a rectangular region that completely contains an entity
 */
class AABB {
    private Point2D topLeft;
    private Point2D bottomRight;

    /**
     * Create an AABB from two points
     *
     * @param topLeft     The top left corner of the AABB
     * @param bottomRight The bottom right corner of the AABB
     */
    AABB(Point2D topLeft, Point2D bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * Create an AABB from a point and size
     *
     * @param topLeft The top left corner of the AABB
     * @param width   The width of the AABB
     * @param height  The height of the AABB
     */
    AABB(Point2D topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.bottomRight = topLeft.add(width, height);
    }

    Point2D getTopLeft() {
        return topLeft;
    }

    Point2D getBottomRight(){
        return bottomRight;
    }

    private int getWidth() {
        return (int) (bottomRight.getX() - topLeft.getX());
    }

    private int getHeight() {
        return (int) (bottomRight.getY() - topLeft.getY());
    }

    public Point2D getBottomLeft(){
        return new Point2D(topLeft.getX(), topLeft.getY() + getHeight());
    }

    public Point2D getTopRight(){
        return new Point2D(topLeft.getX() + getWidth(), topLeft.getY());
    }

    /**
     * Move the AABB to have a new position and the same size
     *
     * @param newTopLeft The new top left corner
     */
    void moveTo(Point2D newTopLeft) {
        Point2D diag = bottomRight.subtract(topLeft);
        topLeft = newTopLeft;
        bottomRight = topLeft.add(diag);
    }

    /**
     * Move the AABB to have a new position and the same size
     * @param newX The new X-coordinate of the top left corner
     * @param newY The new Y-coordinate of the top left corner
     */
    public void moveTo(int newX, int newY){
        moveTo(new Point2D(newX, newY));
    }

    /**
     * Move the AABB by a given change in x and y
     * @param dx The x distance to move, in pixels
     * @param dy The y distance to move, in pixels
     */
    void moveBy(int dx, int dy){
        moveTo(topLeft.add(dx, dy));
    }

    /**
     * Move the AABB as far as possible in the given direction without hitting the other AABB
     * @param velocity The velocity vector to move along (can be unit vector or any magnitude)
     * @param other The other AABB
     * @return The new TopLeft corner of the moving AABB
     */
    Point2D moveTowards(Point2D velocity, AABB other){
        Point2D vBar = velocity.normalize();
        Point2D testPoint = getTopLeft();
        Point2D oldTopLeft = testPoint;
        Point2D newTopLeft = testPoint;
        Point2D prevTopLeft = testPoint;

        // if we are (close to) not moving we would get stuck in an infinite loop
        if (vBar == new Point2D(0, 0)){
            return oldTopLeft;
        }

        while(true){
            testPoint = testPoint.add(vBar);
            newTopLeft = new Point2D((int) testPoint.getX(), (int) testPoint.getY());
            moveTo(newTopLeft);
            if(overlaps(other)){
                moveTo(prevTopLeft);
                return prevTopLeft;
            }
            prevTopLeft = newTopLeft;
        }
//        while(!overlaps(other)){
//            testPoint = testPoint.add(vBar);
//            newTopLeft = new Point2D((int) testPoint.getX(), (int) testPoint.getY());
//            moveTo(newTopLeft);
// //           System.out.println("Testing point "+ testPoint.getX() + ", " + testPoint.getY());
//        }
//        System.out.println("(" + oldTopLeft.getX() + ", " + oldTopLeft.getY() + ") -> (" +
//                newTopLeft.getX() + ", " + newTopLeft.getY() + ")");
//        return newTopLeft;
    }

    /**
     * Determine whether two AABBs are overlapping
     *
     * @param other The other AABB
     * @return If they the boxes overlap
     */
    boolean overlaps(AABB other) {

        // Get the separate x and y coordinates of both AABBs
        double firstLeft = topLeft.getX();
        double firstRight = bottomRight.getX();
        double firstTop = topLeft.getY();
        double firstBottom = bottomRight.getY();

        double secondLeft = other.topLeft.getX();
        double secondRight = other.bottomRight.getX();
        double secondTop = other.topLeft.getY();
        double secondBottom = other.bottomRight.getY();

        // If two AABBs don't overlap then there is a gap
        // between the side of one and the opposite side of the other
        return (firstLeft < secondRight &&
                firstRight > secondLeft &&
                firstTop < secondBottom &&
                firstBottom > secondTop);
    }

    /**
     * Get the distance between the closest edges of this and another AABB
     * @param other The other AABB
     * @return The
     */
    Point2D distanceTo(AABB other){
        int deltaX = (int) Math.min(topLeft.getX() - other.getBottomRight().getX(),
                                    bottomRight.getX() - other.getTopLeft().getX());
        int deltaY = (int) Math.min(topLeft.getY() - other.getBottomRight().getY(),
                                    bottomRight.getY() - other.getTopLeft().getY());

        System.out.println(deltaX + ", " + deltaY);
        return new Point2D(deltaX, deltaY);
    }

    /**
     * Fill in the area covered by the AABB. This should only be used for debugging
     * @param gc The Graphics Context to draw on
     */
    public void draw(GraphicsContext gc)
    {
        gc.setFill(Color.GRAY);
        gc.fillRect(topLeft.getX(), topLeft.getY(), getWidth(), getHeight());
    }
}
