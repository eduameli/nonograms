import javax.swing.*;
import java.awt.*;


public abstract class Tile extends JPanel {

    public Tile(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;

        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Tile(int x, int y, Color c) {
        this.xCoord = x;
        this.yCoord = y;

        this.setBackground(c);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }


    private final int xCoord;
    private final int yCoord;


    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }


    public abstract boolean reveal();

    public abstract void toggle();

    public abstract boolean isCorrect();

}
