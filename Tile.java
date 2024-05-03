import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Tile extends JPanel implements MouseListener {

    public Tile() {
        this.setBackground(Color.gray);
        this.addMouseListener(this);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void reveal() {};
    public void toggle() {};
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}
