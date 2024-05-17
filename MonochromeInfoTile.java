import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MonochromeInfoTile extends InfoTile {
    public MonochromeInfoTile(int x, int y) {
        super(x, y);
    }

    // Calculates what text should be written in the InfoTile to make it into a puzzle. This basically means running a Run Length Encoding algorithm
    // on the slice(could be horizontal or vertical) for the 0 or 1 values, ignoring the 0's so it's not a trivial puzzle where you are given what each tile should be.
    @Override
    public void calculateConstraints() {
        constraintSlice = ParsedImage.getBooleanSlice(getXCoord(), getYCoord());

        if (constraintSlice.length == 0) {
            return;
        }

        ArrayList<Integer> result = runLengthEncoding();
        for(int i = 0; i < result.size(); i++) {
            this.add(new JLabel(String.valueOf(result.get(i))));
        }
    }



    // Returns an array of the length of the different groups of enabled tiles in the slice.
    @Override
    public ArrayList<Integer> runLengthEncoding() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int counter = 0;

        for (int value : constraintSlice) {
            if (value == 1) {
                if (counter > 0) {
                    result.add(counter);
                    counter = 0;
                }
            } else if (value == 0) {
                counter++;
            }
        }
        if(counter > 0) {
            result.add(counter);
        }

        return result;
    }
}
