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
    private Settings settings = new Settings();
    public Menu menu = new Menu();
    Scanner sc = new Scanner(System.in);

    public Game() throws IOException {
        openMenu();
        gamePanel.doIt();
    }

    public void openMenu() {

        System.out.println(menu.getAction());
        menu.openMenu();
        while(menu.getAction() == -1){
            System.out.print("");
        }
        switch (menu.getAction()) {
            case 0 -> {
                System.out.println("0");
                System.exit(0);
            }
            case 1 -> {
                System.out.println(1);
                player();
                openBoard();
            }
            case 2 -> {
                System.out.println(2);
                returnSettings();
            }
        }
    }

    public void openBoard() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 585);
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void returnSettings() {
        settings.openSettings(Game.this);
        while(playerCount == 0){
            System.out.print("");
        }
        System.out.println(playerCount);
    }

    public void player() {
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player());
            players.get(i).setSerialNumber(i + 1);
        }
        for (int i = 0; i < playerCount; i++) {
            players.get(i).chooseName();
            while (players.get(i).getName() == null) {
                System.out.print("");
            }
        }
    }

    public void setFigures(){
        //Player 1
        //gamePanel.changeMap();
    }


    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int count){
        this.playerCount = count;
    }
}
