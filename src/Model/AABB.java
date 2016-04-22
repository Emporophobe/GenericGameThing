package Model;

import javafx.geometry.Point2D;

/**
 * Represents an Aligned-Axis Bounding Box, used to represent a rectangular region that completely contains an entity
 */
public class AABB {
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
    public AABB(Point2D topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.bottomRight = new Point2D(topLeft.getX() + width, topLeft.getY() + height);
    }

    public Point2D getTopLeft() {
        return this.topLeft;
    }

    public int getWidth() {
        return (int) (this.bottomRight.getX() - this.topLeft.getX());
    }

    public int getHeight() {
        return (int) (this.bottomRight.getY() - this.topLeft.getY());
    }

    /**
     * Move the AABB to have a new top left corner and the same size
     *
     * @param newTopLeft The new top left corner
     */
    public void moveTo(Point2D newTopLeft) {
        Point2D diag = this.topLeft.subtract(this.bottomRight);
        this.topLeft = newTopLeft;
        this.bottomRight = this.topLeft.add(diag);
    }

    /**
     * Determine whether two AABBs are overlapping
     *
     * @param other The other AABB
     * @return True if they the boxes overlap
     */
    public boolean overlaps(AABB other) {

        // Get the separate x and y coordinates of both AABBs
        double firstLeft = this.topLeft.getX();
        double firstRight = this.bottomRight.getX();
        double firstTop = this.topLeft.getY();
        double firstBottom = this.bottomRight.getY();

        double secondLeft = other.topLeft.getX();
        double secondRight = other.bottomRight.getX();
        double secondTop = other.topLeft.getY();
        double secondBottom = other.bottomRight.getY();

        // If two AABBs overlap, the top or bottom of one will be within the other's x-range,
        // and the left or right of one will be within the other's y-range
        boolean collided = (firstLeft < secondRight &&
                firstRight > secondLeft &&
                firstTop < secondBottom &&
                firstBottom > secondTop);
        return collided;
    }
}
