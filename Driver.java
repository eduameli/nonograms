import java.awt.event.*;
import java.awt.*;

public class Driver {
    public static void main(String[] args) {

        //ParsedImage.loadBMP("3colour_basketball.bmp");
        //ParsedImage.loadBMP("large.bmp");
        ParsedImage.loadBMP("2colour_elephant.bmp");

        GameWindow window = new GameWindow();
        window.revealSolution();

    }
}