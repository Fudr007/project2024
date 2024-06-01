import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The GamePanel class represents the game board in the game.
 *
 * It displays the board, the players figures, and allows the players to move their figures.
 */
class GamePanel extends JPanel {

    /**
     * The size of each cell on the board.
     */
    private final int CELL_SIZE = 50;

    /**
     * The size of the board
     */
    private final int BOARD_SIZE = 11;

    /**
     * The map of the board, which contains the layout of the board.
     */
    private int[][] map = new int[11][11];

    /**
     * The mapExample helps to "clean up" after the figures
     */
    private int[][] mapExample = new int[11][11];
    private JFrame frame = new JFrame("Man don't be angry");

    /**
     * The player is helping list of players in the game.
     */
    private ArrayList<Player> player = new ArrayList<>(4);

    /**
     * The count is helping value of turns
     */
    private int count = 0;

    /**
     * Paints the game board and the players figures on the game board.
     *
     * @param g the Graphics object to draw on
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    /**
     * The set sets up the map and the frame
     */
    public void set() {
        try {
            fill();
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 585);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(GamePanel.this);
    }

    /**
     * It changes the map based on parameters
     *
     * @param x coordinate
     * @param y coordinate
     * @param who which player
     */
    public void changeMap(int x, int y, int who) {
        map[x][y] = who;
        repaint();
    }

    /**
     * Loads players
     *
     * @param players the array of the players
     */
    public void setPlayers(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++) {
            player.add(players.get(i));
        }
        repaint();
    }

    public Player getPlayer(int i){
        return player.get(i);
    }

    /**
     * It returns value of exampleMap from coords
     *
     * @param x coordinate
     * @param y coordinate
     * @return location form map
     */
    public int exampleLocation(int x, int y) {
        return mapExample[x][y];
    }

    /**
     * It returns value of map from coords
     *
     * @param x coordinate
     * @param y coordinate
     * @return location form map
     */
    public int whatOnLocation(int x, int y) {
        return map[x][y];
    }

    /**
     * It fills the map and exampleMap from files
     *
     * @throws IOException if it could not find any of the files
     */
    public void fill() throws IOException {
        BufferedReader rd = new BufferedReader(new FileReader("map.csv"));
        String line;
        int countX = 0;
        int countY = 0;
        while ((line = rd.readLine()) != null) {
            String[] split = line.split(",");
            countX = split.length;
            countY++;
        }
        BufferedReader rd2 = new BufferedReader(new FileReader("map.csv"));
        if (countX == countY) {
            for (int i = 0; i < countY; i++) {
                String[] split = rd2.readLine().split(",");
                for (int j = 0; j < countX; j++) {
                    map[j][i] = Integer.parseInt(split[j]);
                    mapExample[j][i] = Integer.parseInt(split[j]);
                }
            }
        }
    }

    /**
     * It draw the board to the graphics
     *
     * @param g the graphics
     */
    public void drawBoard(Graphics g) {

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int x = i * CELL_SIZE;
                int y = j * CELL_SIZE;
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, BOARD_SIZE * CELL_SIZE, BOARD_SIZE * CELL_SIZE);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (map[i][j] == 5 || map[i][j] <= 0) {
                    drawPlayingArea(g, i, j, StaticM.color(map[i][j]));
                }
            }
        }
        if (count == 0 ){
            for (int i = 0; i < player.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    drawFigure(g, player.get(i).getFigure(j).getStartringx(), player.get(i).getFigure(j).getStartringy(), StaticM.colorToInt(player.get(i).getColor()), j+1);
                }
            }
            count++;
        }else{
            for (int i = 0; i < player.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    drawFigure(g, player.get(i).getFigure(j).getX(), player.get(i).getFigure(j).getY(), StaticM.colorToInt(player.get(i).getColor()), j+1);
                    map[player.get(i).getFigure(j).getX()][player.get(i).getFigure(j).getY()] = StaticM.colorToInt(player.get(i).getColor());
                }
            }
        }

    }

    /**
     * Draws one cell of the board based on parameters
     *
     * @param g the Graphics object to draw on
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @param color the color of the cell
     */
    public void drawPlayingArea(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Draws a figure on the game board.
     *
     * @param g the Graphics object to draw on
     * @param x the x-coordinate of the figure
     * @param y the y-coordinate of the figure
     * @param color the color of the figure
     * @param countColor the count of the figure
     */
    public void drawFigure(Graphics g, int x, int y, int color, int countColor) {
        g.setColor(StaticM.color(mapExample[x][y]));
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.setColor(StaticM.color(color));
        g.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE - 5, CELL_SIZE - 5);
        g.setColor(StaticM.color(color).darker());
        g.setFont(new Font("Arial", Font.BOLD, CELL_SIZE - 5));
        g.drawString("" + countColor, (x * CELL_SIZE) + 10, (y * CELL_SIZE) + 40);
    }

    public JFrame getFrame() {
        return frame;
    }
}