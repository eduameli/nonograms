import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ColouredInfo extends InfoTile{
    public ColouredInfo(int x, int y) {
        super(x, y);
        this.addMouseListener(this);
    }

    @Override
    public void toggle() {}

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public void reveal() {
        System.out.println("row correct: " + satisfiesConstraints());
    }

    @Override
    public void calculateConstraints() {
        constraintSlice = ParsedImage.getColourSlice(getXCoord(), getYCoord());

        if (constraintSlice.length == 0) {
            return;
        }

        //System.out.println(constraintSlice);

        ArrayList<Integer> result = runLengthEncoding();
        System.out.println(result);


//        System.out.println("COLOURES: " + ParsedImage.possibleColours);
//        System.out.println("RESULT: " + result);
//        System.out.println("SLICE: " + Arrays.toString(constraintSlice));
        for(int i = 0; i < result.size(); i += 2) {
            if(result.get(i+1) == ParsedImage.ignoredColour) {
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
        Tile[] sliceTiles = GameWindow.getTileSlice(getXCoord(), getYCoord());
        for(int  i = 0; i < sliceTiles.length; i++) {
            //Tile t = GameWindow.tileGrid[getYCoord()][getXCoord()];
            //if(GameWindow.tileGrid[getXCoord(), getYCoord()].isCorrect()) {
            if (!sliceTiles[i].isCorrect()) {
                return false;
            }

            //}
        }
        return true;
    }

    
    
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








    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("row correct: " + satisfiesConstraints());
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
