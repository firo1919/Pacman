import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Food extends JLabel {
    public static ArrayList<int[]> foodPos = new ArrayList<>(318);
    Food(int x,int y,Color color){
        setBounds(y*20+5,(60+x*20)+5,10,10);
        setBackground(color);
        setOpaque(true);
    }
}
