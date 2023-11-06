import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pacman extends JLabel {
    public int[] startPos = { 23, 13 };
    public int[] previousPos = { 23, 13 };
    public int[] currentPos = { 23, 13 };
    private String direction = "left";
    Icon imageIcon;
    public ArrayList<int[]> eatenfoods = new ArrayList<int[]>();

    Pacman() {
        changeDirection(direction);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
    }

    public void move(String direction) {
        if (!direction.equals(this.direction)) {
            changeDirection(direction);
            this.direction = direction;
        }
        setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
       
    }

    public void eat(Food food) {
        food.setVisible(false);
        eatenfoods.add(food.getPosition());
    }

    public void changeDirection(String direction) {
        if (direction.equals("left")) {
            imageIcon = new ImageIcon("Pacman.gif");
            setIcon(imageIcon);
            System.out.println("success");
        } else if (direction.equals("right")) {
            imageIcon = new ImageIcon("PacmanRight.gif");
            setIcon(imageIcon);
            System.out.println("success");
        } else if (direction.equals("up")) {
            imageIcon = new ImageIcon("PacmanUp.gif");
            setIcon(imageIcon);
            System.out.println("success");
        } else if (direction.equals("down")) {
            imageIcon = new ImageIcon("PacmanDown.gif");
            setIcon(imageIcon);
            System.out.println("success");
        }
    }
}
