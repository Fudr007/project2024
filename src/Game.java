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
    private Menu menu = new Menu();


    Scanner sc = new Scanner(System.in);

    public Game() throws IOException {
        openMenu();
    }

    public void openMenu(){
        boolean ok = false;
        while (!ok) {
            System.out.println(menu.getAction());
            menu.openMenu();
            while (menu.getAction() == -1) {
                System.out.print("");
            }
            switch (menu.getAction()) {
                case 0 -> {
                    System.out.println(0);
                    System.exit(0);
                }
                case 1 -> {
                    if (playerCount != 0) {
                        ok = true;
                        System.out.println(1);
                        player();
                        returnBoard();
                        gameStart();
                        menu.setAction(-1);
                    } else {
                        JOptionPane.showMessageDialog(menu.getFrame(), "First set player count in settings");
                        menu.setAction(-1);
                    }

                }
                case 2 -> {
                    System.out.println(2);
                    returnSettings();
                    menu.setAction(-1);
                }
            }

        }

    }

    public void returnBoard() {
        gamePanel.doIt();
    }

    public void gameStart(){
        System.out.println("game starts");
        int nwm = sc.nextInt();
        setFigures();
        frame.repaint();
    }

    public void returnSettings() {
        settings.openSettings(Game.this);
        while (playerCount == 0) {
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

    public void setFigures() {
        //Player 1
        gamePanel.changeMap(1, 1, 2);
    }


    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int count) {
        this.playerCount = count;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
