import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Integer> path = new ArrayList<>();
    private GamePanel gamePanel = new GamePanel();
    private JFrame frame = new JFrame("Man Don't Be Angry");
    private int playerCount = 0;
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Player player3 = new Player();
    private Player player4 = new Player();
    Scanner sc = new Scanner(System.in);

    public Game() throws IOException {
        setPlayerCount();
        player();
        openBoard();
        gamePanel.doIt();
        sc.nextInt();
        System.out.println(player1.getName());
        System.out.println(player2.getName());
    }

    public void openBoard() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 585);
        frame.add(gamePanel);
        frame.setVisible(true);
    }

    public void player() {
        player1.setSerialNumber(1);
        player2.setSerialNumber(2);
        player3.setSerialNumber(3);
        player4.setSerialNumber(4);
        switch (this.playerCount) {
            case 2 -> {
                player1.chooseName();
                player2.chooseName();
            }
            case 3 -> {
                player1.chooseName();
                player2.chooseName();
                player3.chooseName();

            }
            case 4 -> {
                player1.chooseName();
                player2.chooseName();
                player3.chooseName();
                player4.chooseName();

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
