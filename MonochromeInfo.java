import javax.swing.*;
import java.util.ArrayList;

public class MonochromeInfo extends InfoTile {
    public MonochromeInfo(int x, int y) {
        super(x, y);
       // this.add(new JLabel("1"));
    }

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

    @Override
    public boolean satisfiesConstraints() {
        //C = ParsedImage.getBooleanSlice(getXCoord(), getYCoord());
        return true;
    }

    @Override
    public void reveal() {}

    @Override
    public void toggle() {

    }

    public ArrayList<Integer> runLengthEncoding() {
        int counter = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < constraintSlice.length; i++) {
            if (constraintSlice[i] == 1) {
                if(counter == 0) {
                    continue;
                }

                result.add(counter);

                counter = 0;
                continue;
            } else if(constraintSlice[i] == 0) {
                counter++;
            }

        }
        return result;
    }

}
