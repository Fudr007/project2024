import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GameMechanics {
    private int[][] path = new int[55][3];
    private ArrayList<Player> players = new ArrayList<>();
    private GamePanel gamePanel = new GamePanel();
    private int playerCount = 0;
    private Settings settings = new Settings();
    private Menu menu = new Menu();
    private Dice dice = new Dice();

    public GameMechanics(){
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
                    path[i][j] = Integer.parseInt(split[j]);
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

    public void returnSettings() {
        settings.openSettings(GameMechanics.this);
        while (playerCount == 0) {
            System.out.print("");
        }
        System.out.println(playerCount);
    }

    public void setFigures() {
        for (int i = 0; i < playerCount; i++) {
            for (int j = 0; j < 4; j++) {
                int[] xy = players.get(i).getFigureXY(j);
                System.out.println(xy[0]+","+xy[1]);
                gamePanel.changeMap(xy[0], xy[1], i);
            }
        }
    }

    public void moveFigure(Player player, int dice, int figure) {
        int x = 0;
        int y = 0;
        int where = player.getFiguresPosition(figure);
        int shift = StaticM.playerShift(player);
        if (shift == 1) {
            shift = 0;
        }

        if (where == 0) {
            int[] xy = getPathLocation(shift);
            y = xy[0];
            x = xy[1];
            gamePanel.changeMap(x, y, player.getOrderNumber());
            player.setFiguresPosition(figure, ((player.getOrderNumber() - 1) * StaticM.playerShift(player)) + 1);
        } else if (where + dice > 40 + shift && where + dice < 45 + shift) {
            int plus = player.getOrderNumber()*4;
            if (player.getOrderNumber() == 1){
                plus = 0;
            }
            int[] xy = getPathLocation(where+dice-shift+plus);
            gamePanel.changeMap(xy[0], xy[0], player.getOrderNumber());
        } else {
            player.setFiguresPosition(figure, where + dice);
        }
        if (gamePanel.whatOnLocation(x, y) > 0) {
            for (int i = 0; i < 4; i++) {
                if (players.get(gamePanel.whatOnLocation(x, y)-1).getFiguresPosition(i) == getLocationOnPath(x, y)) {
                    players.get(gamePanel.whatOnLocation(x, y)).kickOutFigure(i);
                    String[] cd = players.get(gamePanel.whatOnLocation(x, y)).getStartingPosition(i).split(",");
                    int x1 = Integer.parseInt(cd[0]);
                    int y1 = Integer.parseInt(cd[1]);
                    gamePanel.changeMap(x1, y1, player.getOrderNumber());
                }
            }
        }
        int[] xy = getPathLocation(where);
        gamePanel.changeMap(xy[0], xy[1], gamePanel.exampleLocation(xy[0], xy[1]));
    }

    public int[] getPathLocation(int y) {
        return path[y];
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

    public void setPlayerCount(int count) {
        this.playerCount = count;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Menu getMenu() {
        return menu;
    }

    public Dice getDice() {
        return dice;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}