import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{

    private final JPanel mainPanel = new JPanel();
    public static Tile[][] tileGrid = new Tile[0][0];

    private static int height = 0;
    private static int width = 0;

    public GameWindow() {
        this.height = ParsedImage.getHeight();
        this.width = ParsedImage.getWidth();

        tileGrid = new Tile[height+1][width+1];

        GridLayout innerLayout = new GridLayout(height + 1, width + 1);
        mainPanel.setLayout(innerLayout);

        populateNonogram();

        this.setTitle("nonograms");
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
    }

    public void populateNonogram() {

        for(int row = 0; row < height+1; row++) {
            for(int column = 0; column < width+1; column++) {
                Tile tile = getSuitableTile(column, row);
                tile.setPreferredSize(new Dimension(64, 64));
                mainPanel.add(tile);
            }
        }
    }

    public Tile getSuitableTile(int x, int y) {
        Tile tile;

        if(ParsedImage.isColoured()) {
            tile = (x == 0 || y == 0) ? new ColouredInfo(x, y) : new ColouredTile(x, y, new Color(ParsedImage.ignoredColour));
        } else {
            tile = (x == 0 || y == 0) ? new MonochromeInfo(x, y) : new MonochromeTile(x, y);
        }

        if (!(tile instanceof InfoTile)) {
            tileGrid[y-1][x-1] = tile;
        }
        return tile;
    }

    public void revealSolution() {
        for(int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
            tileGrid[x][y].reveal();
            }
        }
    }

    public static Tile[] getTileSlice(int x, int y) {
        Tile[] slice = new Tile[0];

        if(x == 0 && y == 0) {
            return slice;
        }

        if (y == 0) {
            slice = new Tile[height];
            for(int i = 0; i < height; i++) {
                //slice[i] = getBoolean(x-1, y+i) ? 1 : 0;
                slice[i] = tileGrid[x-1][y+i];
            }
        } else if (x == 0) {
            slice = new Tile[width];
            for(int i = 0; i < width; i++) {
                //slice[i] = getBoolean(x+i, y-1) ? 1 : 0;
                slice[i] = tileGrid[x+i][y-1];
            }
        }
        return slice;
    }

   
}