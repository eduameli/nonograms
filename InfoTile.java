import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;

public abstract class InfoTile extends Tile {

    // Contains the data to be processed by calculateConstraints and runLengthEncoding, this is stored as an integer and severs as a boolean array
    // and as a RGB integer array depending on if the image has a bit depth of 1 or 24
    public int[] constraintSlice;
    
    public InfoTile(int x, int y) {
        super(x, y, Color.gray);

        constraintSlice = x == 0 ? new int[ParsedImage.getHeight()] : new int[ParsedImage.getWidth()];
        calculateConstraints();
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
    }


    public abstract void calculateConstraints();

    public abstract ArrayList<Integer> runLengthEncoding();
}