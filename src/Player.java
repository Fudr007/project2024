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

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches("^[a-zA-Z]*")){
            this.name = name;
        }else{
            throw new RuntimeException("Invalid name! Only letters are allowed");
        }
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isAtHome() {
        return atHome;
    }

    public void setAtHome(boolean atHome) {
        this.atHome = atHome;
    }

    public int getHowManyAtHome() {
        return howManyAtHome;
    }

    public void setHowManyAtHome(int howManyAtHome) {
        this.howManyAtHome = howManyAtHome;
    }
}
