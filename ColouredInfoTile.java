import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ColouredInfoTile extends InfoTile{

    public ColouredInfoTile(int x, int y) {
        super(x, y);
    }

    // Calculates what text should be written in the InfoTile to make it into a puzzle. This basically means running a Run Length Encoding algorithm
    // on the slice(could be horizontal or vertical) Colours and ignoring one colour, so that the image is not fully defined making the puzzle trivial.
    @Override
    public void calculateConstraints() {
        constraintSlice = ParsedImage.getColourSlice(getXCoord(), getYCoord());

        if (constraintSlice.length == 0) {
            return;
        }

        ArrayList<Integer> result = runLengthEncoding();

        for(int i = 0; i < result.size(); i += 2) {
            if(result.get(i+1) == ParsedImage.ignoredColour) {
                continue;
            }
            JLabel label = new JLabel(String.valueOf(result.get(i)));
            label.setForeground(new Color(result.get(i+1)));
            this.add(label);
        }
    }
    
    // Returns an integer array that contains pairs of Color.getRGB() and the colours frequency
    @Override
    public ArrayList<Integer> runLengthEncoding() {
        ArrayList<Integer> result = new ArrayList<>();
  
        int counter = 0;
        int lastValue =  constraintSlice[0];

        for(int i = 1; i < constraintSlice.length; i++) {

            counter++;
            if (lastValue != constraintSlice[i]) {
                result.add(counter);
                result.add(constraintSlice[i-1]);
                counter = 0;
                lastValue = constraintSlice[i];
            }
        }

        result.add(counter+1);
        result.add(lastValue);
        return result;
    }
}
