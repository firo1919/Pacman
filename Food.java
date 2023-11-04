import java.awt.Color;

import javax.swing.JLabel;

public class Food extends JLabel {
    public static int numberOfFood = 320;
    Food(int x,int y){
        setBounds(x+5,y+5,10,10);
        setBackground(Color.ORANGE);
        setOpaque(true);
    }
}
