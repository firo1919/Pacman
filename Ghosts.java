import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ghosts extends JLabel implements Runnable{
    public int[] startPos = { 23, 9 };
    public int[] previousPos = { 23, 9 };
    public int[] currentPos = { 23, 9 };
    private ImageIcon imageIcon;
    Map map;
    Ghosts(Pacman pacman){
        map = new Map();
        imageIcon = new ImageIcon("Pacman.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
    }

    @Override
    public void run() {
        map.mapRight(this.currentPos, this.previousPos);
        setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
        System.out.println(currentPos[0]+", "+currentPos[1]);
         map.mapRight(this.currentPos, this.previousPos);
        setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
         System.out.println(currentPos[0]+", "+currentPos[1]);
         map.mapRight(this.currentPos, this.previousPos);
        setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
         System.out.println(currentPos[0]+", "+currentPos[1]);
         map.mapRight(this.currentPos, this.previousPos);
        setLocation(currentPos[1] * 20 - 9, (60 + currentPos[0] * 20) - 9);
          System.out.println(currentPos[0]+", "+currentPos[1]);
          try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // static class Blinky(){

    // }
}