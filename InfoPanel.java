import javax.swing.*;
import java.awt.*;


public class InfoPanel extends JPanel{

    static enum direction {
        VERTICAL,
        HORIZONTAL,
    }

    public InfoPanel(direction dir) {
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);
        this.add(new JLabel("1"));
        this.add(new JLabel("2"));
        this.add(new JLabel("3"));
        this.add(new JLabel("4"));
        this.add(new JLabel("5"));


    }
}