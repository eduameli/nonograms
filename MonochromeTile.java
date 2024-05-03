import java.awt.*;

public class MonochromeTile extends Tile {

    public MonochromeTile(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        correctState = ParsedImage.getValue(x, y);

    }
    private boolean correctState;
    private int x;
    private int y;

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
