package sample;

import sample.Items.Pieces.Piece;
import sample.actions.GetNodes;
import sample.gameStats;

public class Validations {

    public static void checkRedMove(short x, short y)
    {

        if ((GetNodes.getColumn(Piece.getSelectedPiece()) == x - 1 || (GetNodes.getColumn(Piece.getSelectedPiece()) == x + 1))
        && GetNodes.getRow(Piece.getSelectedPiece()) == y + 1 && GetNodes.getTile(Piece.getSelectedPiece()).getId().contains("RED"))
        {
            Piece.movePiece(x, y);
            gameStats.getInstance().nextTurn();
        }
    }

    public static void checkWhiteMove(short x, short y)
    {
        if ((GetNodes.getColumn(Piece.getSelectedPiece()) == x - 1 || (GetNodes.getColumn(Piece.getSelectedPiece()) == x + 1))
        && GetNodes.getRow(Piece.getSelectedPiece()) == y - 1 && GetNodes.getTile(Piece.getSelectedPiece()).getId().contains("WHITE"))
        {
            Piece.movePiece(x, y);
            gameStats.getInstance().nextTurn();
        }
    }

    public static void checkRedEats(short x, short y)
    {
        short oldX = (short) GetNodes.getColumn(Piece.getSelectedPiece());
        short oldY = (short) GetNodes.getRow(Piece.getSelectedPiece());
        if (Math.abs(oldX- x) == 2
                && Math.abs(oldY - y) == 2
                && x >=0 && x <= 7 && y >= 0 && y <= 7
                && GetNodes.getTile((oldX + x) / 2, (oldY + y) / 2).getId().contains("WHITE")
                )
        {
            if ((checkAroundRed(x, y, x+2, y+2) || checkAroundRed(x, y, x-2, y+2)
                    || checkAroundRed(x, y, x+2, y-2) || checkAroundRed (x, y, x-2, y-2)) == false)
            {
                gameStats.getInstance().nextTurn();
            }
            Piece.eatPiece(oldX, oldY, x, y);
        }
    }

    public static void checkWhiteEats(short x, short y)
    {
        short oldX = (short) GetNodes.getColumn(Piece.getSelectedPiece());
        short oldY = (short) GetNodes.getRow(Piece.getSelectedPiece());
        if (Math.abs(oldX- x) == 2
                && Math.abs(oldY - y) == 2
                && x >=0 && x <= 7 && y >= 0 && y <= 7
                && GetNodes.getTile((oldX + x) / 2, (oldY + y) / 2).getId().contains("RED")
        )
        {
            Piece.eatPiece(oldX, oldY, x, y);
            gameStats.getInstance().eatRed();
            System.out.println(gameStats.getInstance().getReds());
        }
    }

    public static Boolean checkAroundRed(short oldX, short oldY, int x, int y)
    {
        if (    x >=0 && x <= 7 && y >= 0 && y <= 7
                && GetNodes.getTile(x, y).getId().contains("EMPTY")
                && GetNodes.getTile((oldX + x) / 2, (oldY + y) / 2).getId().contains("WHITE")
        )
        {
            return true;
        }
        return false;
    }
}
