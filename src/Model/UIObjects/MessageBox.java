package Model.UIObjects;

import Model.GameObjects.World;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class MessageBox implements IUIObject{
    private String message;
    private Font font;

    private Point2D upperLeft;
    private int width;
    private int height;

    private Color bgColor;
    private Color textColor;

    private int xOffset = 10;
    private int yOffset = 10;

    MessageBox(String message,
               Font font,
               Point2D upperLeft,
               int width,
               int height,
               Color bgColor,
               Color textColor){
        this.message = message;
        this.font = font;
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    MessageBox(String message,
               Font font,
               Point2D upperLeft,
               int width,
               int height,
               Color bgColor,
               Color textColor,
               int xOffset,
               int yOffset){
        this(message, font, upperLeft, width, height, bgColor, textColor);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(bgColor);
        gc.fillRect(upperLeft.getX(), upperLeft.getY(), width, height);

        gc.setTextBaseline(VPos.TOP);
        gc.setFill(textColor);
        gc.setFont(font);
        gc.fillText(message, upperLeft.getX() + xOffset, upperLeft.getY() + yOffset);
    }

    @Override
    public void onTick(World w) {

    }

    void setMessage(String message){
        this.message = message;
    }
}
