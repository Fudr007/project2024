import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GamePanel extends JPanel {

    private final int CELL_SIZE = 50;
    private final int BOARD_SIZE = 11;
    private Integer[][] map = new Integer[11][11];


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawBoard(g);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void doIt() throws IOException {
        fill();
        //returnMapa();
    }

    public void changeMap(int x, int y, int who){
        switch (who){
            case 1:

        }
        map[x][y] = who;
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
                    map[i][j] = Integer.valueOf(split[j]);
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
                    drawPlayingArea(g, i, j, CELL_SIZE, CELL_SIZE, StaticM.color(map[i][j]));
                } else{
                    drawFigure(g, i, j, CELL_SIZE, CELL_SIZE, StaticM.color(map[i][j]));
                }
            }
        }
    }

    public void drawPlayingArea(Graphics g, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, width * CELL_SIZE, height * CELL_SIZE);
    }

    public void drawFigure(Graphics g, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRoundRect(x, y, width * CELL_SIZE, height * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

}
