import java.awt.*;
import java.awt.event.MouseEvent;

public class ColouredTile extends PlayableTile {

    private int colorIndex = 0;

    public ColouredTile(int x, int y, Color c) {
        super(x, y);
        this.setBackground(c);
        this.addMouseListener(this);
        correctColour = ParsedImage.getColour(x-1, y-1);
    }

    // This will contain the correct colour this tile should be when the puzzle is solved read directly from the byte array
    private final int correctColour;

    // Reveals the correct colour, this returns a boolean to be used for marking.
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
    public void mouseClicked(MouseEvent mouseEvent) {
        //next(mouseEvent.getButton());
        if (mouseEvent.getButton() == 1) {
            next(1);
        } else if (mouseEvent.getButton() == 3) {
            next(-1);
        }
    }

}
