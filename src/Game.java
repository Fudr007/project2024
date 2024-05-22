import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private int[][] path = new int[3][55];
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
        setPath();
    }

    public void setPath() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("path.txt"));
            String line;
            for (int i = 0; i < 55; i++) {
                line = br.readLine();
                String[] split = line.split(",");
                for (int j = 0; j < 3; j++) {
                    path[j][i] = Integer.parseInt(split[j]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found, " +
                    "check if you have every file that is needed to run this program and try again");
            System.exit(0);
        }
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
        for (int i = 0; i < playerCount; i++) {
            players.get(i).setStartingPosition();
        }
        dice.openDice();

        boolean end = false;
        while (!end) {
            System.out.println(who);
            dice.changeName(players.get(who - 1));
            while (dice.getThrownNumber() == 0) {
                System.out.print("");
            }
            int which = -1;
            if (players.get(who - 1).getHowManyAtHome() < 3){
                which = players.get(who - 1).whichFigure();
                while (which == -1) {
                    System.out.print("");
                }
            }else{
                for (int i = 0; i < 4; i++){
                    if(players.get(who - 1).isMovable(i)){
                        which = i;
                    }
                }
            }

            moveFigure(players.get(who - 1), dice.getThrownNumber(), which);

            //end
            for (int i = 0; i < playerCount; i++) {
                if (players.get(i).isAtHome()) {
                    end = true;
                    JOptionPane.showMessageDialog(gamePanel.getFrame(), players.get(i).getName() + " win!!! Congratulations!");
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                    System.exit(0);
                }
            }//
            if (who == playerCount) {
                this.who = 1;
            } else {
                this.who = who + 1;
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

    public void moveFigure(Player player, int dice, int figure) {

        int where = player.getFiguresPosition(figure);
        if (where == 0) {
            int shift = 0;
            if (player.getOrderNumber() == 1){
                shift = 0;
            }else{
                shift = StaticM.playerShift(player);
            }
            String[] split = getPathLocation(shift).split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            if(gamePanel.getLocation(x,y) > 0){
                //dodelat vyhození figurky a místo ní dat jinou
                for (int i = 0; i < 4; i++) {
                    if (players.get(gamePanel.getLocation(x, y)).getFiguresPosition(i) == getLocationOnPath(x, y)){
                        players.get(gamePanel.getLocation(x, y)).kickOutFigure(i);
                        String[] strings = players.get(gamePanel.getLocation(x, y)).getStartingPosition(i).split(",");
                        int x1 = Integer.parseInt(strings[0]);
                        int y1 = Integer.parseInt(strings[1]);
                        gamePanel.changeMap(x1, y1, gamePanel.getLocation(x,y));
                    }
                }
            }
            player.setFiguresPosition(figure, ((player.getOrderNumber() - 1) * StaticM.playerShift(player)) + 1 + player.getFiguresPosition(figure));
        } else if (where+dice > player.howMuchMovable(figure)) {
            JOptionPane.showMessageDialog(gamePanel.getFrame(), "");
        } else {
            player.setFiguresPosition(figure, where + dice);
        }

    }

    public String getPathLocation(int y) {
        return path[y][0]+","+path[y][1];
    }

    public int getLocationOnPath(int x, int y) {
        int location = 0;
        for (int i = 0; i < path.length; i++){
            if (path[i][0] == x && path[i][1] == y) {
                location = i;
            }
        }
        return location;
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
}