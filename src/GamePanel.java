import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GamePanel extends JPanel {

    private final int CELL_SIZE = 50;
    private final int BOARD_SIZE = 11;
    private Integer[][] map = new Integer[11][11];
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("Man don't be angry");


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawBoard(g);
            drawTransparent(g);
        } catch (Exception e) {
            System.out.println("Files not found");
            System.exit(0);
        }
    }

    public void doIt() {
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
        //returnMapa();
    }

    public void changeMap(int x, int y, int who){
        map[x][y] = who;
        panel.repaint();
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
        BufferedReader rd2 = new BufferedReader(new FileReader("map.csv"));
        if (countX == countY) {
            for (int i = 0; i < countY; i++) {
                String[] split = rd2.readLine().split(",");
                for (int j = 0; j < countX; j++) {
                    map[j][i] = Integer.valueOf(split[j]);
                }
            }
        }
    }

    /*public void returnMapa(){
        for (int i = 0; i<11; i++){
            for (int j = 0; j<11; j++){
                System.out.print(mapa[i][j]+",");
            }
            System.out.println();
        }
    }*/

    public void drawBoard(Graphics g) throws IOException {

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



        BufferedReader rd = new BufferedReader(new FileReader("map.csv"));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (map[i][j] == 5 || map[i][j] <= 0){
                    drawPlayingArea(g, i, j, StaticM.color(map[i][j]));
                }
            }
        }
    }

    public void drawTransparent(Graphics g) throws IOException {
        panel.paintAll(g);
        panel.setOpaque(true);
        BufferedReader rd = new BufferedReader(new FileReader("map.csv"));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (map[i][j] > 0 || map[i][j] < 5){
                    drawFigure(g, i, j, StaticM.color(map[i][j]));
                }
            }
        }

    }

    public void drawPlayingArea(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void drawFigure(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRoundRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

}