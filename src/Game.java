import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
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

    public Game() {
        openMenu();
    }

    public void openMenu() {
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

    public void gameStart() {
        gamePanel.doIt();
        System.out.println("game starts");
        int nwm = sc.nextInt();
        setFigures();
        dice.openDice();
        boolean end = false;
        while (!end) {
            System.out.println(who);
            dice.changeName(players.get(who-1));
            while(dice.getThrownNumber() == 0){
                System.out.print("");
            }
            int which = players.get(who-1).whichFigure;
            while(which == -1){
                System.out.print("");
            }
            moveFigure(players.get(who - 1), dice.getThrownNumber(), which);
            for (int i = 0; i < playerCount; i++) {
                if (players.get(i).isAtHome()) {
                    end = true;
                    JOptionPane.showMessageDialog(gamePanel.getFrame(), players.get(i).getName() + " win!!! Congratulations!");
                }
            }
            if (who == playerCount) {
                setWho(1);
            } else {
                setWho(getWho() + 1);
            }
            dice.clearNumber();
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
            players.get(i).setOrderNumber(i + 1);
        }
        for (int i = 0; i < playerCount; i++) {
            players.get(i).chooseName();
            while (players.get(i).getName() == null) {
                System.out.print("");
            }
        }
    }

    public void setFigures() {
        try {
            BufferedReader rd = new BufferedReader(new FileReader("homes.txt"));
            String line;
            while ((line = rd.readLine()) != null) {
                String[] lineSplit = line.split(",");
                int x = Integer.parseInt(lineSplit[0]);
                int y = Integer.parseInt(lineSplit[1]);
                int who = Integer.parseInt(lineSplit[2]);
                gamePanel.changeMap(x, y, who);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(gamePanel.getFrame(), "File not found, " +
                    "check if you have every file that is needed to run this program and try again");
            System.exit(0);
        }
    }

    public void moveFigure(Player player, int dice, int figure){
        try {
            BufferedReader rd = new BufferedReader(new FileReader("path.txt"));
            int where = player.getFiguresPosition(figure);
            if (where == 0){
                player.setFiguresPosition(figure, ((player.getOrderNumber()-1)*StaticM.playerShift(player))+1);
            } else {
                player.setFiguresPosition(figure, where+dice);
            }

            if (player.isMovable(figure) && player.getHowManyAtHome() <= 3){

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(gamePanel.getFrame(), "File not found, " +
                    "check if you have every file that is needed to run this program and try again");
            System.exit(0);
        }

    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int count) {
        this.playerCount = count;
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