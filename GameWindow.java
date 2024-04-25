import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private JFrame window = new JFrame();
    private JPanel innerPanel = new JPanel();
    private GridLayout innerLayout = new GridLayout();

    
    private JButton[][] grid;
    private int rows;
    private int columns;

    public GameWindow(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

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


        for(int y = 0; y < columns+1; y++) {
            for(int x = 0; x < rows+1; x++) {
               
                if (y == 0) {
                    //JLabel info = new JLabel("<html>First Line<br>Second Line<br>Second Line<br>Second Line<br>Second Line</html>");
                    //GridLayout infoLayout = new GridLayout(5, 1);
                    innerPanel.add(new InfoTile());

                    //innerPanel.add(infoPan);
                } else if(x == 0) {


                    
                    innerPanel.add(new InfoTile());

                } else {
                    PlayableTile tile = new PlayableTile();
                    tile.setPreferredSize(new Dimension(64, 64));
                    innerPanel.add(tile);
                }
            }
        }
    }
   
}