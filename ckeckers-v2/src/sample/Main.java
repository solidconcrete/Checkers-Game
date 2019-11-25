package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Main extends Application {
    Circle selectedPiece = null;
    int turn = -1;
    boolean locked = false;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        GridPane board = new GridPane();
        Circle pieces = new Circle();
        String[][] tileStatus = new String[8][8] ;
        drawBoard(board, tileStatus);
        setPieces(board, pieces, tileStatus);
        Scene scene = new Scene(board, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
}
    public void drawBoard(GridPane board,  String[][] tileStatus)
    {
        int color = 1;
        for (int i = 0; i < 8; i++)
        {
            color *= -1;
            for (int j = 0; j < 8; j++)
            {
                    Rectangle tile = new Rectangle(40, 40);
                if (color == 1)
                {
                    tile.setFill(Color.BLACK);
                }
                else
                {
                    tile.setFill(Color.ANTIQUEWHITE);
                }
                tileStatus[i][j] = "EMPTY";
                tile.setId("tile " + i + j);
                tile.setOnMouseClicked(e ->
                {
                    int newX = Integer.parseInt(String.valueOf(tile.getId().charAt(5)));
                    int newY = Integer.parseInt(String.valueOf(tile.getId().charAt(6)));



                    if (selectedPiece != null && tileStatus[newX][newY] == "EMPTY")
                    {
                        int oldX = GridPane.getColumnIndex(selectedPiece);
                        int oldY = GridPane.getRowIndex(selectedPiece);
                        tileStatus[oldX][oldY] = "EMPTY";

                        if(checkMove(tileStatus,oldX, oldY, newX, newY, board))
                        {
                            tileStatus[newX][newY] = selectedPiece.getId().replaceAll("\\d", "");
                            movePiece(board, newX, newY);
                            if (Math.abs(oldX - newX) == 2 && Math.abs(oldY - newY) == 2)
                            {
                                checkAround(tileStatus, newX, newY, board);
                            }
                        }
                    }
                    System.out.println(tileStatus[newX][newY]);
                });
                board.add(tile, i, j);
                color *= -1;
            }
        }
    }
    public void setPieces (GridPane board, Circle piece, String[][] tileStatus)
    {
        int counter = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if ((i + j) % 2 != 0)
                {
                    Circle circle = new Circle(20);
                    circle.setFill(Color.RED);
                    circle.setId("RED " + counter);
                    circle.setOnMouseClicked(e ->
                    {
                        if (turn == 1 && circle.getId().contains("RED") && !locked)
                        {
                            selectedPiece = circle;
                            System.out.println(selectedPiece.getId());
                            return;
                        }
                        else selectedPiece = null;
                    });

                     tileStatus[i][j] = "RED";
                     board.add(circle, i, j);
                     counter ++;

                }
            }
        }
        counter = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 7; j > 4; j--)
            {
                if ((i + j) % 2 != 0)
                {
                    Circle circle = new Circle(20);
                    circle.setFill(Color.IVORY);
                    circle.setId("WHITE " + counter);
                    circle.setOnMouseClicked(e ->
                    {
                        if (turn == -1 && circle.getId().contains("WHITE") && !locked)
                        {
                            selectedPiece = circle;
                            System.out.println(selectedPiece.getId());
                            return;
                        }
                        else selectedPiece = null;

                    });
                    tileStatus[i][j] = "WHITE";
                    board.add(circle, i, j);
                    counter ++;
                }
            }
        }
    }

    public void movePiece(GridPane board, int x, int y)
    {
        Circle tempCircle = selectedPiece;
        if( !tempCircle.getId().contains("QUEEN") && tempCircle.getId().contains("RED") && y == 7)
        {
            System.out.println("BIG GUNS");
            String tempId = tempCircle.getId();
            tempCircle.setId(tempId + " QUEEN");
        }
        board.getChildren().remove(selectedPiece);
        board.add(tempCircle, x, y);

        if (!locked)
        selectedPiece = null;
    }

    public boolean checkMove(String[][] tileStatus, int oldX, int oldY, int newX, int newY, GridPane board)
    {
//        if (selectedPiece.getId().contains("QUEEN") && Math.abs(oldX - newX) == Math.abs(oldY - newY) && tileStatus[newX][newY] == "EMPTY"
//            )
//        {
//            if (newX < oldX && newY < oldY)
//                for (int i = oldX; i < newX; i--)
//                {
//                    if (tileStatus[i][i] != "EMPTY" && tileStatus[i][i] != tileStatus[oldX][oldY])
//                    {
//                        Circle pieceToDelete = getPiece(i, i, board);
//                        board.getChildren().remove(pieceToDelete);
//                    }
//                }
////            if (newX < oldX && newY > oldY)
////                for (int i = oldX; i < newX; i--)
////                {
////                    if (tileStatus[i][i] != "EMPTY" && tileStatus[i][i] != tileStatus[oldX][oldY])
////                    {
////                        Circle pieceToDelete = getPiece(i, i, board);
////                        board.getChildren().remove(pieceToDelete);
////                    }
////                }
//            movePiece(board, newX, newY);
//            turn *= -1;
//
//        }
        if (((newX == oldX - 1 || newX == oldX + 1))
                && (newY == oldY + 1 || newY == oldY - 1)
                && (tileStatus[newX][newY] == "EMPTY") && selectedPiece.getId().contains("QUEEN"))
        {
            turn *= -1;
            locked = false;
            return true;
        }
        if (((newX == oldX - 1 || newX == oldX + 1))
                && (((newY == oldY - 1 && selectedPiece.getId().contains("WHITE")) || (newY == oldY + 1 && selectedPiece.getId().contains("RED"))))
                && (tileStatus[newX][newY] == "EMPTY"))
        {
            turn *= -1;
            locked = false;
            return true;
        }

        if ((tileStatus[newX][newY] == "EMPTY") && newX == oldX - 2 && newY == oldY - 2  && (tileStatus[oldX - 1][oldY - 1] != tileStatus[oldX][oldY]
                && tileStatus[oldX - 1][oldY - 1] != "EMPTY"))
        {
            board.getChildren().remove(getPiece(oldX -1, oldY - 1, board));
            tileStatus[oldX-1] [oldY-1] = "EMPTY";
            return  true;
        }
        if ((tileStatus[newX][newY] == "EMPTY") && newX == oldX + 2 && newY == oldY - 2  && (tileStatus[oldX + 1][oldY - 1] != tileStatus[oldX][oldY]
                && tileStatus[oldX + 1][oldY - 1] != "EMPTY"))
        {
            board.getChildren().remove(getPiece(oldX +1, oldY - 1, board));
            tileStatus[oldX + 1] [oldY - 1] = "EMPTY";
            return  true;
        }
        if ((tileStatus[newX][newY] == "EMPTY") && newX == oldX - 2 && newY == oldY + 2  && (tileStatus[oldX - 1][oldY + 1] != tileStatus[oldX][oldY]
                && tileStatus[oldX - 1][oldY + 1] != "EMPTY"))
        {
            board.getChildren().remove(getPiece(oldX -1, oldY + 1, board));
            tileStatus[oldX - 1] [oldY + 1] = "EMPTY";
            return  true;
        }
        if ((tileStatus[newX][newY] == "EMPTY") && newX == oldX + 2 && newY == oldY + 2  && (tileStatus[oldX + 1][oldY + 1] != tileStatus[oldX][oldY]
                && tileStatus[oldX + 1][oldY + 1] != "EMPTY"))
        {
            board.getChildren().remove(getPiece(oldX + 1, oldY + 1, board));
            tileStatus[oldX + 1] [oldY + 1] = "EMPTY";
            return  true;
        }
        return false;
    }
    public void checkAround (String[][] tileStatus, int oldX, int oldY, GridPane board)
    {


        boolean one = false, two = false, three = false, four = false;
        if (oldX != 0 && oldX !=1 && oldY != 0 && oldY != 1)
        {
           if (checkMove(tileStatus, oldX, oldY, oldX -2, oldY - 2, board) == true)
               one = true;

        }
        if (oldX != +6 && oldX !=7 && oldY != 0 && oldY != 1)
        {
            if (checkMove(tileStatus, oldX, oldY, oldX +2, oldY - 2, board) == true)
             two = true;
        }
        if (oldX != 0 && oldX !=1 && oldY != 6 && oldY != 7)
        {
            if (checkMove(tileStatus, oldX, oldY, oldX -2, oldY + 2, board))
            three = true;
        }
        if (oldX != 6 && oldX !=7 && oldY != 6 && oldY != 7)
        {
           if (checkMove(tileStatus, oldX, oldY, oldX + 2, oldY + 2, board))
               four = true;
        }
        if (one == false && two == false & three ==false && four == false)
        {
            turn *= -1;
            System.out.println("No moves available");
        }
    }

    public Circle getPiece(int x, int y, GridPane board)
    {
        String col;
        if (selectedPiece.getId().charAt(0)=='W')
        {
            col = "RED";
        }
        else col = "WHITE";
        for (Node node : board.getChildren())
        {
            if(node.getId().charAt(0) != 't' && node.getId().contains(col) && GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y)
                return (Circle) node;
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
