import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel {
    private final int CELL_SIZE = 50;
    private final int BOARD_SIZE = 11;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
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
        drawHomeArea(g, 0, 9, 2, 2, Color.RED);
        drawHomeArea(g, 0, 0, 2, 2, Color.GREEN);
        drawHomeArea(g, 9, 0, 2, 2, Color.BLUE);
        drawHomeArea(g, 9, 9, 2, 2, Color.YELLOW);

        // Draw starting areas
        drawStartingArea(g, 4, 10, 1, 1, Color.RED);
        drawStartingArea(g, 0, 4, 1, 1, Color.GREEN);
        drawStartingArea(g, 6, 0, 1, 1, Color.BLUE);
        drawStartingArea(g, 10, 6, 1, 1, Color.YELLOW);

        //Draw ending areas
        drawEndArea(g, 5, 6, 1, 4, Color.RED);
        drawEndArea(g, 1, 5, 4, 1, Color.GREEN);
        drawEndArea(g, 5, 1, 1, 4, Color.BLUE);
        drawEndArea(g, 6, 5, 4, 1, Color.YELLOW);

        //Draw playing area
        drawPlayingArea(g);
    }

    private void drawHomeArea(Graphics g, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, width * CELL_SIZE, height * CELL_SIZE);
    }

    private void drawStartingArea(Graphics g, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, width * CELL_SIZE, height * CELL_SIZE);
    }

    private void drawEndArea(Graphics g, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, width * CELL_SIZE, height * CELL_SIZE);
    }

    private void drawPlayingArea(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        //Draw x lines
        g.fillRect(1 * CELL_SIZE, 4 * CELL_SIZE, 4 * CELL_SIZE, 1 * CELL_SIZE);
        g.fillRect(6 * CELL_SIZE, 4 * CELL_SIZE, 5 * CELL_SIZE, 1 * CELL_SIZE);
        g.fillRect(0 * CELL_SIZE, 6 * CELL_SIZE, 5 * CELL_SIZE, 1 * CELL_SIZE);
        g.fillRect(6 * CELL_SIZE, 6 * CELL_SIZE, 4 * CELL_SIZE, 1 * CELL_SIZE);

        //Draw y lines
        g.fillRect(4 * CELL_SIZE, 0 * CELL_SIZE, 1 * CELL_SIZE, 4 * CELL_SIZE);
        g.fillRect(4 * CELL_SIZE, 7 * CELL_SIZE, 1 * CELL_SIZE, 3 * CELL_SIZE);
        g.fillRect(6 * CELL_SIZE, 1 * CELL_SIZE, 1 * CELL_SIZE, 3 * CELL_SIZE);
        g.fillRect(6 * CELL_SIZE, 7 * CELL_SIZE, 1 * CELL_SIZE, 4 * CELL_SIZE);

        //Draw points
        g.fillRect(5 * CELL_SIZE, 10 * CELL_SIZE, 1 * CELL_SIZE, 1 * CELL_SIZE);
        g.fillRect(0 * CELL_SIZE, 5 * CELL_SIZE, 1 * CELL_SIZE, 1 * CELL_SIZE);
        g.fillRect(5 * CELL_SIZE, 0 * CELL_SIZE, 1 * CELL_SIZE, 1 * CELL_SIZE);
        g.fillRect(10 * CELL_SIZE, 5 * CELL_SIZE, 1 * CELL_SIZE, 1 * CELL_SIZE);
    }
}

