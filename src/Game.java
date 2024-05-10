import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Integer> path = new ArrayList<>();
    private GamePanel gamePanel = new GamePanel();
    private JFrame frame = new JFrame("Man Don't Be Angry");
    private int playerCount = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private Menu menu = new Menu();
    Scanner sc = new Scanner(System.in);

    public Game() throws IOException {
        //openMenu();
        setPlayerCount();
        player();
        openBoard();
        gamePanel.doIt();
    }

    public void openMenu(){
        menu.openMenu();
    }

    public void openBoard() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 585);
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void player() {
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player());
            players.get(i).setSerialNumber(i+1);
        }
        for (int i = 0; i < playerCount; i++) {
            players.get(i).chooseName();
            while (players.get(i).getName() == null){
                System.out.print("");
            }
        }
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount() {
        boolean ok = false;
        do {
            try {
                System.out.println("How many players? (Up to 4)");
                int num = sc.nextInt();
                if (num >= 2 && num <= 4) {
                    this.playerCount = num;
                } else {
                    throw new IllegalArgumentException("Player count must be between 2 and 4");
                }
                ok = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                ok = false;
            } catch (Exception e) {
                System.out.println("Only numbers from 2 to 4");
            }
        } while (!ok);
    }
}
