import java.util.ArrayList;
import java.awt.*;

/**
 * The StaticM is class where the static methods are
 */
public class StaticM {

    /**
     * It loads the figures to the incoming array
     * @param list to load up
     * @return loaded list
     */
    public static ArrayList<Figure> addFigures(ArrayList<Figure> list) {
        for (int i = 0; i < 4; i++) {
            list.add(new Figure());
        }
        return list;
    }

    /**
     * Method which change number to corresponding color
     * @param number the number
     * @return the color
     */
    public static Color color(int number) {
        Color figureRed = new Color(150, 0, 0);
        Color figureBlue = new Color(0, 0, 150);
        Color figureGreen = new Color(0, 150, 0);
        Color figureYellow = new Color(160, 160, 0);
        return switch (number) {
            case 0 -> Color.DARK_GRAY;
            case -1 -> Color.RED;
            case -2 -> Color.GREEN;
            case -3 -> Color.BLUE;
            case -4 -> Color.YELLOW;
            case 5 -> Color.LIGHT_GRAY;
            case 1 -> figureRed;
            case 2 -> figureGreen;
            case 3 -> figureBlue;
            case 4 -> figureYellow;
            default -> null;
        };
    }

    /**
     * Opposite of the color method
     * @param color the incoming color
     * @return integer based on the color
     */
    public static int colorToInt(Color color) {
        int colorInt = 0;
        if (color == Color.RED) {
            colorInt = 1;
        }
        if (color == Color.GREEN) {
            colorInt = 2;
        }
        if (color == Color.BLUE) {
            colorInt = 3;
        }
        if (color == Color.YELLOW) {
            colorInt = 4;
        }
        return colorInt;
    }

    /**
     * Returns shift that is used for moving on path for different players
     * @param player incoming player
     * @return shift value based on players order number
     */
    public static int playerShift(Player player) {
        return switch (player.getOrderNumber()){
            case 1 -> 0;
            case 2 -> 10;
            case 3 -> 20;
            case 4 -> 30;
            default -> throw new IllegalStateException("Unexpected value: " + player.getOrderNumber());
        };
    }
}
