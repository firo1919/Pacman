import java.awt.*;
import javax.swing.*;

public class Mypanel extends JPanel {
    Mypanel() {
        setLayout(null);
        setBounds(0, 0, 560, 720);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D path = (Graphics2D) g;
        path.setStroke(new BasicStroke(1));
        path.setPaint(Color.RED);
        for (int i = 0; i < 28; i++) {
            path.drawLine(i * 20, 1, i * 20, 720);
        }
        for (int i = 0; i < 36; i++) {
            path.drawLine(1, i * 20, 560, i * 20);
        }
        g2d.setPaint(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));
        // ====================outer
        // left side
        g2d.drawLine(1, 60, 1, 260);
        g2d.drawLine(1, 440, 1, 680);
        g2d.drawLine(1, 260, 100, 260);
        g2d.drawLine(1, 440, 100, 440);
        g2d.drawLine(100, 260, 100, 320);
        g2d.drawLine(1, 320, 100, 320);
        g2d.drawLine(100, 380, 100, 440);
        g2d.drawLine(1, 380, 100, 380);
        // top and bottom
        g2d.drawLine(1, 60, 559, 60);
        g2d.drawLine(1, 680, 559, 680);
        // right side
        g2d.drawLine(559, 60, 559, 260);
        g2d.drawLine(559, 440, 559, 680);
        g2d.drawLine(559, 260, 460, 260);
        g2d.drawLine(559, 440, 460, 440);
        g2d.drawLine(460, 260, 460, 320);
        g2d.drawLine(559, 320, 460, 320);
        g2d.drawLine(460, 380, 460, 440);
        g2d.drawLine(559, 380, 460, 380);

        // ===================inner
        // left side
        g2d.drawLine(10, 70, 10, 250);
        g2d.drawLine(10, 450, 10, 550);
        g2d.drawLine(10, 570, 10, 670);
        g2d.drawLine(10, 550, 50, 550);
        g2d.drawLine(10, 570, 50, 570);
        g2d.drawLine(50, 550, 50, 570);
        g2d.drawLine(10, 250, 110, 250);
        g2d.drawLine(10, 450, 110, 450);
        g2d.drawLine(110, 250, 110, 330);
        g2d.drawLine(1, 330, 110, 330);
        g2d.drawLine(110, 370, 110, 450);
        g2d.drawLine(1, 370, 110, 370);
        // top and bottom
        g2d.drawLine(10, 70, 270, 70);
        g2d.drawLine(290, 70, 550, 70);
        g2d.drawLine(270, 70, 270, 150);
        g2d.drawLine(290, 70, 290, 150);
        g2d.drawLine(270, 150, 290, 150);
        g2d.drawLine(10, 670, 550, 670);
        // right side
        g2d.drawLine(550, 70, 550, 250);
        g2d.drawLine(550, 450, 550, 550);
        g2d.drawLine(550, 570, 550, 670);
        g2d.drawLine(550, 550, 510, 550);
        g2d.drawLine(550, 570, 510, 570);
        g2d.drawLine(510, 550, 510, 570);
        g2d.drawLine(550, 250, 450, 250);
        g2d.drawLine(550, 450, 450, 450);
        g2d.drawLine(450, 250, 450, 330);
        g2d.drawLine(559, 330, 450, 330);
        g2d.drawLine(450, 370, 450, 450);
        g2d.drawLine(559, 370, 450, 370);

        // ================
        g2d.drawRect(50, 110, 60, 40);
        g2d.drawRect(50, 190, 60, 20);
        g2d.drawRect(150, 110, 80, 40);

        g2d.drawRect(450, 110, 60, 40);
        g2d.drawRect(450, 190, 60, 20);
        g2d.drawRect(330, 110, 80, 40);

        g2d.drawRect(210, 190, 140, 20);
        g2d.drawRect(270, 210, 20, 60);

        g2d.drawRect(150, 190, 20, 140);
        g2d.drawRect(170, 250, 60, 20);

        g2d.drawRect(390, 190, 20, 140);
        g2d.drawRect(330, 250, 60, 20);

        g2d.drawRect(210, 430, 140, 20);
        g2d.drawRect(270, 450, 20, 60);

        g2d.drawRect(150, 370, 20, 80);
        g2d.drawRect(390, 370, 20, 80);

        g2d.drawRect(150, 490, 80, 20);
        g2d.drawRect(330, 490, 80, 20);

        g2d.drawRect(90, 490, 20, 80);
        g2d.drawRect(450, 490, 20, 80);

         g2d.drawRect(50, 490, 60, 20);
        g2d.drawRect(450, 490, 60, 20);

        g2d.drawRect(210, 550, 140, 20);
        g2d.drawRect(270, 570, 20, 60);

        g2d.drawRect(50, 610, 180, 20);
        g2d.drawRect(150, 550, 20, 60);

        g2d.drawRect(330, 610, 180, 20);
        g2d.drawRect(390, 550, 20, 60);

        g2d.drawRect(210, 380, 140, 10);
        g2d.drawRect(210, 310, 10, 70);
        g2d.drawRect(340, 310, 10, 70);
        g2d.drawRect(210, 310, 50, 10);
        g2d.drawRect(300, 310, 50, 10);
        

    }
}