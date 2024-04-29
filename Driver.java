import java.awt.event.*;
import java.awt.*;

public class Driver {
    public static void main(String[] args) {
        GameWindow window = new GameWindow(15, 15);

        ParsedImage img = new ParsedImage("elephant.bmp");
        window.revealSolution(img.rawData);
        //PlayableTile t = window.getPlayableTile(14, 14);
        //t.setBackground(Color.green);
    }
}