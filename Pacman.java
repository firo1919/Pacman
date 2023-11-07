import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pacman extends JLabel implements Runnable {
    public int[] startPos = { 23, 13 };
    public int[] currentPos = { 23, 13 };
    public int[] previousPos = { 23, 13 };
    private String direction = "right";

    Map map;
    Icon imageIcon;
    public ArrayList<int[]> eatenfoods = new ArrayList<int[]>();

    Pacman() {
        map = new Map();
        changeDirection(direction);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    public void move(String direction) throws InterruptedException {
        if (!direction.equals(this.direction)) {
            changeDirection(direction);
            this.direction = direction;
        }
        switch (direction) {
            case "up":
                if (map.mapUp(this.currentPos,this.previousPos)) {
                    setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
                    eat();
                }
                break;
            case "down":
                if (map.mapDown(this.currentPos,this.previousPos)) {
                    setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
                    eat();
                }
                break;
            case "left":
                if (map.mapLeft(this.currentPos,this.previousPos)) {
                    setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
                    eat();
                }
                break;
            case "right":
                if (map.mapRight(this.currentPos,this.previousPos)) {
                    setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
                    eat();
                }
                break;

            default:
                break;
        }
        Thread.sleep(80);
        move(this.direction);
    }

    public void eat() {
        for (Food food : Game.foods) {
            if ((food.getPosition()[0] == this.currentPos[0])
                    && (food.getPosition()[1] == this.currentPos[1])
                    && (!this.eatenfoods.contains(food.getPosition()))) {
                food.setVisible(false);
                eatenfoods.add(food.getPosition());
                break;
            }
        }

    }

    public void changeDirection(String direction) {
        if (direction.equals("left")) {
            imageIcon = new ImageIcon("Pacman.gif");
            setIcon(imageIcon);

        } else if (direction.equals("right")) {
            imageIcon = new ImageIcon("PacmanRight.gif");
            setIcon(imageIcon);

        } else if (direction.equals("up")) {
            imageIcon = new ImageIcon("PacmanUp.gif");
            setIcon(imageIcon);

        } else if (direction.equals("down")) {
            imageIcon = new ImageIcon("PacmanDown.gif");
            setIcon(imageIcon);

        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public void run() {
        try {
            move(direction);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
