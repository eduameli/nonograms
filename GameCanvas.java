import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel{

    //private JFrame window = new JFrame();
    //private JPanel innerPanel = new JPanel();
    private GridLayout innerLayout = new GridLayout();

    
    private JButton[][] grid;
    private int rows;
    private int columns;

    public GameCanvas(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        innerLayout = new GridLayout(rows+1, columns+1);
        this.setLayout(innerLayout);

        populateNonogram();

       // window.setTitle("nonograms");

        //GridLayout innerPanel = new GridLayout();

        //JPanel innerPanel = new JPanel();
        //innerPanel.setLayout(innerLayout);



        // Set preferred width for leftInfo panel

       

        //window.setContentPane(innerPanel);
        //this.pack();
        //window.pack();
        //window.setVisible(true);
    }

    public void populateNonogram() {
        //for (int i = 0; i < rows+1*columns+1; i++) {
            //if 
        //    PlayableTile tile = new PlayableTile();
        //    tile.setPreferredSize(new Dimension(64, 64));
            //tile.setBackground(Color.yellow);
        //    innerPanel.add(tile);
        //}

        for(int y = 0; y < columns; y++) {
            for(int x = 0; x < rows; x++) {

                PlayableTile tile = new PlayableTile();
                tile.setPreferredSize(new Dimension(64, 64));
                //tile.setMaximumSize(new Dimension(50, 50));
                this.add(tile);

            }
        }
    }
}
   
