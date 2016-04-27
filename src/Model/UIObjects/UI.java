package Model.UIObjects;

import Model.GameObjects.World;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class UI {
    private static final int screenWidth = 800;
    private static final int screenHeight = 600;

    private List<IUIObject> uiElements = new ArrayList<>();

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public void initUI(){
        HealthBar healthBar = new HealthBar(Color.RED, Color.DARKRED, new Point2D(650, 520), 100, 10);
        ConsoleMessageBox console = new ConsoleMessageBox(5);

        uiElements.add(console);
        uiElements.add(healthBar);
    }

    public void onTick(World w){
        uiElements.forEach(u -> u.onTick(w));
    }

    public List<IUIObject> getUiElements() {
        return uiElements;
    }

    public void setUiElements(List<IUIObject> uiElements) {
        this.uiElements = uiElements;
    }
}
