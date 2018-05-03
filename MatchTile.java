import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MatchTile extends StackPane {

    private int row;
    private int col;
    private static HashMap<Integer, Image> images = new HashMap<Integer, Image>();
    
    public MatchTile(int tileValue, int ro, int co) {
        row = ro;
        col = co;
        Circle circ = new Circle(0, 0, 90);
        circ.setFill(Color.rgb(255, 0, 0));
        //Text tileText = new Text(Integer.toString(tileValue));
        //System.out.println(tileValue);
        getChildren().add(circ);
        if (tileValue != 0) {
            getChildren().add(new ImageView(images.get(tileValue)));
            //getChildren().add(tileText);
        }
    }
    
    public static void loadImages() {
        images.put(1, new Image("Pictures/img1.png"));
        images.put(2, new Image("Pictures/img2.png"));
        images.put(3, new Image("Pictures/img3.png"));
        images.put(4, new Image("Pictures/img4.png"));
        images.put(5, new Image("Pictures/img5.png"));
        images.put(6, new Image("Pictures/img6.png"));
        images.put(7, new Image("Pictures/img7.png"));
        images.put(8, new Image("Pictures/img8.png"));
        images.put(9, new Image("Pictures/img9.png"));
        images.put(10, new Image("Pictures/img10.png"));
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}