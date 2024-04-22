import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayableTile extends JButton implements ActionListener, MouseListener {
    
    private Color enabledColour;
    private Color defaultColour = Color.yellow;

    
    private tileState state;    

    static enum tileState {
        DEFAULT,
        ENABLED,
        DISABLED,
    }

    public PlayableTile() {
        state = tileState.DEFAULT;
        this.setBackground(Color.yellow);
        this.addMouseListener(this);
       
    }

    public void setState(tileState state) {
        this.state = state;
    }


    public void tileToggle() {
        switch (state) {
            case DEFAULT -> {
                state = tileState.ENABLED;
                this.setBackground(enabledColour);
                System.out.println("enabling!");
            }
            
            case ENABLED -> {
                state = tileState.DEFAULT;
                this.setBackground(defaultColour);
            } 
            
            case DISABLED -> {
                state = tileState.ENABLED;
                this.setBackground(enabledColour);
            }
        }
    }

    

    @Override
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
        System.out.println(button);
        if (button == 1) {            
            enabledColour = Color.black;


        } else if (button == 3){            
            enabledColour = Color.white;

           
        }

        Color bgColour = this.getBackground();
        if (bgColour != enabledColour && bgColour != defaultColour) {
            this.setBackground(enabledColour);
        } else {
            tileToggle();
        }
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

 


}