import java.awt.*;

public class ColouredTile extends PlayableTile {

    public ColouredTile(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        correctColour = ParsedImage.getColour(x, y);
    }

    private Color correctColour;
    private int x;
    private int y;

    public void toggle() {}
    public void reveal() {
        this.setBackground(correctColour);
        //System.out.println("reveal");
    }
}
