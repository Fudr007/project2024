import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Integer> path = new ArrayList<>();
    private GamePanel gamePanel = new GamePanel();
    private int playerCount = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private Settings settings = new Settings();
    private Menu menu = new Menu();
    private Dice dice = new Dice();
    Scanner sc = new Scanner(System.in);
    private int who = 1;

    public Game(){
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

    public void gameStart(){
        gamePanel.doIt();
        System.out.println("game starts");
        int nwm = sc.nextInt();
        setFigures();
        dice.openDice();
        boolean end = false;
        while (!end){
            System.out.println(who);
            dice.changeName(players.get(who-1));


            if (who == playerCount){
                setWho(1);
            }else {
                setWho(getWho()+1);
            }
        }
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
        gamePanel.changeMap(0, 0, 2);
        gamePanel.changeMap(1, 0, 2);
        gamePanel.changeMap(0, 1, 2);
        gamePanel.changeMap(1, 1, 2);

        gamePanel.changeMap(9, 0, 3);
        gamePanel.changeMap(10, 1, 3);
        gamePanel.changeMap(9, 0, 3);
        gamePanel.changeMap(10, 1, 3);

        gamePanel.changeMap(9, 9, 4);
        gamePanel.changeMap(10, 10, 4);
        gamePanel.changeMap(9, 10, 4);
        gamePanel.changeMap(10, 9, 4);

        gamePanel.changeMap(0, 9, 1);
        gamePanel.changeMap(1, 10, 1);
        gamePanel.changeMap(0, 10, 1);
        gamePanel.changeMap(1, 9, 1);

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

    public Menu getMenu() {
        return menu;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }
}