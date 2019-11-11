package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;


import java.util.Scanner;

public class Main extends Application {
    String selectedPiece = null;
    int selectedPieceX, selectedPieceY;
    int boardData[][] = new int[8][8];
    String  boardData2[][] = new String[8][8];
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Checkers Game");

        GridPane board = new GridPane();
        Rectangle tile[][] = new Rectangle[8][8];
        Circle pieces[][] = new Circle[2][12];
        drawBoard(board, tile, pieces);

        board.getChildren();
        setPieces(board, pieces);
        Scene scene = new Scene(board, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        for( int i = 0; i<8;i++)
        {
            System.out.println();
            for (int j = 0; j<8; j++)
            {
                System.out.println(boardData2[j][i]);
            }
        }
//        pieces[1][2].addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                System.out.println(mouseEvent.getSource());
//                  Circle source = (Circle) mouseEvent.getSource();
//                  String id = source.getId();
//                  Circle circle = (Circle) scene.lookup("#"+ id);
//                  System.out.println();
//            }
//        });

    }


    public void drawBoard(GridPane board, Rectangle[][] tile, Circle pieces[][])
    {
        int counter = 0;
        int color = 1;
        for (int i = 0; i < 8; i++)
        {
            color *= -1;
            for (int j = 0; j < 8; j++)
            {
                Rectangle rectangle = new Rectangle(40, 40);
                if (color == 1)
                {
                    rectangle.setFill(Color.BLACK);
                }
                else
                {
                    rectangle.setFill(Color.WHITESMOKE);
                }
                boardData2[i][j] = "EMPTY";
                rectangle.setId("tile " + +i+j);
                rectangle.setOnMouseClicked(e ->
                {
                    System.out.println(rectangle.getId());
                    if (selectedPiece!=null)
                    {
                        int x, y;
                        y = GridPane.getRowIndex(rectangle);
                        x = GridPane.getColumnIndex(rectangle);
                        if (checkTile(x, y, board, pieces))
                        {
                            movePiece(board, pieces, x, y);
                            selectedPiece = null;
                        }
                    }
                });
                tile[i][j] = rectangle;
                board.add(tile[i][j], i, j);
                color *= -1;
            }

        }
    }


    void setPieces(GridPane board, Circle pieces[][])
    {
        int counter = 0;
        for( int i = 0; i < 8; i++)
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
                        selectedPiece = circle.getId();
                        System.out.println(circle.getId());
                        selectedPieceY = GridPane.getRowIndex(circle);
                        selectedPieceX = GridPane.getColumnIndex(circle);
//                        System.out.println(boardData[x][y]);
                        if(e.getButton() == MouseButton.MIDDLE)
                        {
                            board.getChildren().remove(circle);
                        }
                    });
                    boardData[i][j] = 1;
                    boardData2[i][j] = circle.getId();
                    pieces[0][counter] = circle;
                    board.add(pieces[0][counter], i, j);
                    counter++;
                }

            }
        }
        counter = 0;
        for( int i = 0; i < 8; i++)
        {
            for (int j = 7; j > 4; j--)
            {
                if ((i + j) % 2 != 0)
                {
                    Circle circle = new Circle(20);
                    circle.setFill(Color.ANTIQUEWHITE);
                    circle.setId("WHITE " + counter);
                    circle.setOnMouseClicked(e ->
                    {
                        selectedPiece = circle.getId();
                        System.out.println(board.getChildren().indexOf(circle));
                        System.out.println(circle.getId());
//                        int y = GridPane.getRowIndex(circle);
//                        int x = GridPane.getColumnIndex(circle);
//                        System.out.println(boardData[x][y]);
                        selectedPieceY = GridPane.getRowIndex(circle);
                        selectedPieceX = GridPane.getColumnIndex(circle);
                        if(e.getButton() == MouseButton.MIDDLE)
                        {
                            board.getChildren().remove(circle);
                        }
                    });

                    boardData[i][j] = 2;
                    boardData2[i][j] = circle.getId();
                    pieces[1][counter] = circle;
                    board.add(pieces[1][counter], i, j);
                    counter++;
                }

            }
        }
    }
    public void removePiece(String pieceId, GridPane board)
    {
        Scene scene = board.getScene();
        Circle circle = (Circle) scene.lookup("#" + pieceId);
        System.out.println(pieceId + " deleted");
        board.getChildren().remove(circle);
    }
    public void movePiece(GridPane board, Circle pieces[][], int x, int y)
    {
        String tempId = selectedPiece;
//        removePiece(selectedPiece, board);
        Circle circle = new Circle(20);
        circle.setFill(circle.getFill());
        circle.setId(selectedPiece);
        tempId = tempId.replaceAll("\\D+","");
        Paint a= circle.getFill();
        int id = Integer.parseInt(tempId);
        if(selectedPiece.contains("RED"))
        {
            boardData[x][y] = 1;
            boardData[selectedPieceX][selectedPieceY] = 0;
            boardData2[x][y] = selectedPiece;
            boardData2[selectedPieceX][selectedPieceY] = "EMPTY";
            board.add(pieces[0][id], x, y);
        }
        else if (selectedPiece.contains("WHITE"))
        {
            boardData[x][y] = 2;
            boardData[selectedPieceX][selectedPieceY] = 0;
            boardData2[x][y] = selectedPiece;
            boardData2[selectedPieceX][selectedPieceY] = "EMPTY";
            board.add(pieces[1][id], x, y);
        }

    }

    public boolean checkTile(int x, int y, GridPane board, Circle pieces[][])
    {
        if (selectedPiece.contains("RED"))
        {
            if((selectedPieceX - x == 1 || selectedPieceX - x == -1) && selectedPieceY - y == -1)
                return true;
            if(selectedPieceX - x == 2 && selectedPieceY - y == -2)
            {
                if (boardData[x + 1][y-1] == 2)
                {
                    System.out.println(boardData2[x+1][y-1]);
                    removePiece(boardData2[x+1][y-1], board);
                    return true;
                }
            }
            if(selectedPieceX - x == -2 && selectedPieceY - y == -2)
            {
                if (boardData[x - 1][y-1] == 2)
                {
                    removePiece(boardData2[x - 1][y - 1], board);
                    return true;
                }
            }
            if(selectedPieceX - x == 2 && selectedPieceY - y == 2)
            {
                if (boardData[x + 1][y+1] == 2)
                {
                    removePiece(boardData2[x + 1][y + 1], board);
                    return true;
                }
            }
            if(selectedPieceX - x == -2 && selectedPieceY - y == 2)
            {
                if (boardData[x - 1][y+1] == 2)
                {
                    removePiece(boardData2[x - 1][y + 1], board);
                    return true;
                }
            }

        }
        if (selectedPiece.contains("WHITE")) {

            if ((selectedPieceX - x == 1 || selectedPieceX - x == -1) && selectedPieceY - y == 1)
                return true;

            if (selectedPieceX - x == 2 && selectedPieceY - y == 2 && boardData[selectedPieceX-1][selectedPieceY-1]==1)
            {
                return true;
            }
            if (selectedPieceX - x == -2 && selectedPieceY - y == 2 && boardData[selectedPieceX+1][selectedPieceY-1]==1)
            {
                return true;
            }
            if (selectedPieceX - x == 2 && selectedPieceY - y == -2 && boardData[selectedPieceX-1][selectedPieceY+1]==1)
            {
                return true;
            }
            if (selectedPieceX - x == -2 && selectedPieceY - y == -2 && boardData[selectedPieceX+1][selectedPieceY+1]==1)
            {
                return true;
            }


        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
