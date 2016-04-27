package Model.UIObjects;

import Model.GameObjects.World;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class StatusBar implements IUIObject{
    private float percent;
    private Color fgColor;
    private Color bgColor;
    private int width;
    private int height;
    private Point2D upperLeft;

    StatusBar(Color fgColor, Color bgColor, Point2D upperLeft, int width, int height){
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.percent = 100;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(bgColor);
        gc.fillRect(upperLeft.getX(), upperLeft.getY(), width, height);
        gc.setFill(fgColor);
        gc.fillRect(upperLeft.getX(), upperLeft.getY(), width * percent, height);
    }

    @Override
    public void onTick(World w) {

    }

    void setPercent(float percent){
        this.percent = percent;
    }

    void setPercent(int value, int maxValue){
        this.percent = (float) value / maxValue;
    }
}
