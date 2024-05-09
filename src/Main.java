import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            Game game = new Game();
        } catch (IOException e) {
            System.out.println("Something bad happened :/ Try it again");
        }
    }
}