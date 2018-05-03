import javafx.application.*;
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
import java.util.*;
import java.io.*;
//! The MatchGrid class.
/*! Sets up and populates the grid used for displaying the circles,
        and contains methods for interacting with it.
*/
  
public class MatchGrid {
    private int[][] grid = new int[3][3]; /**< Holds the numerical values of the circles */
    private boolean[][] shows = new boolean[3][3]; /**< If -true-, the image under the circle is shown */
    private Random rand = new Random(); /**< Random number */
    private int unmatched; /**< The number of unmatched circles */
    private int[] uRow = new int[3]; /**< The number of rows in the grid */
    private int[] uCol = new int[3]; /**< The number of columns in the grid */
    private int score; /**< The user's score, based on the number of circles matched */
    
    //! The default constructor.
    /*! Populates the grid array with random values. */
    public MatchGrid() {
        boolean[] used = {false, false, false, false, false};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = 0;
            }
        }
        score = 0;
        unmatched = 0;
        grid[rand.nextInt(3)][rand.nextInt(3)] = 1;
        int spacesLeft = 8; //!< The number of circles left to populate.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                shows[i][j] = false;
                if (spacesLeft == 0)
                    break;
                if (grid[i][j] != 0)
                    continue;
                int imgIndex; //!< Used to select which circle will be populated.
                while (used[imgIndex = rand.nextInt(5)]);
                grid[i][j] = imgIndex + 2;
                int row;
                int col;
                //while (grid[row] == rand.nextInt(3)||col == rand.nextInt(3));
                do {
                    row = rand.nextInt(3);
                    col = rand.nextInt(3);
                } while (grid[row][col] != 0);
                grid[row][col] = imgIndex + 2;
                used[imgIndex] = true;
                spacesLeft -= 2;
            }
        }
    }
    
    //! A custom getter method.
    /*!
      \param row the row to query
      \param col the column to query
      \returns If it is showing, the grid index; if not, 0.
    */
    public int get(int row, int col) {
        if (shows[row][col]) {
            return grid[row][col];
        } else {
            return 0;
        }
    }
    
    //! A method that unhides the image and increases score if it matches.
    /*!
      \param row the row to use
      \param col the column to use
      \returns true if it completes, false if the image is already shown
    */
    public boolean show(int row, int col) {
        if (!shows[row][col] && unmatched < 2) {
            shows[row][col] = true;
            uRow[unmatched] = row;
            uCol[unmatched] = col;
            unmatched++;
            if (grid[row][col] == 1)
                return false;
            System.out.println("unmatched: " + unmatched);
            for (int i = 0; i < unmatched-1; i++) {
                for (int j = i+1; j < unmatched; j++) {
                    System.out.println("i: " + i + " j: " + j);
                    System.out.println("uRow: " + uRow[i] + " " + uRow[j]);
                    System.out.println("uCol: " + uCol[i] + " " + uCol[j]);
                    //if (uRow[i] == uRow[j] && uCol[i] == uCol[j]) {
                    if (grid[uRow[i]][uCol[i]] == grid[uRow[j]][uCol[j]]) {
                        score += 25;
                        System.out.println("score: " + score);
                        unmatched = 0;
                        break;
                    }
                }
            }
        } else
            hide();
        return unmatched == 1;
    }
    
    //! A method for getting the score.
    /*!
      \returns The score of the game.
    */
    public int getScore() {
        return score;
    }
    
    //! Hides all images.
    public void hide() {
        for (int i = 0; i < unmatched; i++) {
            shows[uRow[i]][uCol[i]] = false;
        }
        unmatched = 0;
    }
    
    //! Prints the contents of grid[][].
    public void printGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
        
    //public static void main(String[] args) {
        //MatchGrid a = new MatchGrid(args);
        //a.PrintGrid();
        //Application.launch(args);
        
    //}
    
}