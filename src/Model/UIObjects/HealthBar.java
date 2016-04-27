package Model.UIObjects;

import Model.GameObjects.World;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


class HealthBar extends StatusBar {

    HealthBar(Color fgColor, Color bgColor, Point2D upperLeft, int width, int height) {
        super(fgColor, bgColor, upperLeft, width, height);
    }

    @Override
    public void onTick(World w){
        setPercent(w.getPlayer().getHealth(), w.getPlayer().getMaxHealth());
    }
}
