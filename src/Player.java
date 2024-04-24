import java.util.ArrayList;

public class Player {

    private String name;
    private int serialNumber;
    private boolean atHome = false;
    private int howManyAtHome = 0;
    private ArrayList<Integer> figures = new ArrayList<>();

    public Player(String name, int serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
        StaticM.addFigures(figures, serialNumber);
    }


}
