import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ghosts extends JLabel implements Runnable {
    public int[] startPos = new int[2];
    public int[] currentPos = new int[2];
    public int[] previousPos = new int[2];
    private ImageIcon imageIcon;
    Map map;
    Pacman pacman;
    Game game;

    Ghosts(Pacman pacman, Game game, int[] startPos) {
        this.startPos = startPos;
        this.currentPos = startPos;
        this.previousPos = startPos;
        this.game = game;
        this.pacman = pacman;
        map = new Map();
        imageIcon = new ImageIcon("Pacman.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    // @Override
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
           for (int i = 1; i < 4; i++) {
                    setLocation(prev[0], prev[1] - 5 * i);
                 Thread.sleep(80);
                }
        } else if (distances[index] == downdis) {
            map.mapDown(this.currentPos, this.previousPos);
            for (int i = 1; i < 4; i++) {
                    setLocation(prev[0], prev[1] + 5 * i);
                 Thread.sleep(80);
                }

        } else if (distances[index] == leftdis) {
            map.mapLeft(this.currentPos, this.previousPos);
           for (int i = 1; i < 4; i++) {
                    setLocation(prev[0] - 5 * i, prev[1]);
                 Thread.sleep(80);
                }

        } else if (distances[index] == rightdis) {
            map.mapRight(this.currentPos, this.previousPos);
            System.out.println(previousPos[1]);
                for (int i = 1; i <= 5; i++) {
                    setLocation(prev[0] + 4 * i, prev[1]);
                    Thread.sleep(80);
                }

        }
        if (distance(this.currentPos, end) == 1) {
            return;
        }

        chase(this.currentPos, end);

    }

    public double distance(int[] start, int[] end) {
        double dis = Math.sqrt(Math.abs(end[0] - start[0]) * Math.abs(end[0] - start[0])
                + Math.abs(end[1] - start[1]) * Math.abs(end[1] - start[1]));
        return dis;
    }

}