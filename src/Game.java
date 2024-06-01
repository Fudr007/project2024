import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Game class represents the game and its mechanics.
 * It is responsible for setting up the game, handling user input, updating the game state, and loading the game board.
 */
public class Game {

    /**
     * The path array represents the game board.
     *
     * It is a 2D array with a size of 3x55, where each element represents a location on the board.
     */
    private int[][] path = new int[3][55];

    private GamePanel gamePanel = new GamePanel();

    /**
     * The playerCount represents the number of players in the game.
     */
    private int playerCount = 0;

    /**
     * The players list contains all the players in the game.
     */
    public ArrayList<Player> players = new ArrayList<>();

    private Settings settings = new Settings();

    private Menu menu = new Menu();

    private Dice dice = new Dice();

    /**
     * The who variable keeps track of whose turn it is.
     */
    private int who = 1;

    /**
     * The constructor sets up the game.
     */
    public Game() {
        setPath();
        openMenu();
    }

    /**
     * The setPath method sets up the game board.
     *
     * It reads the game board configuration from a file and initializes the path array.
     */
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

    /**
     * The openMenu method opens the game menu.
     *
     * It displays the game menu and waits for user input.
     */
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
                    if (playerCount!= 0) {
                        ok = true;
                        player();
                        gamePanel.setPlayers(players);
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

    /**
     * The gameStart method starts the game.
     *
     * It initializes the game state and starts the game loop.
     */
    public void gameStart() {
        gamePanel.set();

        for (int i = 0; i < playerCount; i++) {
            players.get(i).setStartingPosition();
        }
        gamePanel.setPlayers(players);

        boolean end = false;
        while (!end) {
            boolean ok = false;
            gamePanel.setPlayers(players);
            dice.openDice();
            dice.setName(players.get(who - 1));
            while (dice.getThrownNumber() == 0) {
                System.out.print("");
            }
            if (players.get(who - 1).getHowManyAtHome() < 3) {
                while (!ok) {
                    players.get(who - 1).whichFigure();
                    while (players.get(who - 1).getWhichFigure() == 0) {
                        System.out.print("");
                    }
                    if (players.get(who - 1).howMuchMovable(players.get(who - 1).getWhichFigure() - 1) < dice.getThrownNumber()) {
                        ok = false;
                        JOptionPane.showMessageDialog(gamePanel.getFrame(), "You can't move with this one");
                    } else {
                        ok = true;
                    }
                    ok = moveFigure(players.get(who - 1), dice.getThrownNumber(), players.get(who - 1).getWhichFigure() - 1);
                    if (!ok){
                        JOptionPane.showMessageDialog(gamePanel.getFrame(), "You can'tmove with this one");
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if (players.get(who - 1).isMovable(i)) {
                        players.get(who - 1).setWhichFigure(i);
                    }
                }
                moveFigure(players.get(who - 1), dice.getThrownNumber(), players.get(who - 1).getWhichFigure() - 1);
            }

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

    /**
     * The player method initializes the players.
     *
     * It creates a new player object for each player and sets their name and color.
     */
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

    /**
     * The moveFigure method moves a figure on the game board.
     *
     * It checks if the move is valid and updates the game state accordingly.
     *
     * @param player the player who is moving the figure
     * @param dice the result of the dice roll
     * @param figure the figure being moved
     * @return true if the figure would not kick out other own figure
     */
    public boolean moveFigure(Player player, int dice, int figure) {
        boolean ok = true;
        int x = player.getFigure(figure).getX();
        int y = player.getFigure(figure).getY();
        int[] xy;
        if (dice == 6 && player.getFigure(figure).getPathPosition() == -1) {
            x = player.getFigure(figure).getStartringx();
            y = player.getFigure(figure).getStartringy();
            xy = getPathLocation((player.getOrderNumber() - 1) * 10);
            if (!figureBackToStart(xy, player)){
                return false;
            }
            player.getFigure(figure).setPathPosition((player.getOrderNumber() - 1) * 10);
            player.getFigure(figure).setXY(xy[0], xy[1]);
        } else if (player.getFigure(figure).getPathPosition() + dice > 40 + StaticM.playerShift(player) && player.getFigure(figure).getPathPosition() + dice < 45 + StaticM.playerShift(player)) {
            int plus = (player.getOrderNumber()-1) * 4;
            xy = getPathLocation(player.getFigure(figure).getPathPosition() + dice - StaticM.playerShift(player) + plus);
            if (!figureBackToStart(xy, player)){
                return false;
            }
            player.getFigure(figure).setXY(xy[0], xy[1]);
            player.getFigure(figure).setPathPosition(player.getFigure(figure).getPathPosition() + dice - StaticM.playerShift(player) + plus);
        } else if (player.getFigure(figure).getPathPosition() + dice > 40&& player.getOrderNumber()!= 1) {
            xy = getPathLocation(player.getFigure(figure).getPathPosition() + dice - 40);
            if (!figureBackToStart(xy, player)){
                return false;
            }
            player.getFigure(figure).setPathPosition(player.getFigure(figure).getPathPosition() + dice);
            player.getFigure(figure).setXY(xy[0], xy[1]);
        } else if (player.getFigure(figure).getPathPosition()!= -1) {
            xy = getPathLocation(player.getFigure(figure).getPathPosition() + dice);
            if (!figureBackToStart(xy, player)){
                return false;
            }
            player.getFigure(figure).setPathPosition(player.getFigure(figure).getPathPosition() + dice);
            player.getFigure(figure).setXY(xy[0], xy[1]);
        }
        gamePanel.changeMap(x, y, gamePanel.exampleLocation(x, y));
        gamePanel.setPlayers(players);
        return ok;
    }

    /**
     * The figureBackToStart method checks if a figure can be moved back to the start.
     *
     * It checks if there is another figure on the same location and if so, kicks it out.
     *
     * @param xy the location to check
     * @param player the player who is moving the figure
     * @return true if the figure can be moved back to the start, false otherwise
     */
    public boolean figureBackToStart(int[] xy, Player player) {
        boolean ok = true;
        if (gamePanel.whatOnLocation(xy[0], xy[1]) > 0 && gamePanel.whatOnLocation(xy[0], xy[1])!= player.getOrderNumber()) {
            for (int i = 0; i < 4; i++) {
                if (players.get(gamePanel.whatOnLocation(xy[0], xy[1])-1).getFigure(i).getPathPosition() == getLocationOnPath(xy[0], xy[1])) {
                    players.get(gamePanel.whatOnLocation(xy[0], xy[1])-1).kickOutFigure(i);
                    ok = true;
                }
            }
        }else if (gamePanel.whatOnLocation(xy[0], xy[1]) == player.getOrderNumber()){
            ok = false;
        }
        return ok;
    }

    /**
     * The getPathLocation method returns the location on the path for a given position.
     *
     * @param y the position on the path
     * @return the location on the path
     */
    public int[] getPathLocation(int y) {
        int[] location = new int[2];
        location[0] = path[0][y] - 1;
        location[1] = path[1][y] - 1;
        return location;
    }

    /**
     * The getLocationOnPath method returns the position on the path for a given location.
     *
     * @param x the x-coordinate of the location
     * @param y the y-coordinate of the location
     * @return the position on the path
     */
    public int getLocationOnPath(int x, int y) {
        int location = 0;
        for (int i = 0; i < 55; i++) {
            if (path[0][i]-1 == x && path[1][i]-1 == y) {
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

    public void returnSettings() {
        settings.openSettings(Game.this);
        while (playerCount == 0) {
            System.out.print("");
        }
    }
}