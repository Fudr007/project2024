import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testChooseName() {
        String name = player.chooseName();
        assertNotNull(name);
        assertEquals(name, player.getName());
    }

    @Test
    public void testIsValidName() {
        assertTrue(player.isValidName("Pitr"));
        assertFalse(player.isValidName("123"));
    }

    @Test
    public void testHowMuchMovable() {
        player.setOrderNumber(1);
        player.getFigure(0).setPathPosition(38);
        assertEquals(6, player.howMuchMovable(0));
        player.getFigure(0).setPathPosition(42);
        assertEquals(2, player.howMuchMovable(0));
    }

    @Test
    public void testKickOutFigure() {
        player.setOrderNumber(1);
        player.getFigure(0).setPathPosition(10);
        player.kickOutFigure(0);
        assertEquals(-1, player.getFigure(0).getPathPosition());
    }

}