import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GamePanel extends JPanel {

    private final int CELL_SIZE = 50;
    private final int BOARD_SIZE = 11;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawBoard(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawBoard(Graphics g) throws IOException {
        // Draw cells
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

        // Draw outer border
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, BOARD_SIZE * CELL_SIZE, BOARD_SIZE * CELL_SIZE);

        // Draw home areas
        drawPlayingArea(g, 0, 9, 2, 2, Color.RED);
        drawPlayingArea(g, 0, 0, 2, 2, Color.GREEN);
        drawPlayingArea(g, 9, 0, 2, 2, Color.BLUE);
        drawPlayingArea(g, 9, 9, 2, 2, Color.YELLOW);

        //Draw ending areas
        drawPlayingArea(g, 5, 6, 1, 4, Color.RED);
        drawPlayingArea(g, 1, 5, 4, 1, Color.GREEN);
        drawPlayingArea(g, 5, 1, 1, 4, Color.BLUE);
        drawPlayingArea(g, 6, 5, 4, 1, Color.YELLOW);

        //Draw playing area
        BufferedReader rd = new BufferedReader(new FileReader("nwm.txt"));
        String line;
        int count = 0;
        while ((line = rd.readLine()) != null) {
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0])-1;
            int y = Integer.parseInt(coords[1])-1;
            int starting = Integer.parseInt(coords[2]);
            System.out.println("x = " + x + ", y = " + y + ", starting = " + starting);
            switch (starting) {
                case 0:
                    drawPlayingArea(g, x, y, 1, 1, Color.DARK_GRAY);
                case 1:
                    drawPlayingArea(g, x, y, 1, 1, AbstractM.color(count));
                    count++;
            }


        }

    }

    public void drawPlayingArea(Graphics g, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, width * CELL_SIZE, height * CELL_SIZE);
    }

    public void editPlayingArea(){

    }
}
