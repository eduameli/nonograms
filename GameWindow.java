import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{

    private final JPanel mainPanel = new JPanel();
    private final Tile[][] tileGrid;

    private final int height;
    private final int width;

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
            tile = (x == 0 || y == 0) ? new ColouredInfo(x, y) : new ColouredTile(x, y);
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

   
}