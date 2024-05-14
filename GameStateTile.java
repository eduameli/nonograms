import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameStateTile extends Tile implements MouseListener {
    public GameStateTile(int x, int y) {
        super(x, y);
        this.add(new JLabel("MARK"));
        this.setBackground(Color.red);
        this.addMouseListener(this);
    }

    @Override
    public boolean reveal() {
        return false;
    }

    @Override
    public void toggle() {

    }

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("MARK NONOGRAM!");
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
