import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Tile extends JPanel {

    public Tile(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;

        this.setBackground(Color.gray);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }



    private final int xCoord;
    private final int yCoord;


    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }


    public abstract void reveal();

    public abstract void toggle();

    //public abstract void next();

//    @Override
//    public void mouseClicked(MouseEvent mouseEvent) {
//        next();
//    }
//
//    @Override
//    public void mousePressed(MouseEvent mouseEvent) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent mouseEvent) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent mouseEvent) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent mouseEvent) {
//
//    }

}
