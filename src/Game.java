import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Integer> path = new ArrayList<>();

    public Game() {
        fillPath();
        player();
        openBoard();
    }

    public void fillPath(){
        for(int i=0 ; i<40; i++){
            path.add(0);
        }
    }
    public void editPath(){

    }

    public void openBoard(){
        JFrame frame = new JFrame("Man Don't Be Angry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 585);
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setVisible(true);
    }

    public void player(){
        Scanner sc = new Scanner(System.in);
        Player p1 = new Player();
        p1.setSerialNumber(1);
        Player p2 = new Player();
        p2.setSerialNumber(2);
        Player p3 = new Player();
        p3.setSerialNumber(3);
        Player p4 = new Player();
        p4.setSerialNumber(4);
        int count = 1;
        boolean wrong = false;
        String error = "";
        do{
            try{
                String name = switch (count){
                    case 1 -> "Player 1";
                    case 2 -> "Player 2";
                    case 3 -> "Player 3";
                    case 4 -> "Player 4";
                    default -> throw new IllegalStateException("Unexpected value: " + count);
                };

                JFrame frame = new JFrame(name);
                frame.setSize(300, 80);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setSize(300, 80);

                JTextField textField = new JTextField(20);
                textField.setToolTipText("Type here");
                frame.add(textField, BorderLayout.NORTH);

                JButton button = new JButton();
                if (!wrong){
                    button.setName("Next");
                }else{
                    button.setName(error);
                }
                button.setSize(300, 20);

                frame.add(button, BorderLayout.SOUTH);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch(frame.getName()){
                            case "Player 1" -> p1.setName(textField.getText());
                            case "Player 2" -> p1.setName(textField.getText());
                            case "Player 3" -> p1.setName(textField.getText());
                            case "Player 4" -> p1.setName(textField.getText());
                        }
                        frame.dispose();
                    }
                });
                frame.add(panel);
                frame.setVisible(true);

                count++;
            }catch(RuntimeException e){
                wrong = true;
                error = e.getMessage();
            }
        }while(count != 5);



    }
}
