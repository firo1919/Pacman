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
    public Clyde clyde;
    public Inky inky;
    public Blinky blinky;
    public Pinky pinky;
    static boolean isAlive = true;
    static ArrayList<Food> foods;

    Game(ArrayList<int[]> eatenFood) {
        foods = new ArrayList<Food>();
        pacman = new Pacman();
        pacman.eatenfoods = eatenFood;
        blinky = new Blinky(pacman, this);
        clyde = new Clyde(pacman, this);
        inky = new Inky(pacman, this);
        pinky = new Pinky(pacman, this);
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
                    System.out.println(eatenFood.size());
                    if(!pacman.eatenfoods.contains(Pos))
                    {foods.add(new Food(Pos));}
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
        new Game(new ArrayList<>());
    }

    public void over() {
        int selectedOption = JOptionPane.showOptionDialog(null, "GAME OVER!!! WANNA TRY AGAIN", null,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
        if (selectedOption == 1) {
            this.dispose();
        } else if(selectedOption==0){
            this.dispose();
            isAlive = true;
            new Game(pacman.eatenfoods);
        }
    }
}
