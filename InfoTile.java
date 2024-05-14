import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;

public abstract class InfoTile extends Tile implements MouseListener {

    public int[] constraintSlice;
    
    public InfoTile(int x, int y) {
        super(x, y, Color.gray);

        constraintSlice = x == 0 ? new int[ParsedImage.getHeight()] : new int[ParsedImage.getWidth()];
        calculateConstraints();
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));

        // universal infotile styling!

    }


    public abstract void calculateConstraints();

    public abstract boolean satisfiesConstraints();

    public abstract ArrayList<Integer> runLengthEncoding();
}