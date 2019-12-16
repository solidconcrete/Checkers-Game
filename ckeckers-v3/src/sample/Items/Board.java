package sample.Items;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Items.Pieces.Piece;
import sample.Validations;
import sample.actions.GetNodes;
import sample.actions.Tiles;
import sample.gameStats;

public class Board {
    private static Board single_instance = null;
    private GridPane board;
    private Rectangle clickedTile;
    private Board()
    {
        board = new GridPane();
    }

    public static Board getInstance()
    {
        if (single_instance == null)
            single_instance = new Board();
        return single_instance;
    }

    public void drawBoard()
    {
        for (short x = 0; x < 8; x++)
            for (short y = 0; y < 8; y++)
            {
                Rectangle tile = new Rectangle(40,40);
                if (Tiles.isLight(x, y))
                {
                    tile.setFill(Color.ANTIQUEWHITE);
                }
                else
                {
                    tile.setFill(Color.BLACK);
                }
                tile.setId(x + "" + y + " EMPTY");
                board.add(tile, x, y);
                selectTile(tile);
            }
    }

    public void selectTile(Rectangle clickedTile)
    {
        clickedTile.setOnMouseClicked(e ->
        {
            this.clickedTile = clickedTile;
            System.out.println(clickedTile.getId());
            if (Piece.getSelectedPiece() != null)
            {
                Validations.checkRedMove(Short.parseShort(String.valueOf(clickedTile.getId().charAt(0))),
                        Short.parseShort(String.valueOf(clickedTile.getId().charAt(1))));
                Validations.checkWhiteMove(Short.parseShort(String.valueOf(clickedTile.getId().charAt(0))),
                        Short.parseShort(String.valueOf(clickedTile.getId().charAt(1))));
                Validations.checkRedEats(Short.parseShort(String.valueOf(clickedTile.getId().charAt(0))),
                        Short.parseShort(String.valueOf(clickedTile.getId().charAt(1))));
                Validations.checkWhiteEats(Short.parseShort(String.valueOf(clickedTile.getId().charAt(0))),
                        Short.parseShort(String.valueOf(clickedTile.getId().charAt(1))));
                System.out.println(gameStats.getInstance().getTurn());
            }
        });
    }

    public GridPane getBoard() {
        return board;
    }
}
