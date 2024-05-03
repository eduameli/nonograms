import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private JFrame window = new JFrame();
    private JPanel innerPanel = new JPanel();
    private GridLayout innerLayout = new GridLayout();

    private PlayableTile[] flatGrid;
    private PlayableTile[][] tileGrid;
    private int gridCounter;

    private int height;
    private int width;
    public GameWindow() {
        this.height = ParsedImage.getHeight();
        this.width = ParsedImage.getWidth();

        flatGrid = new PlayableTile[height*width];
        tileGrid = new PlayableTile[height][width];


        innerLayout = new GridLayout(height+1, width+1);
        innerPanel.setLayout(innerLayout);
        populateNonogram();

        window.setTitle("nonograms");

        innerPanel.setLayout(innerLayout);

       

        window.setContentPane(innerPanel);
        window.pack();
        window.setVisible(true);
    }

    public void populateNonogram() {

        for(int row = 0; row < height+1; row++) {
            for(int column = 0; column < width+1; column++) {
               
                if (column == 0) {

                    innerPanel.add(new InfoTile());

                    //innerPanel.add(infoPan);
                } else if(row == 0) {


                    
                    innerPanel.add(new InfoTile());

                } else {
                    PlayableTile tile;

                    if(ParsedImage.isColoured()) {

                        tile = new ColouredTile( column-1, row - 1);
                    } else {
                        tile = new MonochromeTile(column - 1, row - 1);
                    }

                    flatGrid[gridCounter] = tile;
                    tileGrid[row-1][column-1] = tile;
                    gridCounter++;

                    tile.setPreferredSize(new Dimension(64, 64));
                    innerPanel.add(tile);
                }
            }
        }
    }
    


    public void revealSolution() {
        for(int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
            tileGrid[x][y].reveal();
            }
        }
    }

   
}