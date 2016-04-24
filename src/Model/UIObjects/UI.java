package Model.UIObjects;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class UI {
    public ArrayList<IUIObject> uiElements = new ArrayList<>();

    public void initUI(){
        StatusBar bar1 = new StatusBar(Color.BLUE, Color.RED, new Point2D(100, 10), 50, 10);
        bar1.setPercent(0.75f);

        uiElements.add(bar1);
    }
}
