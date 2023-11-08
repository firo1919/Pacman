import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ghosts extends JLabel implements Runnable {
    public int[] startPos = new int[2];
    public int[] currentPos = new int[2];
    public int[] previousPos = new int[2];
    public ImageIcon imageIcon;
    Map map;
    Pacman pacman;

    Ghosts(Pacman pacman) {
        this.pacman = pacman;
        map = new Map();
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    public void run() {
        try {
            chase(this.startPos, pacman.currentPos);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void chase(int[] start, int[] end) throws InterruptedException {
        int[] check = { 0, 0 };
        double updis = 0;
        double downdis = 0;
        double leftdis = 0;
        double rightdis = 0;
        int index = 5;
        int[] prev = { this.previousPos[1] * 20 - 9, (60 + this.previousPos[0] * 20) - 9 };
        if ((map.Map[start[0] - 1][start[1]]) == 2) {
            check[0] = start[0] - 1;
            check[1] = start[1];
            updis = distance(check, end);
        }
        if ((map.Map[start[0] + 1][start[1]]) == 2) {
            check[0] = start[0] + 1;
            check[1] = start[1];
            downdis = distance(check, end);
        }
        if ((map.Map[start[0]][start[1] - 1]) == 2) {
            check[0] = start[0];
            check[1] = start[1] - 1;
            leftdis = distance(check, end);
        }
        if ((map.Map[start[0]][start[1] + 1]) == 2) {
            check[0] = start[0];
            check[1] = start[1] + 1;
            rightdis = distance(check, end);
        }
        double[] distances = { updis, downdis, leftdis, rightdis };
        Arrays.sort(distances);
        for (int i = 0; i < 4; i++) {
            if (distances[i] != 0) {
                index = i;
                break;
            }
        }
        if (distances[index] == updis) {
            map.mapUp(this.currentPos, this.previousPos);
            setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
        } else if (distances[index] == downdis) {
            map.mapDown(this.currentPos, this.previousPos);
            setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
        } else if (distances[index] == leftdis) {
            map.mapLeft(this.currentPos, this.previousPos);
            setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
        } else if (distances[index] == rightdis) {
            map.mapRight(this.currentPos, this.previousPos);
            setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
        }
        if (distance(this.currentPos, end) == 1) {
            return;
        }
        System.out.println(distances[index]);
        Thread.sleep(200);
        chase(this.currentPos, end);

    }

    public double distance(int[] start, int[] end) {
        double dis = Math.round(Math.sqrt(Math.abs(end[0] - start[0]) * Math.abs(end[0] - start[0])
                + Math.abs(end[1] - start[1]) * Math.abs(end[1] - start[1]))*10.0)/10.0;
        return dis;
    }

}

class Blinky extends Ghosts{

    Blinky(Pacman pacman) {
        super(pacman);
        int[] pos = {29,1};
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.previousPos = pos.clone();
        super.imageIcon = new ImageIcon("blinky.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        //TODO Auto-generated constructor stub
    }
    
}

class Clyde extends Ghosts{

    Clyde(Pacman pacman) {
        super(pacman);
        int[] pos = {1,7};
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.previousPos = pos.clone();
        super.imageIcon = new ImageIcon("clyde.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        //TODO Auto-generated constructor stub
    }
    
}

class Inky extends Ghosts{

    Inky(Pacman pacman) {
        super(pacman);
         int[] pos = {1,26};
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.previousPos = pos.clone();
        super.imageIcon = new ImageIcon("inky.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        //TODO Auto-generated constructor stub
    }
    
}

class Pinky extends Ghosts{

    Pinky(Pacman pacman) {
        super(pacman);
         int[] pos = {1,1};
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.previousPos = pos.clone();
        super.imageIcon = new ImageIcon("pinky.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        //TODO Auto-generated constructor stub
    }
    
}