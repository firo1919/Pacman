package com.firomsa;
import java.awt.Color;
import javax.swing.JLabel;

public class Food extends JLabel {
    private int[] position = new int[2];
    Food(int[] Pos){
        position = Pos.clone();
        setBounds(Pos[1]*20+5,(60+Pos[0]*20)+5,10,10);
        setBackground(Color.ORANGE);
        setOpaque(true);
    }
    public int[] getPosition() {
        return position;
    }
    public void setPosition(int[] position) {
        this.position = position;
    }
}
