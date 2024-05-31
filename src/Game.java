import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private int[][] path = new int[3][55];
    private GamePanel gamePanel = new GamePanel();
    private int playerCount = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private Settings settings = new Settings();
    private Menu menu = new Menu();
    private Dice dice = new Dice();
    private int who = 1;

    public Game() {
        setPath();
        openMenu();
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
            menu.openMenu();
            while (menu.getAction() == -1) {
                System.out.print("");
            }
            switch (menu.getAction()) {
                case 0 -> {
                    System.exit(0);
                }
                case 1 -> {
                    if (playerCount != 0) {
                        ok = true;
                        player();
                        gameStart();
                        menu.setAction(-1);
                    } else {
                        JOptionPane.showMessageDialog(menu.getFrame(), "First set player count in settings");
                        menu.setAction(-1);
                    }

                }
                case 2 -> {
                    returnSettings();
                    menu.setAction(-1);
                }
            }

        }

    }

    public void gameStart() {
        gamePanel.set();
        for (int i = 0; i < playerCount; i++) {
            players.get(i).setStartingPosition();
        }
        setFiguresOnmap();


        boolean end = false;
        while (!end) {
            boolean ok = false;
            dice.openDice();
            dice.setName(players.get(who - 1));
            while (dice.getThrownNumber() == 0) {
                System.out.print("");
            }
            int which = -1;
            if (players.get(who - 1).getHowManyAtHome() < 3) {
                while (!ok) {
                    which = players.get(who - 1).whichFigure();
                    while (which == 0) {
                        which = players.get(who - 1).getWhichFigure();
                    }
                    if (players.get(who - 1).howMuchMovable(which) < dice.getThrownNumber()) {
                        ok = false;
                        JOptionPane.showMessageDialog(gamePanel.getFrame(), "You can't move with this one");
                    } else {
                        ok = true;
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if (players.get(who - 1).isMovable(i)) {
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
    }

    public void player() {
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player());
            players.get(i).setOrderNumber(i + 1);
            players.get(i).setColor();
        }
        for (int i = 0; i < playerCount; i++) {
            players.get(i).chooseName();
            while (players.get(i).getName() == null) {
                System.out.print("");
            }
        }
    }

    public void setFiguresOnmap() {
        for (int i = 0; i < playerCount; i++) {
            for (int j = 0; j < 4; j++) {
                gamePanel.changeMap(players.get(i).getFigure(j).getStartringx(), players.get(i).getFigure(j).getStartringy(), StaticM.colorToInt(players.get(i).getColor()));
            }
        }
    }

    public void moveFigure(Player player, int dice, int figure) {
        int x = player.getFigure(figure).getX();
        int y = player.getFigure(figure).getY();
        if (dice == 6 && player.getFigure(figure).getPathPosition() == 0) {
            int[] xy = getPathLocation((player.getOrderNumber() - 1) * 10);
            System.out.println(xy[0] + " " + xy[1]);
            gamePanel.changeMap(xy[0], xy[1], StaticM.colorToInt(player.getColor()));
            player.getFigure(figure).setPathPosition((player.getOrderNumber() - 1) * 10);
            player.getFigure(figure).setXY(xy[0], xy[1]);
            gamePanel.changeMap(x, y, gamePanel.exampleLocation(x, y));
        }else if (player.getFigure(figure).getPathPosition() != 0){
            int[] xy = getPathLocation(player.getFigure(figure).getPathPosition()+dice);
            gamePanel.changeMap(xy[0], xy[1], StaticM.colorToInt(player.getColor()));
            player.getFigure(figure).setPathPosition(player.getFigure(figure).getPathPosition()+dice);
        }
        /*int x = 0;
        int y = 0;
        int where = player.getFigure(figure).getPathPosition();
        int shift = StaticM.playerShift(player);
        if (shift == 1) {
            shift = 0;
        }

        if (where == 0) {
            if (player.getOrderNumber() == 1) {
                shift = 0;
            } else {
                shift = StaticM.playerShift(player);
            }
            String[] split = getPathLocation(shift).split(",");
            x = Integer.parseInt(split[0]);
            y = Integer.parseInt(split[1]);
            gamePanel.changeMap(x, y, player.getOrderNumber());
            player.getFigure(figure).setPathPosition(((player.getOrderNumber() - 1) * StaticM.playerShift(player)) + 1);

        } else if (where + dice > 40 + shift && where + dice < 45 + shift) {
            int plus = player.getOrderNumber() * 4;
            if (player.getOrderNumber() == 1) {
                plus = 0;
            }
            String[] xy = getPathLocation(where + dice - shift + plus).split(",");
            gamePanel.changeMap(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), player.getOrderNumber());
        } else {
            player.getFigure(figure).setPathPosition(where + dice);
        }

        if (gamePanel.whatOnLocation(x, y) > 0) {
            for (int i = 0; i < 4; i++) {
                if (players.get(gamePanel.whatOnLocation(x, y)).getFigure(i).getPathPosition() == getLocationOnPath(x, y)) {
                    players.get(gamePanel.whatOnLocation(x, y)).kickOutFigure(i);
                    gamePanel.changeMap(players.get(gamePanel.whatOnLocation(x, y)).getFigure(i).getX(), players.get(gamePanel.whatOnLocation(x, y)).getFigure(i).getY(), gamePanel.whatOnLocation(x, y));
                }
            }
        }

        String[] xy = getPathLocation(where).split(",");
        //gamePanel.changeMap(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), gamePanel.exampleLocation(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
        gamePanel.changeMap(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), player.getOrderNumber());

        */
    }

    public int[] getPathLocation(int y) {
        int[] location = new int[2];
        location[0] = path[0][y]-1;
        location[1] = path[1][y]-1;
        return location;
    }

    public int getLocationOnPath(int x, int y) {
        int location = 0;
        for (int i = 0; i < path.length; i++) {
            if (path[i][0] == x && path[i][1] == y) {
                location = i;
            }
        }
        return location;
    }

    public void setPlayerCount(int count) {
        this.playerCount = count;
    }

    public Menu getMenu() {
        return menu;
    }
}