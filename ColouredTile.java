import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ColouredTile extends Tile implements MouseListener {

    private int colorIndex = 0;

    public ColouredTile(int x, int y, Color c) {
        super(x, y);
        this.setBackground(c);
        this.addMouseListener(this);
        correctColour = ParsedImage.getColour(x-1, y-1);
    }

    private final int correctColour;

    public void toggle() {}
    public boolean reveal() {
        boolean wasCorrect = this.getBackground().getRGB() == correctColour;
        this.setBackground(new Color(correctColour));
        return wasCorrect;
    }

    public void next(int increment) {
        colorIndex += increment;
        this.setBackground(new Color(ParsedImage.possibleColours.get(Math.abs(colorIndex) % ParsedImage.possibleColours.size())));

    }

    @Override
    public boolean isCorrect() {
        return getBackground().getRGB() == correctColour;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //next(mouseEvent.getButton());
        if (mouseEvent.getButton() == 1) {
            System.out.println("left click!");
            next(1);
        } else if (mouseEvent.getButton() == 3) {
            next(-1);
            System.out.println("right click");
        }
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
