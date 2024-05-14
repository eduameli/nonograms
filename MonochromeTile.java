import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MonochromeTile extends Tile implements MouseListener {

    public MonochromeTile(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        correctState = ParsedImage.getBoolean(x-1, y-1);
        this.addMouseListener(this);

    }
    private final boolean correctState;

    private int[] possibleColors = new int[] {-1, Color.black.getRGB()};
    int colorIndex = -1;
    private final int x;
    private final int y;

    public void toggle() {}

    @Override
    public boolean isCorrect() {
        return false;
    }

    public void reveal() {
        if(correctState) {
            this.setBackground(Color.yellow);
        } else {
            this.setBackground(Color.black);
        }
        //this.setBackground();
    }

    public void next() {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        colorIndex++;
        this.setBackground(new Color(possibleColors[colorIndex % 2]));
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
