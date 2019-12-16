package sample.Items.Pieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.Items.Board;
import sample.actions.Tiles;
import sample.actions.GetNodes;

import java.util.ArrayList;

public class RedPiece extends Piece {

    public ArrayList<RedPiece> setRedPieces()
    {
        for (short x = 0; x < 8; x++)
            for (short y = 7; y > 4; y--)
            {
                if (!Tiles.isLight(x, y))
                {
                    Circle circle = new Circle(20);
                    circle.setFill(Color.RED);
                    Board.getInstance().getBoard().add(circle, x, y);
                    GetNodes.getTile(x, y).setId(x + "" + y + " RED");
                    selectPiece(circle);
                }
            }
        return null;
    }
}
