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
import javafx.scene.image.*;
import java.util.*;
import java.io.*;

import java.util.ArrayList;

public class Driver extends Application {
    public static Label score;

    public static class xy {
        public static double x = 0.0;
        public static double y = 0.0;
    }
    public static void updateScore(int scoreN) {
    String strScore = Integer.toString(scoreN);
    if (score == null)
        score = new Label(strScore);
    else
        score.setText(strScore);
    }
    public static ArrayList<xy> points;
    public static boolean isnear(MouseEvent me, xy point) {
    if ((Math.abs(me.getX() - xy.x)) < 10 && (Math.abs(me.getY() - xy.y)) < 10) {
        updateScore(25);
        return true;
    }
    else return false;}
    
    public static void main(String args [])
    {
        points = new ArrayList<xy>();
        xy p1 = new xy();
        p1.x = 350;
        p1.y = 350;
        points.add(p1);
        

        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUpPane(primaryStage);

    }

    public void setUpPane(Stage primaryStage) {
        BorderPane topPane = new BorderPane();
        Scene scene = new Scene(topPane);
        primaryStage.setScene(scene);
        HBox titleBox = new HBox();
        titleBox.setPadding(new Insets(10,20,10,320));
        Label gameLab = new Label("Spot the difference");

        titleBox.getChildren().add(gameLab);
         updateScore(0);
         HBox scoreBox = new HBox();
         scoreBox.setPadding(new Insets(10,20,10,100));
         scoreBox.setSpacing(30);
         Label scoreLab = new Label("Score:");
         titleBox.getChildren().add(scoreBox);
         scoreBox.getChildren().add(scoreLab);
         scoreBox.getChildren().add(score);
         scoreLab.setFont(new Font("Arial", 10));
         score.setFont(new Font("Arial", 12));
        BorderPane leftPane = null;
        BorderPane rightPane = null;


            leftPane = new BorderPane(getImages().get(0));
            rightPane = new BorderPane(getImages().get(1));



        topPane.setLeft(leftPane);
        topPane.setRight(rightPane);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                System.out.println(event.getX() + " " + event.getY());
                for (int i = 0; i < points.size(); i++) {
                    boolean check = isnear(event, points.get(i));
                }
            }
        });
        topPane.setTop(titleBox);
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static ArrayList<ImageView> getImages() {
        ArrayList<ImageView> images = new ArrayList<>();

        images.add(new ImageView(new Image("./img1.jpg")));
        images.add(new ImageView(new Image("./img2.png")));


        for (ImageView v: images)
        {
            v.setFitWidth(350);
        }

        return images;
    }
}
