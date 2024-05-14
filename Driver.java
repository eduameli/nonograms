import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class Driver {
    public static void main(String[] args) {
        ParsedImage.loadBMP(chooseFile());


        GameWindow window = new GameWindow();
        window.revealSolution();
    }

    public static void reset() {

    }

    public static String chooseFile() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        return chooser.getSelectedFile().getAbsolutePath();
    }
}