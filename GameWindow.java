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

        //GridLayout innerPanel = new GridLayout();

        //JPanel innerPanel = new JPanel();
        innerPanel.setLayout(innerLayout);



        // Set preferred width for leftInfo panel

       

        window.setContentPane(innerPanel);
        window.pack();
        window.setVisible(true);
    }

    public void populateNonogram() {
        //for (int i = 0; i < rows+1*columns+1; i++) {
            //if 
        //    PlayableTile tile = new PlayableTile();
        //    tile.setPreferredSize(new Dimension(64, 64));
            //tile.setBackground(Color.yellow);
        //    innerPanel.add(tile);
        //}

        for(int y = 0; y < columns+1; y++) {
            for(int x = 0; x < rows+1; x++) {
               
                //if(x == 0 && y == 0 ){
                //    continue;
                //}
                
               
                if (y == 0) {
                    //JLabel info = new JLabel("<html>First Line<br>Second Line<br>Second Line<br>Second Line<br>Second Line</html>");
                    GridLayout infoLayout = new GridLayout(5, 1);
                    JPanel infoPan =new JPanel();
                    infoPan.setLayout(infoLayout);
                    infoPan.add(new JLabel("1"));
                    infoPan.add(new JLabel("2"));
                    infoPan.add(new JLabel("3"));
                    infoPan.add(new JLabel("4"));
                    infoPan.add(new JLabel("5"));

                    innerPanel.add(infoPan);
                } else if(x == 0) {
                    GridLayout infoLayout = new GridLayout(1, 5);
                    JPanel infoPan =new JPanel();
                    infoPan.setLayout(infoLayout);
                    infoPan.add(new JLabel("1"));
                    infoPan.add(new JLabel("2"));
                    infoPan.add(new JLabel("3"));
                    infoPan.add(new JLabel("4"));
                    infoPan.add(new JLabel("5"));
                    
                    innerPanel.add(infoPan);

                } else {
                    PlayableTile tile = new PlayableTile("(" + x + ", " + y + ")");
                    tile.setPreferredSize(new Dimension(64, 64));
                    innerPanel.add(tile);
                }
            }
        }
    }
   
}