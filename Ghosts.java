import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ghosts extends JLabel implements Runnable{
    public int[] startPos = { 23, 9 };
    public int[] currentPos = { 23, 9 };
    private ImageIcon imageIcon;
    Map map;
    Pacman pacman;
    Game game;
    Ghosts(Pacman pacman, Game game){
        this.game = game;
        this.pacman = pacman;
        map = new Map();
        imageIcon = new ImageIcon("Pacman.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
    }

    @Override
    public void run() {
        map.mapRight(this.currentPos);
        setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        win();
        System.out.println(currentPos[0]+", "+currentPos[1]);
         map.mapRight(this.currentPos);
        setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
         try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        win();
         System.out.println(currentPos[0]+", "+currentPos[1]);
         map.mapRight(this.currentPos);
        setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
         try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        win();
         System.out.println(currentPos[0]+", "+currentPos[1]);
         map.mapRight(this.currentPos);
        setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
         try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        win();
          System.out.println(currentPos[0]+", "+currentPos[1]);
    }
        public void win(){
        if(Arrays.equals(pacman.currentPos,this.currentPos)){
           int selectedOption =  JOptionPane.showOptionDialog(null, "Sorry u lost!!!, Do u want to Continue", null,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
           if(selectedOption==1){
            game.dispose();
        }
        else{
          pacman.currentPos = pacman.startPos;
          pacman.move("left");
          this.currentPos =  startPos.clone();
          //pacman.move("left");
          run();
        }
    }
    }
}