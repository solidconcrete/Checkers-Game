package sample.actions;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Items.Board;

public class GetNodes {
    public static Rectangle getTile(int x, int y)
    {
        for (Node node : Board.getInstance().getBoard().getChildren())
        {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y)
            {
                return (Rectangle) node;
            }
        }
        System.out.println("Could not get Tile by coordinates");
        return null;
    }
    public static Rectangle getTile (Circle piece)
    {
        return getTile(GridPane.getColumnIndex(piece), GridPane.getRowIndex(piece));

    }

    public static Circle getPiece(int x, int y)
    {
        for (Node node : Board.getInstance().getBoard().getChildren())
        {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y && node.getClass().equals(Circle.class))
            {
                return (Circle) node;
            }
        }
        System.out.println("Could not get Tile by coordinates");
        return null;
    }
    public static int getColumn(Circle piece)
    {
        return GridPane.getColumnIndex(piece);
    }

    public static int getRow(Circle piece)
    {
        return GridPane.getRowIndex(piece);
    }
}
