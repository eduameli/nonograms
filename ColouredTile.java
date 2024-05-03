import java.awt.*;

public class ColouredTile extends Tile {

    public ColouredTile(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        correctColour = ParsedImage.getColour(x-1, y-1);
    }

    private final Color correctColour;
    private final int x;
    private final int y;

    public void toggle() {}
    public void reveal() {
        this.setBackground(correctColour);
        //System.out.println("reveal");
    }
}
