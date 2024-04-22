import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayableTile extends JButton implements ActionListener, MouseListener {
    private tileState state;    

    static enum tileState {
        DEFAULT,
        ENABLED,
        DISABLED,
    }

    public PlayableTile() {
        state = tileState.DEFAULT;
        this.setBackground(Color.yellow);
        this.addActionListener(this);
        this.addMouseListener(this);
       
    }

    public void setState(tileState state) {
        this.state = state;
    }


    // make this into on func that takes a color variable at the top(set in the overloaded constructor)
    public void leftToggle() {
        switch (state) {
            case DEFAULT -> {
                state = tileState.ENABLED;
                this.setBackground(Color.black);
            }
            
            case ENABLED -> {
                state = tileState.DEFAULT;
                this.setBackground(Color.yellow);
            } 
            
            case DISABLED -> {
                state = tileState.ENABLED;
                this.setBackground(Color.black);
            }
        }
    }

    public void rightToggle() {
        switch (state) {
            case DEFAULT -> {
                state = tileState.ENABLED;
                this.setBackground(Color.white);
            }
            
            case ENABLED -> {
                state = tileState.DEFAULT;
                this.setBackground(Color.yellow);
            } 
            
            case DISABLED -> {
                state = tileState.ENABLED;
                this.setBackground(Color.white);
            }
        }
    }

    

    @Override
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
        if (button == 1) {
            System.out.println("left click");
            leftToggle();
        } else if (button == 2){
            System.out.println("right clicked!!");
            rightToggle();
        }
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        leftToggle();
    }


}