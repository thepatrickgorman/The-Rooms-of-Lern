import javafx.scene.image.Image;

import java.util.ArrayList;

public class GameImage {

    private Image image;
    private ArrayList<Point> points = new ArrayList<>();

    public GameImage(Image im) {
        this.image = im;

    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }
}
