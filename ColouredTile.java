import java.awt.*;

public class ColouredTile extends Tile {

    public ColouredTile(int x, int y) {
        super(x, y);
        correctColour = ParsedImage.getColour(x-1, y-1);
    }

    private final Color correctColour;

    public void toggle() {}
    public void reveal() {
        this.setBackground(correctColour);
    }
}
