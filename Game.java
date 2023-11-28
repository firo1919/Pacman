import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Game extends JFrame implements KeyListener {
    Pacman pacman;
    public static boolean isAlive = true;//set to false if pacman gets  caught
    public static ArrayList<Food> foods;
    public static JLabel score;


    Game() {
        //list of foods
        foods = new ArrayList<Food>();
        //the pacman object
        pacman = new Pacman(this);
        score = new JLabel(pacman.getEatenfoods().size()+""); 
        score.setBounds(500,20,40,20);
        score.setForeground(Color.YELLOW);
        add(score);
        //all 4 ghosts initialized
        Blinky blinky = new Blinky(pacman, this);
        Clyde clyde = new Clyde(pacman, this);
        Inky inky = new Inky(pacman, this);
        Pinky pinky = new Pinky(pacman, this);
        //map for reference between object location in jframe and the game
        Map map = new Map();
        map.setBackground(Color.BLACK);

        JLabel lifes = new JLabel("Lifes"); 
        lifes.setBounds(0,20,40,20);
        lifes.setForeground(Color.YELLOW);
        add(lifes);
        for(int i = 0; i < pacman.getLives();i++){
            JLabel life = new JLabel(new ImageIcon("images/lifes.png"));
            life.setBounds(i*30+40,20,20,20);
            add(life);
        }
        
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
        addKeyListener(this);
        setVisible(true);

        //adds all uneaten foods to the appropriate position
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 27; j++) {
                if (Map.Map[i][j] == 2 && !(((i == 23) && (j == 13 || j == 14)) || ((i == 13 || i == 14 || i == 15)
                        && (j == 11 || j == 12 || j == 13 || j == 14 || j == 15 || j == 16)))) {
                    if(!contain(new int[]{i,j},pacman.getEatenfoods()))
                    {foods.add(new Food(new int[]{i,j}));}
                }
            }
        }
        for (Food food : foods) {
            map.add(food);
        }

        //starts the thread for each ghost , 
        Thread ghost1chase = new Thread(blinky);
        ghost1chase.start();
        Thread ghost2chase = new Thread(pinky);
        ghost2chase.start();
        Thread ghost3chase = new Thread(inky);
        ghost3chase.start();
        Thread ghost4chase = new Thread(clyde);
        ghost4chase.start();
        //then pacman starts running concurrently from the ghosts
        pacman.run();
       

    }

    //a key listner for direction input from user keyboard
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
        if(pacman.getLives()>0){
        int selectedOption = JOptionPane.showOptionDialog(null, "GAME OVER!!! WANNA TRY AGAIN", null,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
        if (selectedOption == 1) {
            this.dispose();
        } else if(selectedOption==0){
            this.dispose();
            isAlive = true;
            new Game();
        }}
        else{
             int selectedOption = JOptionPane.showOptionDialog(null, "YOU HAVE WASTED YOUR LIVES WANNA START AGAIN", null,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
                if (selectedOption == 1) {
            this.dispose();
        } else if(selectedOption==0){
            this.dispose();
            isAlive = true;
            pacman.getEatenfoods().clear();
            pacman.resetLives();
            new Game();
        }
        }
    }

    public boolean contain(int[] target,ArrayList<int[]> value) {
        for (int[] i : value) {
            if (Arrays.equals(i, target))
                return true;
        }
        return false;
    }
}
