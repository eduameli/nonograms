import java.util.ArrayList;

public class ColouredInfo extends InfoTile{
    public ColouredInfo(int x, int y) {
        super(x, y);
    }

    @Override
    public void toggle() {}

    @Override
    public void reveal() {}

    @Override
    public void calculateConstraints() {}

    @Override
    public boolean satisfiesConstraints() {
        return true;
    }

    @Override
    public ArrayList<Integer> runLengthEncoding() {
        return null;
    }

}
