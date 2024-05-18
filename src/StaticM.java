import java.util.ArrayList;
import java.awt.*;

public class StaticM {

    public static ArrayList<Integer> addFigures(ArrayList<Integer> list) {
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        return list;
    }

    public static Color color(int number) {
        Color figureRed = new Color(150, 0, 0);
        Color figureBlue = new Color(0, 0, 150);
        Color figureGreen = new Color(0, 150, 0);
        Color figureYellow = new Color(160, 160, 0);
        return switch (number) {
            case 0 -> Color.BLACK;
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

    public static int playerShift(Player player) {
        return switch (player.getOrderNumber()){
            case 1 -> 1;
            case 2 -> 10;
            case 3 -> 20;
            case 4 -> 30;
            default -> throw new IllegalStateException("Unexpected value: " + player.getOrderNumber());
        };
    }
}
