import java.util.ArrayList;

public class Game {

    private ArrayList<Integer> path = new ArrayList<>();

    public Game() {
        fillPath();
    }

    public void fillPath(){
        for(int i=0 ; i<40; i++){
            path.add(0);
        }
    }
    public void editPath(){

    }
}
