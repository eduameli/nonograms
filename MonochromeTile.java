import java.awt.*;

public class MonochromeTile extends Tile {

    public MonochromeTile(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        correctState = ParsedImage.getBoolean(x-1, y-1);

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
