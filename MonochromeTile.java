import java.awt.*;

public class MonochromeTile extends Tile {

    public MonochromeTile(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        correctState = ParsedImage.getValue(x-1, y-1);

    }
    private final boolean correctState;
    private final int x;
    private final int y;

    public void toggle() {}
    public void reveal() {
        if(correctState) {
            this.setBackground(Color.yellow);
        } else {
            this.setBackground(Color.black);
        }
        //this.setBackground();
    }

}
