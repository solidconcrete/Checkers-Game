package sample.actions;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Tiles {
    private Rectangle tile;

    public static boolean isLight(short x, short y)
    {
        if ((x % 2) == (y % 2))
        {
            return true;
        }
        else return false;
    }

}
