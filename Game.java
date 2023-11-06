import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Game extends JFrame implements KeyListener{
    Map map;
    Pacman pacman;
    Ghosts ghost;
   ArrayList<Food> foods;
    Game() {
        foods = new ArrayList<Food>();
        pacman = new Pacman();
        ghost = new Ghosts(pacman,this);
        Thread ghostchase = new Thread(ghost);
        ghostchase.start();
       map = new Map();
       map.setBackground(Color.BLACK);
        setLayout(null);
        setTitle("Pacman");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 720);
        setLocationRelativeTo(null);
        add(ghost);
        add(map);
       map.add(pacman);
        for(int i=0;i<31;i++){
            for(int j=0;j<28;j++){
                if(map.Map[i][j]==2&& !(((i==23)&&(j==13||j==14))||((i==13||i==14||i==15)&&(j==11||j==12||j==13||j==14||j==15||j==16)))){
                    int[] Pos = {i,j};
                   foods.add(new Food(Pos));
                }
            }
        }
        for (Food food : foods) {
            if(!pacman.eatenfoods.contains(food.getPosition()))map.add(food);
        }
        addKeyListener(this);
        setVisible(true);
    }
    @Override
    public void keyPressed(KeyEvent e) {
       switch(e.getKeyCode()){
        case 38: 
        if(map.mapUp(pacman.currentPos)){
            pacman.isMoving = true;
            pacman.move("up");
            for (Food food: foods) {
                if((food.getPosition()[0]==pacman.currentPos[0])&&(food.getPosition()[1]==pacman.currentPos[1])&&(!pacman.eatenfoods.contains(food.getPosition()))){
                    pacman.eat(food);
                    break;
                }
            }
        }
        break;
        case 40:
        if(map.mapDown(pacman.currentPos)){
              pacman.isMoving = true;
            pacman.move("down");
           for (Food food: foods) {
                if((food.getPosition()[0]==pacman.currentPos[0])&&(food.getPosition()[1]==pacman.currentPos[1])&&(!pacman.eatenfoods.contains(food.getPosition()))){
                    pacman.eat(food);
                    break;
                }
            }
        }
        break;
        case 37:
        if(map.mapLeft(pacman.currentPos)){
              pacman.isMoving = true;
            pacman.move("left");
           for (Food food: foods) {
                if((food.getPosition()[0]==pacman.currentPos[0])&&(food.getPosition()[1]==pacman.currentPos[1])&&(!pacman.eatenfoods.contains(food.getPosition()))){
                    pacman.eat(food);
                    break;
                }
            }
        }
        break;
        case 39:
        if(map.mapRight(pacman.currentPos)){
        pacman.isMoving = true;
        pacman.move("right");
        for (Food food: foods) {
                if((food.getPosition()[0]==pacman.currentPos[0])&&(food.getPosition()[1]==pacman.currentPos[1])&&(!pacman.eatenfoods.contains(food.getPosition()))){
                    pacman.eat(food);
                    break;
                }
            }
        }
        break;
       }
          try {
        Thread.sleep(2000);
    } catch (InterruptedException E) {
        // TODO Auto-generated catch block
        E.printStackTrace();
    }
      map.repaint();
      pacman.isMoving = false;
       //win();
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // System.out.println("Key Released: " + e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("Key Typed: " + e.getKeyChar());
    }
    public static void main(String[] args) {
        Game game = new Game();
}}
