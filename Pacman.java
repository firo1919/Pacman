import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Pacman extends JLabel {
    private int[] startPos = { 23, 13 };
    private int[] currentPos = { 23, 13 };
    private Game game;
    private String currentDirection = "right";
    private String previousDirection = "right";
    private Icon imageIcon;
    private static int lives = 4;
    private static ArrayList<int[]> eatenfoods = new ArrayList<int[]>();

    Pacman(Game game) {
        this.game = game;
        changeDirection(this.currentDirection);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    public void move() throws InterruptedException {
        if(!this.previousDirection.equals(this.currentDirection)){
            changeDirection(this.currentDirection);
            this.previousDirection = currentDirection+"";
        }
        switch (this.previousDirection) {
            case "up":
                if (Map.mapUp(this.currentPos)) {
                        setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
                    eat();
                }
                break;
            case "down":
                if (Map.mapDown(this.currentPos)) {
                    setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
                    eat();
                }
                break;
            case "left":
                if (Map.mapLeft(this.currentPos)) {
                   setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
                    eat();
                }
                break;
            case "right":
                if (Map.mapRight(this.currentPos)) {
                     setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
                    eat();
                }
                break;

            default:
                break;
        }
        Thread.sleep(120);
        if(Game.isAlive){
            if(this.getEatenfoods().size()!=296){ move();}
            else{
             int selectedOption = JOptionPane.showOptionDialog(null, "YOU WON CONGRATULATIONS WANNA PLAY AGAIN?", null,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
                if (selectedOption == 1) {
            game.dispose();
        } else if(selectedOption==0){
            game.dispose();
            Game.isAlive = true;
            this.getEatenfoods().clear();
            this.resetLives();
            new Game();
        }
        }
    }
    }

    public void eat() {
        for (Food food : Game.foods) {
            if ((food.getPosition()[0] == this.currentPos[0])
                    && (food.getPosition()[1] == this.currentPos[1])
                    && (!eatenfoods.contains(food.getPosition()))) {
                food.setVisible(false);
                eatenfoods.add(food.getPosition());
                Game.score.setText(eatenfoods.size()+"");
                break;
            }
        }

    }

    public void changeDirection(String direction) {
        if (direction.equals("left")) {
            imageIcon = new ImageIcon("images/PacmanLeft.gif");
            setIcon(imageIcon);

        } else if (direction.equals("right")) {
            imageIcon = new ImageIcon("images/Pacman.gif");
            setIcon(imageIcon);

        } else if (direction.equals("up")) {
            imageIcon = new ImageIcon("images/PacmanUp.gif");
            setIcon(imageIcon);

        } else if (direction.equals("down")) {
            imageIcon = new ImageIcon("images/PacmanDown.gif");
            setIcon(imageIcon);

        }
    }

    public void run() {
        try {
            move();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 public void setDirection(String direction) {
        this.currentDirection = direction;
    }
     public int[] getStartPos() {
        return startPos;
    }

    public void setStartPos(int[] startPos) {
        this.startPos = startPos;
    }

    public int[] getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int[] currentPos) {
        this.currentPos = currentPos;
    }

    public ArrayList<int[]> getEatenfoods() {
        return eatenfoods;
    }

    public void setEatenfoods(ArrayList<int[]> eatenfoods) {
        eatenfoods = eatenfoods;
    }
     public int getLives() {
        return lives;
    }

    public void killLives(){
        lives--;
    }

    public void resetLives() {
        lives = 4;
    }


}
