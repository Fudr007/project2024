import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Integer> path = new ArrayList<>();

    public Game() {
        fillPath();
        player();
        openBoard();
    }

    public void fillPath(){
        for(int i=0 ; i<40; i++){
            path.add(0);
        }
    }
    public void editPath(){

    }

    public void openBoard(){
        JFrame frame = new JFrame("Man Don't Be Angry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 585);
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setVisible(true);
    }

    public void player(){
        Scanner sc = new Scanner(System.in);
        Player p1 = new Player();
        p1.setSerialNumber(1);
        Player p2 = new Player();
        p2.setSerialNumber(2);
        Player p3 = new Player();
        p3.setSerialNumber(3);
        Player p4 = new Player();
        p4.setSerialNumber(4);
        int counter = 1;
        do {
            try{
                System.out.println(counter+". Player");
                switch (counter) {
                    case 1-> p1.setName(sc.next());
                    case 2-> p2.setName(sc.next());
                    case 3-> p3.setName(sc.next());
                    case 4-> p4.setName(sc.next());
                }
                counter++;
            } catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        }while(counter < 5);
    }
}
