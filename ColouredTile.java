import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ColouredTile extends Tile implements MouseListener {

    private int colorIndex = -1;

    public ColouredTile(int x, int y) {
        super(x, y);
        //this.setBackground(new Color(1, 0, 0));
        this.addMouseListener(this);
        correctColour = ParsedImage.getColour(x-1, y-1);
    }

    private final int correctColour;

    public void toggle() {}
    public void reveal() {
        this.setBackground(new Color(correctColour));
    }

    public void next() {
        colorIndex++;
        this.setBackground(new Color(ParsedImage.possibleColours.get(colorIndex % ParsedImage.possibleColours.size())));

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        next();
        System.out.println("CLICKED");
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
