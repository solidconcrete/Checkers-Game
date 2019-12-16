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
import sample.Items.Board;
import sample.Items.Pieces.Piece;
import sample.Items.Pieces.RedPiece;
import sample.Items.Pieces.WhitePiece;
import sample.actions.GetNodes;

public class Main extends Application {
    Circle selectedPiece = null;
    int turn = -1;
    boolean locked = false;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        Board board =  Board.getInstance();
        RedPiece redPiece = new RedPiece();
        WhitePiece whitePiece = new WhitePiece();
        board.drawBoard();
        Scene scene = new Scene(board.getBoard(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        redPiece.setRedPieces();
        whitePiece.setRedPieces();
}
    public static void main(String[] args) {
        launch(args);
    }
}
