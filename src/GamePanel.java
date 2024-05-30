import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GamePanel extends JPanel {

    private final int CELL_SIZE = 50;
    private int BOARD_SIZE = 11;
    private int[][] map = new int[11][11];
    private int[][] mapExample = new int[11][11];
    private JPanel transparentPanel = new JPanel();
    private JFrame frame = new JFrame("Man don't be angry");


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

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

    public void changeMap(int y, int x, int who) {
        map[y][x] = who;
        this.repaint();
        transparentPanel.repaint();
    }

    public int whatOnLocation(int x, int y) {
        return map[y][x];
    }

    public int exampleLocation(int y, int x) {
        return mapExample[x][y];
    }

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
        if (countX == countY) {
            BOARD_SIZE = countX;
        }

        BufferedReader rd2 = new BufferedReader(new FileReader("map.csv"));
        for (int i = 0; i < BOARD_SIZE; i++) {
            String[] split = rd2.readLine().split(",");
            for (int j = 0; j < BOARD_SIZE; j++) {
                map[i][j] = Integer.parseInt(split[j]);
                mapExample[i][j] = Integer.parseInt(split[j]);
            }
        }

    }

    public void returnMap(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(mapExample[i][j]);
            }
            System.out.println();
        }
    }

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

        int countR = 0;
        int countG = 0;
        int countB = 0;
        int countY = 0;

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (map[y][x] == 5 || map[y][x] <= 0) {
                    drawPlayingArea(g, y, x, StaticM.color(map[y][x]));
                } else {
                    switch (map[y][x]) {
                        case 1 -> {
                            countR++;
                            drawFigure(g, y, x, map[y][x], countR);
                        }
                        case 2 -> {
                            countG++;
                            drawFigure(g, y, x, map[y][x], countG);
                        }
                        case 3 -> {
                            countB++;
                            drawFigure(g, y, x, map[y][x], countB);
                        }
                        case 4 -> {
                            countY++;
                            drawFigure(g, y, x, map[y][x], countY);
                        }
                    }
                }
            }
        }
    }

    public void drawPlayingArea(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(y * CELL_SIZE, x * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void drawFigure(Graphics g, int y, int x, int color, int countColor) {
        g.setColor(StaticM.color(mapExample[y][x]));
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