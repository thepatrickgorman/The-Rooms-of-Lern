import javafx.application.*;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.*;
import java.io.*;

public class GUIMatch extends Application {

    private GridPane pane;
    private int GRIDSIZE = 3;
    private Label score;
    private MyKeyHandler keyHandler = new MyKeyHandler();
    private MatchGrid board;
    private int timers;

    public void start(Stage primaryStage) {
         setUpPane(primaryStage);
         board = new MatchGrid();
         timers = 0;
         updateBoard();
     }

     private void updateScore(int scoreN) {
         String strScore = Integer.toString(scoreN);
         if (score == null)
             score = new Label(strScore);
         else
             score.setText(strScore);
     }

     private void updateBoard() {
         updateScore(board.getScore());
         pane.getChildren().clear();
         for (int row = 0; row < GRIDSIZE; row++) {
             for (int col = 0; col < GRIDSIZE; col++) {
                 int boardValue = board.get(row, col);
                 MatchTile tile = new MatchTile(boardValue, row, col);
                 tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                     public void handle(MouseEvent e) {
                         System.out.println("row: " + tile.getRow() + " col: " + tile.getCol());
                         if (board.show(tile.getRow(), tile.getCol())) {
                            Task<Void> time = new Task<Void>() {
                              @Override
                              protected Void call() throws Exception {
                                timers++;
                                try {
                                  Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                }
                                return null;
                              }
                           };
                           time.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                               @Override
                               public void handle(WorkerStateEvent event) {
                                 timers--;
                                 if (timers<=0) {
                                   board.hide();
                                   updateBoard();
                                   timers = 0;
                                 }
                               }
                           });
                           new Thread(time).start();
                         }
                         updateBoard();
                     }
                 });
             pane.add(tile, col, row);
             }
         }
     }

     private void turnOver() {
         System.out.println("Turn Over");
         board.hide();
         updateBoard();
     }

     public void setUpPane(Stage primaryStage) {
         BorderPane topPane = new BorderPane();
         Scene scene = new Scene(topPane);
         primaryStage.setTitle("Match 2!");
         primaryStage.setScene(scene);
         HBox titleBox = new HBox();
         titleBox.setPadding(new Insets(5,20,5,100));
         Label gameLab = new Label("Match");
         titleBox.getChildren().add(gameLab);
         gameLab.setFont(new Font("Arial", 32));
         topPane.setTop(titleBox);
         BorderPane.setAlignment(titleBox, Pos.TOP_CENTER);
         updateScore(0);
         HBox scoreBox = new HBox();
         scoreBox.setPadding(new Insets(5,20,5,250));
         scoreBox.setSpacing(15);
         Label scoreLab = new Label("Score:");
         titleBox.getChildren().add(scoreBox);
         scoreBox.getChildren().add(scoreLab);
         scoreBox.getChildren().add(score);
         scoreLab.setFont(new Font("Arial", 24));
         score.setFont(new Font("Arial", 30));
         primaryStage.setScene(scene);
         pane = new GridPane();
         pane.setAlignment(Pos.CENTER);
  		   pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
  		   pane.setHgap(5.5);
  		   pane.setVgap(5.5);
         topPane.setCenter(pane);
         MatchTile.loadImages();
         primaryStage.setWidth(700);
         primaryStage.setHeight(700);
         primaryStage.show();
     }

     public class MyKeyHandler implements EventHandler<KeyEvent> {
         public void handle(KeyEvent e) {
         }
     }
    public static void main(String[] args){
        Application.launch(args);
    }
}
