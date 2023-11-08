import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ghosts extends JLabel implements Runnable {
    public int[] startPos = new int[2];
    public int[] currentPos = new int[2];
    public int[] previousPos = new int[2];
    public ImageIcon imageIcon;
    Map map;
    Pacman pacman;
    private int[] pacpos;
    ArrayList<int[]> movedPlaces;

    Ghosts(Pacman pacman) { 
        this.pacman = pacman;
        pacpos = pacman.currentPos.clone();
        movedPlaces = new ArrayList<int[]>();
        map = new Map();
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    public void run() {
        try {
            chase(this.startPos, pacman.currentPos);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void chase(int[] start, int[] end) throws InterruptedException {
        int[] U = {start[0]-1,start[1]}; 
        int[] D = {start[0]+1,start[1]}; 
        int[] L = {start[0],start[1]-1}; 
        int[] R = {start[0],start[1]+1}; 

        int[] check = { 0, 0 };
        double updis = 0;
        double downdis = 0;
        double leftdis = 0;
        double rightdis = 0;

        if ((map.Map[start[0] - 1][start[1]]) == 2 ) {
            check[0] = start[0] - 1;
            check[1] = start[1];
            updis = distance(check, end);
        }
        if ((map.Map[start[0] + 1][start[1]]) == 2) {
            check[0] = start[0] + 1;
            check[1] = start[1];
            downdis = distance(check, end);
        }
        if ((map.Map[start[0]][start[1] - 1]) == 2) {
            check[0] = start[0];
            check[1] = start[1] - 1;
            leftdis = distance(check, end);
        }
        if ((map.Map[start[0]][start[1] + 1]) == 2) {
            check[0] = start[0];
            check[1] = start[1] + 1;
            rightdis = distance(check, end);
        }
        System.out.println(toString());
        System.out.println(""+R[0]+", "+R[1]+"  "+contain(R));
        System.out.println(""+L[0]+", "+L[1]+"  "+contain(R));
        if(Arrays.equals(pacpos, pacman.currentPos)){
            if(contain(U)){  updis=0.0; System.out.println(""+U[0]+", "+U[1]);}
            if(contain(D)) { downdis=0.0;System.out.println(""+D[0]+", "+D[1]);}
            if(contain(L))  {leftdis=0.0;System.out.println(""+L[0]+", "+L[1]);}
            if(contain(R))  { rightdis=0.0;System.out.println(""+R[0]+", "+R[1]);}
        }
        else{
            pacpos = pacman.currentPos.clone();
        }

        System.out.println(""+updis+" "+rightdis+" "+leftdis+" "+downdis);
        ArrayList<Double> distances = getDistances(updis, rightdis, leftdis, downdis);
        Collections.sort(distances);
        if ((double) distances.get(0) == updis) {
            map.mapUp(this.currentPos, this.previousPos);
            setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
            movedPlaces.add(this.currentPos.clone());
        } else if ((double) distances.get(0) == downdis) {
            map.mapDown(this.currentPos, this.previousPos);
            setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
            movedPlaces.add(this.currentPos.clone());
        } else if ((double) distances.get(0) == leftdis) {
            map.mapLeft(this.currentPos, this.previousPos);
            setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
            movedPlaces.add(this.currentPos.clone());
        } else if ((double) distances.get(0) == rightdis) {
            map.mapRight(this.currentPos, this.previousPos);
            setLocation(this.currentPos[1] * 20 - 9,(60 + this.currentPos[0] * 20) - 9);
            movedPlaces.add(this.currentPos.clone());
        }
        if (distance(this.currentPos, end) == 1) {
            return;
        }
        Thread.sleep(200);
        chase(this.currentPos, end);

    }

    @Override
    public String toString() {
        String result = new String();
        for (int[] i : movedPlaces) {
            result+= ", ["+i[0]+", "+i[1]+"]";
        }
        return result;
    }

    public double distance(int[] start, int[] end) {
        double dis = Math.round(Math.sqrt(Math.abs(end[0] - start[0]) * Math.abs(end[0] - start[0])
                + Math.abs(end[1] - start[1]) * Math.abs(end[1] - start[1]))*10.0)/10.0;
        return dis;
    }

    public boolean contain(int[] value){
        for (int[] i : this.movedPlaces) {
            if(Arrays.equals(i,value)) return true;
        }
        return false;
    }
    public ArrayList<Double> getDistances(double x,double y,double z,double w){
        ArrayList<Double> values = new ArrayList<Double>();
        if(x!=0.0) values.add(x);
        if(y!=0.0) values.add(y);
        if(z!=0.0) values.add(z);
        if(w!=0.0) values.add(w);
        return values;
    }
}

class Blinky extends Ghosts{

    Blinky(Pacman pacman) {
        super(pacman);
        int[] pos = {29,1};
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.previousPos = pos.clone();
        super.imageIcon = new ImageIcon("blinky.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        //TODO Auto-generated constructor stub
    }
    
}

class Clyde extends Ghosts{

    Clyde(Pacman pacman) {
        super(pacman);
        int[] pos = {1,7};
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.previousPos = pos.clone();
        super.imageIcon = new ImageIcon("clyde.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        //TODO Auto-generated constructor stub
    }
    
}

class Inky extends Ghosts{

    Inky(Pacman pacman) {
        super(pacman);
         int[] pos = {1,26};
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.previousPos = pos.clone();
        super.imageIcon = new ImageIcon("inky.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        //TODO Auto-generated constructor stub
    }
    
}

class Pinky extends Ghosts{

    Pinky(Pacman pacman) {
        super(pacman);
         int[] pos = {1,1};
        super.startPos = pos.clone();
        super.currentPos = pos.clone();
        super.previousPos = pos.clone();
        super.imageIcon = new ImageIcon("pinky.gif");
        setIcon(imageIcon);
        setBounds(startPos[1] * 20 - 9, startPos[0] * 20 + 60 - 9, 38, 38);
        //TODO Auto-generated constructor stub
    }
    
}