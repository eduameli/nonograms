import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.event.MouseEvent;

public class MonochromeInfo extends InfoTile {
    public MonochromeInfo(int x, int y) {
        super(x, y);
        this.add(new JLabel("1"));
    }

    @Override
    public void calculateConstraints() {
        boolean[] slice = ParsedImage.getBooleanSlice(getXCoord(), getYCoord());
    }

    @Override
    public boolean satisfiesConstraints() {
        boolean[] slice = ParsedImage.getBooleanSlice(getXCoord(), getYCoord());
        return true;
    }

    @Override
    public void reveal() {}

    @Override
    public void toggle() {

    }
}
