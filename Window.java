import javax.swing.*;
import java.awt.*;

public class Window {

    private JFrame window = new JFrame();
    private JPanel outerPanel = new JPanel();
    private JPanel innerPanel = new JPanel();
    private GridLayout layout;

    private JButton[][] grid;

    public Window() {
        window.setTitle("nonograms");
        //window.setSize(640, 640);
        window.setVisible(true);
        
        layout = new GridLayout(10, 10);
        
        outerPanel.setLayout(layout);

       populateNonogram(10, 10);
       //JButton b = new JButton();
       //b.setBackground(Color.black);
        //panel.add(b);

        //JButton b2 = new JButton();
       //b2.setBackground(Color.black);
        //panel.add(b2);

        window.setContentPane(outerPanel);

        window.pack();
        window.setVisible(true);
        

    }

    public void populateNonogram(int rows, int columns) {
        for (int i = 0; i < rows*columns; i++) {
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(64, 64));
            tile.setBackground(Color.red);
            outerPanel.add(tile);
        }
    }
}