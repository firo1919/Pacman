import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class Startpage extends JPanel{
    JLabel welcome;
    JButton start;
    Startpage(){
        welcome = new JLabel("WELCOME TO PACMAN");
        welcome.setForeground(Color.ORANGE);
        //welcome.setFont(new Font(SANS_, ALLBITS, ABORT))
        welcome.setBounds(200,300,300,100);
    setLayout(null);
    setBounds(0, 0, 560, 720);
    setBackground(Color.BLACK);
    setVisible(false);
    }
}
