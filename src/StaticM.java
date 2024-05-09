import java.util.ArrayList;
import java.awt.*;
import java.util.Random;

public class StaticM {

    public static ArrayList<Integer> addFigures(ArrayList<Integer> list) {
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        return list;
    }

    public static Color color(int number) {
        return switch (number) {
            case 0 -> Color.BLACK;
            case -1 -> Color.RED;
            case -2 -> Color.GREEN;
            case -3 -> Color.BLUE;
            case -4 -> Color.YELLOW;
            case 5 -> Color.LIGHT_GRAY;
            case 1 -> Color.RED;
            case 2 -> Color.GREEN;
            case 3 -> Color.BLUE;
            case 4 -> Color.YELLOW;
            default -> null;
        };
    }

    public static int dice() {
        Random r = new Random();
        return r.nextInt(6) + 1;
    }

}
