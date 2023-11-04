import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pacman extends JLabel{
    public int[] startPos = {14,24};
    Pacman(){
        Icon imgIcon = new ImageIcon(this.getClass().getResource("Pacman.gif"));
        setIcon(imgIcon);
        setBounds(startPos[1]*20, startPos[0]*20+60, 40, 40);
    }
}
