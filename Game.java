import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Game extends JFrame implements KeyListener {
    Pacman pacman;
    Thread ghost1chase;
    Thread ghost2chase;
    Thread ghost3chase;
    Thread ghost4chase;
    static ArrayList<Food> foods;

    Game() {
        foods = new ArrayList<Food>();
        pacman = new Pacman();
        Ghosts blinky = new Blinky(pacman, this);
        Ghosts clyde = new Clyde(pacman, this);
        Ghosts inky = new Inky(pacman, this);
        Ghosts pinky = new Pinky(pacman, this);
        Map map = new Map();
        map.setBackground(Color.BLACK);
        setLayout(null);
        setTitle("Pacman");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 720);
        setLocationRelativeTo(null);
        add(blinky);
        add(clyde);
        add(inky);
        add(pinky);
        add(pacman);
        add(map);

        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 27; j++) {
                if (map.Map[i][j] == 2 && !(((i == 23) && (j == 13 || j == 14)) || ((i == 13 || i == 14 || i == 15)
                        && (j == 11 || j == 12 || j == 13 || j == 14 || j == 15 || j == 16)))) {
                    int[] Pos = { i, j };
                    foods.add(new Food(Pos));
                }
            }
        }
        for (Food food : foods) {
            map.add(food);
        }
        addKeyListener(this);
        setVisible(true);
        Thread ghost1chase = new Thread(blinky);
        ghost1chase.start();
        Thread ghost2chase = new Thread(pinky);
        ghost2chase.start();
        Thread ghost3chase = new Thread(inky);
        ghost3chase.start();
        Thread ghost4chase = new Thread(clyde);
        ghost4chase.start();
        pacman.run();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38:
                pacman.setDirection("up");
                break;
            case 40:
                pacman.setDirection("down");
                break;
            case 37:
                pacman.setDirection("left");
                break;
            case 39:
                pacman.setDirection("right");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("Key Released: " + e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println("Key Typed: " + e.getKeyChar());
    }

    public static void main(String[] args) {
        new Game();
    }

    public void over() {
        this.ghost1chase = null;
        this.ghost2chase = null;
        this.ghost3chase = null;
        this.ghost4chase = null;
    }
}
