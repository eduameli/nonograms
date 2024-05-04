import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ColouredInfo extends InfoTile{
    public ColouredInfo(int x, int y) {
        super(x, y);
    }

    @Override
    public void toggle() {}

    @Override
    public void reveal() {}

    @Override
    public void calculateConstraints() {
        constraintSlice = ParsedImage.getColourSlice(getXCoord(), getYCoord());

        if (constraintSlice.length == 0) {
            return;
        }

        //System.out.println(constraintSlice);

        ArrayList<Integer> result = runLengthEncoding();



//        System.out.println("COLOURES: " + ParsedImage.possibleColours);
//        System.out.println("RESULT: " + result);
//        System.out.println("SLICE: " + Arrays.toString(constraintSlice));
        for(int i = 0; i < result.size(); i += 2) {
            if(result.get(i+1) == -1) {
                continue;
            }
            JLabel label = new JLabel(String.valueOf(result.get(i)));
            label.setForeground(new Color(result.get(i+1)));
            this.add(label);
            //this.add(new JLabel(String.valueOf(result.get(i))).setForeground(new Color(ParsedImage.possibleColours.get(result.get(i+1)))));
        }
    }

    @Override
    public boolean satisfiesConstraints() {
        return true;
    }

    @Override
    public ArrayList<Integer> runLengthEncoding() {
        ArrayList<Integer> result = new ArrayList<>();
        //result.add(3);
        //result.add(-65536);

        int counter = 0;
        int lastValue =  constraintSlice[0];
        for(int i = 1; i < constraintSlice.length; i++) {

            //if(constraintSlice[i] == ParsedImage.ignoredColour) {
            //    continue;
            //}

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
