import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    void player() {
        game.setPlayerCount(2);
        game.player();
        assertNotNull(game.players);
        assertEquals(2, game.players.size());
        for (Player player : game.players) {
            assertNotNull(player.getName());
            assertNotNull(player.getColor());
        }
    }

    @Test
    void moveFigure() {
        game.setPlayerCount(1);
        game.player();
        Player player = game.players.get(0);
        player.setOrderNumber(1);
        Figure figure = player.getFigure(0);
        figure.setPathPosition(10);
        game.moveFigure(player, 3, 0);
        assertEquals(13, figure.getPathPosition());
    }

    @Test
    void figureBackToStart() {
        game.setPlayerCount(1);
        game.player();
        Player player = game.players.get(0);
        player.setOrderNumber(1);
        Figure figure = player.getFigure(0);
        figure.setPathPosition(40);
        game.figureBackToStart(new int[] {10, 10}, player);
        assertEquals(-1, figure.getPathPosition());
    }
}