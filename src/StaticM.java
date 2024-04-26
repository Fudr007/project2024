import java.util.ArrayList;
import java.awt.*;
import java.util.Random;

public class StaticM {

    public static ArrayList<Integer> addFigures(ArrayList<Integer> list, int number){
        list.add(number);
        list.add(number);
        list.add(number);
        list.add(number);
        return list;
    }

    public static Color color(int number){
        switch (number){
            case 0:
                return Color.RED;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.YELLOW;
            default:
                return Color.BLACK;
        }
    }

    public static int dice(){
        Random r = new Random();
        return r.nextInt(6) + 1;
    }

}
