import javax.swing.*;
import java.awt.event.MouseEvent;
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

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public ArrayList<Integer> runLengthEncoding() {
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        int counter = 0;
        
        for(int i = 1; i < constraintSlice.length; i++) {
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
        result.add(counter+1);
        return result;
    }























    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

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
