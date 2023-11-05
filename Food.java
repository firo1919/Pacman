import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Food extends JLabel {
    public static int numberOfFood = 318;
    public static ArrayList<int[]> foodPos = new ArrayList<>(318);
    Food(int x,int y){
        int[] Pos = {x,y};
        foodPos.add(Pos);
        setBounds(y*20+5,(60+x*20)+5,10,10);
        setBackground(Color.ORANGE);
        setOpaque(true);
    }
}
