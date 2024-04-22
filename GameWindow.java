import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private JFrame window = new JFrame();
    private JPanel innerPanel = new JPanel();
    private GridLayout innerLayout;

    
    private JButton[][] grid;
    private int rows;
    private int columns;

    public GameWindow(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        
        innerLayout = new GridLayout(rows, columns);
        innerPanel.setLayout(innerLayout);

        populateNonogram();

        window.setTitle("nonograms");
        window.setContentPane(innerPanel);
        window.pack();
        window.setVisible(true);
        

    }

    public void populateNonogram() {
        for (int i = 0; i < rows*columns; i++) {
            PlayableTile tile = new PlayableTile();
            tile.setPreferredSize(new Dimension(64, 64));
            //tile.setBackground(Color.yellow);
            innerPanel.add(tile);
        }
    }
   
}