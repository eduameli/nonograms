import java.awt.*;
import java.awt.event.MouseEvent;

public class MonochromeTile extends PlayableTile {

    public MonochromeTile(int x, int y) {
        super(x, y);
        correctState = ParsedImage.getBoolean(x-1, y-1);
        this.addMouseListener(this);

    }

    // The correct state of this tile according to the read image, B/W so it can be stored as a boolean
    private final boolean correctState;

    // Array containing black and white, this is used to swap between the colours when you click, indexing a click counter into this array but using the % operator so it
    // doesn't go out  of bounds
    private final int[] possibleColors = new int[] {-1, Color.black.getRGB()};
    int colorIndex = -1;

    public boolean reveal() {
        Color c = this.getBackground();
        boolean wasCorrect = (c.getRGB() == (correctState ? Color.white.getRGB() : Color.black.getRGB()));
        if(correctState) {
            this.setBackground(Color.white);
        } else {
            this.setBackground(Color.black);
        }
        return wasCorrect;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        colorIndex++;
        this.setBackground(new Color(possibleColors[colorIndex % 2]));
    }
}
