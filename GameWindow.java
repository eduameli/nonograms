import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameWindow extends JFrame{

    private static final JPanel mainPanel = new JPanel();
    public static PlayableTile[][] tileGrid = new PlayableTile[0][0];

    private static int height = 0;
    private static int width = 0;

    // The whole window is basically just a GridLayout and each item in the GridLayout is a subclass of Tile to give different behaviour depending on where it is added.
    public GameWindow() {
        height = ParsedImage.getHeight();
        width = ParsedImage.getWidth();

        tileGrid = new PlayableTile[height+1][width+1];

        GridLayout innerLayout = new GridLayout(height + 1, width + 1);
        mainPanel.setLayout(innerLayout);

        populateNonogram();

        this.setTitle("nonograms");
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
    }

    // Fill in the grid with the suitable instance
    public void populateNonogram() {

        for(int row = 0; row < height+1; row++) {
            for(int column = 0; column < width+1; column++) {
                Tile tile = getSuitableTile(column, row);
                tile.setPreferredSize(new Dimension(64, 64));
                mainPanel.add(tile);
            }
        }
    }

    // Returns a suitable Tile subclass, depending on its position and the bit depth of the image to be added to the grid.
    public Tile getSuitableTile(int x, int y) {
        Tile tile;

        if(ParsedImage.isColoured()) {
            tile = (x == 0 || y == 0) ? new ColouredInfoTile(x, y) : new ColouredTile(x, y, new Color(ParsedImage.ignoredColour));
        } else {
            tile = (x == 0 || y == 0) ? new MonochromeInfoTile(x, y) : new MonochromeTile(x, y);
        }

        if(x == 0 && y == 0) {
            tile = new GameStateTile(x, y);
        }

        if (tile instanceof PlayableTile) {
            tileGrid[y-1][x-1] = (PlayableTile) tile;
        }
        return tile;
    }

    // Marks the grid, adding a red border if the tile was wrong and a green if it was right, reveals the solution
    public static void markSolution() {
        int errors = 0;
        for(int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                boolean correct = tileGrid[x][y].reveal();
                Color borderColor;
                if(!correct) {
                    borderColor = Color.red;
                    errors++;
                } else {
                    borderColor = Color.green;
                }

                if(tileGrid[x][y].getBackground().getRGB() == borderColor.getRGB()) {
                    borderColor = borderColor.darker();
                }
                tileGrid[x][y].setBorder(new LineBorder(borderColor, 5));
            }
        }


        // Adds a polite popup about the game statistics

        String message = "Thanks for playing!\nBetter luck next time... You got " + errors + " errors out of  " + width*height ;
        if(errors == 0) {
            message = "Wow, impressive! You completed the puzzle successfully!";
        }
        JOptionPane.showMessageDialog(null, message);
    }


    // Gets a slice of the tileGrid 2d array. Takes in the InfoTile coordinate that is asking to determine if the slice should be horizontal or vertical
    // from this position
    public static PlayableTile[] getTileSlice(int x, int y) {
        PlayableTile[] slice = new PlayableTile[0];

        if(x == 0 && y == 0) {
            return slice;
        }

        if (y == 0) {
            slice = new PlayableTile[height];
            //slice[i] = getBoolean(x-1, y+i) ? 1 : 0;
            System.arraycopy(tileGrid[x - 1], y, slice, 0, height);
        } else if (x == 0) {
            slice = new PlayableTile[width];
            for(int i = 0; i < width; i++) {
                //slice[i] = getBoolean(x+i, y-1) ? 1 : 0;
                slice[i] = tileGrid[x+i][y-1];
            }
        }
        return slice;
    }


   
}