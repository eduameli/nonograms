import java.awt.event.*;
import java.awt.*;

public class Driver {
    public static void main(String[] args) {

        //ParsedImage img = new ParsedImage("3colour_basketball.bmp");

        ParsedImage img = new ParsedImage("2colour_elephant.bmp");
        //ParsedImage img = new ParsedImage("elephant.bmp");

        GameWindow window = new GameWindow(img.getHeight(), img.getWidth());
        window.revealSolution(img.rawData);
        //PlayableTile t = window.getPlayableTile(14, 14);
        //t.setBackground(Color.green);
    }
}