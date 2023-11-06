import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class Game extends JFrame implements KeyListener{
    Map map;
    Pacman pacman;
   ArrayList<Food> foods;
    Game() {
        foods = new ArrayList<Food>();
        pacman = new Pacman();
       map = new Map();
       map.setBackground(Color.BLACK);
        setLayout(null);
        setTitle("Pacman");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 720);
        setLocationRelativeTo(null);
        add(map);
       map.add(pacman);
        for(int i=0;i<31;i++){
            for(int j=0;j<28;j++){
                if(map.Map[i][j]==2&& !(i==23&&(j==13||j==14))){
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

    public void win(){
        if(pacman.eatenfoods.size()==10){
           int selectedOption =  JOptionPane.showOptionDialog(null, "Sorry u lost!!!, Do u want to Continue", null,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
           if(selectedOption==1){
            dispose();
        }
        else{
          pacman.currentPos = pacman.startPos;
          pacman.move("left");
        }
    }
    }


    @Override
    public void keyPressed(KeyEvent e) {
       switch(e.getKeyCode()){
        case 38: 
        if(map.mapUp(pacman.currentPos,pacman.previousPos)){
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
        if(map.mapDown(pacman.currentPos,pacman.previousPos)){
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
        if(map.mapLeft(pacman.currentPos,pacman.previousPos)){
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
        if(map.mapRight(pacman.currentPos,pacman.previousPos)){
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
       win();
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
        Ghosts ghost = new Ghosts(game.pacman);
        Thread ghostchase = new Thread(ghost);
        ghostchase.start();
}}
