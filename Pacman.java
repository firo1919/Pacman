import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pacman extends JLabel{
    public int[] startPos = {23,13};
    public int[] currentPos = {23,13};
    Pacman(){
        Icon imgIcon = new ImageIcon(this.getClass().getResource("Pacman.gif"));
        setIcon(imgIcon);
        setBounds(startPos[1]*20+1, startPos[0]*20+60-9, 38, 38);
    }
    public void moveLeft(){
        setLocation(currentPos[1]*20-9,(60+currentPos[0]*20)-9);
    }
    public void moveUp(){
        setLocation(currentPos[1]*20-9,(60+currentPos[0]*20-9));
    }
     public void moveRight(){
        setLocation(currentPos[1]*20-9,(60+currentPos[0]*20)-9);
    }
    public void moveDown(){
        setLocation(currentPos[1]*20-9,(60+currentPos[0]*20)-9);
    }
    private void eat(){
        if(Food.foodPos.contains(currentPos)){

        }
    }
}
