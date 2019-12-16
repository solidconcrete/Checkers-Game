package sample.Items.Pieces;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Items.Board;
import sample.actions.GetNodes;
import sample.gameStats;

public class Piece {
    private static Circle selectedPiece;

    public void selectPiece (Circle piece)
    {
        piece.setOnMouseClicked(e ->
        {
            if (gameStats.getInstance().getTurn() == 1 && GetNodes.getTile(piece).getId().contains("RED"))
            {
                this.selectedPiece = piece;
                System.out.println("SELECTED RED");
            }
            else if (gameStats.getInstance().getTurn() == -1 && GetNodes.getTile(piece).getId().contains("WHITE"))
            {
                this.selectedPiece = piece;
                System.out.println("SELECTED WHITE");
            }
        });
    }

    public static Circle getSelectedPiece() {
        return selectedPiece;
    }

    public static void movePiece(short x, short y)
    {
        short oldX = (short) GetNodes.getColumn(selectedPiece), oldY = (short) GetNodes.getRow(selectedPiece);
        String col = getSelectedPieceColor();
        removePiece(oldX, oldY, selectedPiece);
        addPiece(x, y, col);
    }

    public static void eatPiece(short oldX, short oldY, short x, short y)
    {
        short toEatX = (short)((oldX + x) / 2);
        short toEatY = (short)((oldY + y) / 2);
        String col = getSelectedPieceColor();
        Circle pieceToEat = GetNodes.getPiece(toEatX , toEatY);
        removePiece(toEatX, toEatY, pieceToEat);
        removePiece(oldX, oldY, selectedPiece);
        addPiece(x, y, col);
    }

    public static void removePiece(short x, short y, Circle piece)
    {
        GetNodes.getTile(x, y).setId(x + "" + y + " EMPTY");
        Board.getInstance().getBoard().getChildren().remove(piece);
    }

    public static void addPiece(short x, short y, String col)
    {
        GetNodes.getTile(x, y).setId(x + "" + y + col);
        Board.getInstance().getBoard().add(selectedPiece, x, y);
    }

    private static String getSelectedPieceColor()
    {
       String col = GetNodes.getTile(selectedPiece).getId().substring(2);
       System.out.println(col);
       return col;
    }

    private static String getPieceColor(Circle piece)
    {
        String col = GetNodes.getTile(piece).getId().substring(2);
        System.out.println(col);
        return col;
    }
}
