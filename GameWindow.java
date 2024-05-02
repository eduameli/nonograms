import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private JFrame window = new JFrame();
    private JPanel innerPanel = new JPanel();
    private GridLayout innerLayout = new GridLayout();

    private PlayableTile[] flatGrid;
    private PlayableTile[][] tileGrid;
    private int gridCounter;

    private int rows;
    private int columns;

    public GameWindow(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        flatGrid = new PlayableTile[rows*columns];
        tileGrid = new PlayableTile[rows][columns];


        innerLayout = new GridLayout(rows+1, columns+1);
        innerPanel.setLayout(innerLayout);
        populateNonogram();

        window.setTitle("nonograms");

        innerPanel.setLayout(innerLayout);

       

        window.setContentPane(innerPanel);
        window.pack();
        window.setVisible(true);
    }

    public void populateNonogram() {


        for(int y = 0; y < rows+1; y++) {
            for(int x = 0; x < columns+1; x++) {
               
                if (y == 0) {

                    innerPanel.add(new InfoTile());

                    //innerPanel.add(infoPan);
                } else if(x == 0) {


                    
                    innerPanel.add(new InfoTile());

                } else {
                    PlayableTile tile = new PlayableTile();
                    flatGrid[gridCounter] = tile;
                    tileGrid[y-1][x-1] = tile;
                    gridCounter++;

                    tile.setPreferredSize(new Dimension(64, 64));
                    innerPanel.add(tile);
                }
            }
        }
    }

    
    public void revealSolution(boolean[][] pixels) {
        System.out.println("size: " + flatGrid.length);
        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < columns; x++) {
                //System.out.println("bit: " + pixels[x][y]);
                if (pixels[y][x]) {
                    tileGrid[rows-1-y][x].setBackground(Color.white);
                    //System.out.println(x + " : " + y);
                } else {
                    tileGrid[rows-1-y][x].setBackground(Color.black);
                }
            }
        }
    }

    public PlayableTile getPlayableTile(int x, int y) {
        return flatGrid[x + y*columns];
    }
   
}