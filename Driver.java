import javax.swing.*;

public class Driver {
    public static void main(String[] args) {

        String path = "";
        if (args.length != 0) {
            path = args[0];
        } else {
            path = chooseFile();
        }

        ParsedImage.loadBMP(path);

        GameWindow window = new GameWindow();
    }


    // Opens a new window to request a file be selected through the GUI
    public static String chooseFile() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        return chooser.getSelectedFile().getAbsolutePath();
    }
}