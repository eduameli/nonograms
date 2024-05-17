import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameStateTile extends Tile implements MouseListener {

    // This tile is basically just a button that sits at coordinate in the GridLayout (x, y), and when clicked marks the solution using GameWindow.markSolution()
    public GameStateTile(int x, int y) {
        super(x, y);
        this.add(new JLabel("MARK"));
        this.setBackground(Color.red);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        GameWindow.markSolution();
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
