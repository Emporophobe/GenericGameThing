package Model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        this.bottomRight = topLeft.add(width, height);
    }

    public Point2D getTopLeft() {
        return this.topLeft;
    }

    private int getWidth() {
        return (int) (this.bottomRight.getX() - this.topLeft.getX());
    }

    private int getHeight() {
        return (int) (this.bottomRight.getY() - this.topLeft.getY());
    }

    /**
     * Move the AABB to have a new top left corner and the same size
     *
     * @param newTopLeft The new top left corner
     */
    public void moveTo(Point2D newTopLeft) {
        Point2D diag = this.bottomRight.subtract(this.topLeft);
        this.topLeft = newTopLeft;
        this.bottomRight = this.topLeft.add(diag);
    }

    /**
     * Determine whether two AABBs are overlapping
     *
     * @param other The other AABB
     * @return If they the boxes overlap
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

        // If two AABBs don't overlap then there is a gap
        // between the side of one and the opposite side of the other
        return (firstLeft < secondRight &&
                firstRight > secondLeft &&
                firstTop < secondBottom &&
                firstBottom > secondTop);
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
