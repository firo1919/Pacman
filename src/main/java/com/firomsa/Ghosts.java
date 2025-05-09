package com.firomsa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class Ghosts extends JLabel implements Runnable {
    public int[] startPos = new int[2];
    public int[] currentPos = new int[2];
    public ImageIcon imageIcon;
    Pacman pacman;
    Game game;
    private int[] pacpos;
    private ArrayList<int[]> movedPlaces;

    Ghosts(Pacman pacman, Game game) {
        this.game = game;
        this.pacman = pacman;
        pacpos = pacman.getCurrentPos().clone();
        movedPlaces = new ArrayList<int[]>();
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            chase(this.startPos, pacpos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void chase(int[] start, int[] end) throws InterruptedException {
        int[] U = { start[0] - 1, start[1] };
        int[] D = { start[0] + 1, start[1] };
        int[] L = { start[0], start[1] - 1 };
        int[] R = { start[0], start[1] + 1 };

        int[] check = { 0, 0 };
        double updis = 0;
        double downdis = 0;
        double leftdis = 0;
        double rightdis = 0;

        if ((Map.Map[U[0]][U[1]]) == 2) {
            check[0] = start[0] - 1;
            check[1] = start[1];
            updis = distance(check, end);
        }
        if ((Map.Map[D[0]][D[1]]) == 2) {
            check[0] = start[0] + 1;
            check[1] = start[1];
            downdis = distance(check, end);
        }
        if ((Map.Map[L[0]][L[1]]) == 2) {
            check[0] = start[0];
            check[1] = start[1] - 1;
            leftdis = distance(check, end);
        }
        if ((Map.Map[R[0]][R[1]]) == 2) {
            check[0] = start[0];
            check[1] = start[1] + 1;
            rightdis = distance(check, end);
        }
        if (Arrays.equals(pacpos, pacman.getCurrentPos())) {
            if (contain(U, movedPlaces)) {
                updis = 0.0;
            }
            if (contain(D, movedPlaces)) {
                downdis = 0.0;
            }
            if (contain(L, movedPlaces)) {
                leftdis = 0.0;
            }
            if (contain(R, movedPlaces)) {
                rightdis = 0.0;
            }
        } else {
            movedPlaces.clear();
            pacpos = pacman.getCurrentPos().clone();
        }

        ArrayList<Double> distances = new ArrayList<>(Arrays.asList(updis, downdis, leftdis, rightdis));
        Collections.sort(distances);
        distances.removeIf(n -> (n == 0));

        if (distances.get(0) == updis) {
            Map.mapUp(this.currentPos);
            setLocation(this.currentPos[1] * 20 - 9, (60 + this.currentPos[0] * 20) - 9);
        } else if (distances.get(0) == downdis) {
            Map.mapDown(this.currentPos);
            setLocation(this.currentPos[1] * 20 - 9, (60 + this.currentPos[0] * 20) - 9);
        } else if (distances.get(0) == leftdis) {
            Map.mapLeft(this.currentPos);
            setLocation(this.currentPos[1] * 20 - 9, (60 + this.currentPos[0] * 20) - 9);
        } else if (distances.get(0) == rightdis) {
            Map.mapRight(this.currentPos);
            setLocation(this.currentPos[1] * 20 - 9, (60 + this.currentPos[0] * 20) - 9);
        }

        movedPlaces.add(this.currentPos.clone());

        if (distance(this.currentPos, end) == 1) {
            Game.isAlive = false;
            pacman.killLives();
            game.over();
        }
        Thread.sleep(250);
        if (Game.isAlive) {
            chase(this.currentPos, this.pacpos);
        } else {
            return;
        }
    }

    public double distance(int[] start, int[] end) {
        double dis = Math.round(Math.sqrt(Math.abs(end[0] - start[0]) * Math.abs(end[0] - start[0])
                + Math.abs(end[1] - start[1]) * Math.abs(end[1] - start[1])));
        return dis;
    }

    public boolean contain(int[] target, ArrayList<int[]> value) {
        for (int[] i : value) {
            if (Arrays.equals(i, target))
                return true;
        }
        return false;
    }
}

class Blinky extends Ghosts {

    Blinky(Pacman pacman, Game game) {
        super(pacman, game);
        int[] pos = { 29, 1 };
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.imageIcon = super.imageIcon = new ImageIcon(getClass().getResource("/images/blinky.gif"));
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
    }

}

class Clyde extends Ghosts {

    Clyde(Pacman pacman, Game game) {
        super(pacman, game);
        int[] pos = { 29, 26 };
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.imageIcon = super.imageIcon = new ImageIcon(getClass().getResource("/images/clyde.gif"));
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
    }

}

class Inky extends Ghosts {

    Inky(Pacman pacman, Game game) {
        super(pacman, game);
        int[] pos = { 1, 26 };
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.imageIcon = super.imageIcon = new ImageIcon(getClass().getResource("/images/inky.gif"));
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
    }

}

class Pinky extends Ghosts {

    Pinky(Pacman pacman, Game game) {
        super(pacman, game);
        int[] pos = { 1, 1 };
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.imageIcon = super.imageIcon = new ImageIcon(getClass().getResource("/images/pinky.gif"));
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
    }

}