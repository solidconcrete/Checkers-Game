package sample.Items.Pieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.Items.Board;
import sample.actions.Tiles;
import sample.actions.GetNodes;


import java.util.ArrayList;

public class WhitePiece extends Piece{

    public ArrayList<RedPiece> setRedPieces()
    {
        for (short x = 0; x < 8; x++)
            for (short y = 0; y < 3; y++)
            {
                if (!Tiles.isLight(x, y))
                {
                    Circle circle = new Circle(20);
                    circle.setFill(Color.FLORALWHITE);
                    Board.getInstance().getBoard().add(circle, x, y);
                    GetNodes.getTile(x, y).setId(x + "" + y + " WHITE");
                    selectPiece(circle);
                }
            }
        return null;
    }
}
