import javax.swing.*;
import java.awt.*;

public class GameWindow {

    private JFrame window = new JFrame();
    private JPanel innerPanel = new JPanel();
    private BorderLayout innerLayout = new BorderLayout();

    
    private JButton[][] grid;
    private int rows;
    private int columns;

    public GameWindow(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        //window.setSize(1980, 1024);
        //innerLayout.setPreferredSize(1024, 640);

        innerPanel.setLayout(innerLayout);
                //populateNonogram();

        window.setTitle("nonograms");

        innerPanel.setLayout(innerLayout);

        innerPanel.add(new GameCanvas(rows, columns), BorderLayout.CENTER);
        //innerPanel.add(new JLabel("Nonograms!"), BorderLayout.NORTH);
        //innerPanel.add(new JPanel(), BorderLayout.WEST);
        innerPanel.add(new InfoPanel(InfoPanel.direction.HORIZONTAL), BorderLayout.NORTH);

        // Set preferred width for leftInfo panel

       

        window.setContentPane(innerPanel);
        window.pack();
        window.setVisible(true);
    }


   
}