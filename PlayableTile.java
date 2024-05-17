import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class PlayableTile extends Tile implements MouseListener {

    public PlayableTile(int x, int y) {
        super(x, y);
    }

    public PlayableTile(int x, int y, Color c) {
        super(x, y, c);
    }

    public abstract boolean reveal();


    // These methods are never used but they are part of the interface so I decided to implement them only once inside this super class.
    // The only method I use for detecting user input is mouseClicked inside MonochromeTile & ColouredTile.

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