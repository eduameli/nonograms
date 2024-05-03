public abstract class InfoTile extends Tile {
    public InfoTile(int x, int y) {
        super(x, y);
        calculateConstraints();
        // global infotile styling!

    }

    public abstract void calculateConstraints();

    public abstract boolean satisfiesConstraints();
}