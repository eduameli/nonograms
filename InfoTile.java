import java.util.ArrayList;

public abstract class InfoTile extends Tile {

    public int[] constraintSlice;

    public InfoTile(int x, int y) {
        super(x, y);

        constraintSlice = x == 0 ? new int[ParsedImage.getHeight()] : new int[ParsedImage.getWidth()];
        calculateConstraints();

        // universal infotile styling!

    }


    public abstract void calculateConstraints();

    public abstract boolean satisfiesConstraints();

    public abstract ArrayList<Integer> runLengthEncoding();
}