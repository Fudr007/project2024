import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Integer> path = new ArrayList<>();
    private GamePanel gamePanel = new GamePanel();
    private JFrame frame = new JFrame("Man Don't Be Angry");

    public Game() throws IOException {
        player();
        openBoard();
        gamePanel.doIt();
    }

    public void openBoard(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 585);
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

                JFrame nameFrame = new JFrame(name);
                nameFrame.setSize(300, 80);
                nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                nameFrame.setLocationRelativeTo(null);

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setSize(300, 80);

                JTextField textField = new JTextField(20);
                textField.setToolTipText("Type here");
                nameFrame.add(textField, BorderLayout.NORTH);

                JButton button = new JButton();
                if (!wrong){
                    button.setName("Next");
                }else{
                    button.setName(error);
                }
                button.setSize(300, 20);

                nameFrame.add(button, BorderLayout.SOUTH);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch(nameFrame.getName()){
                            case "Player 1" -> p1.setName(textField.getText());
                            case "Player 2" -> p1.setName(textField.getText());
                            case "Player 3" -> p1.setName(textField.getText());
                            case "Player 4" -> p1.setName(textField.getText());
                        }
                        nameFrame.dispose();
                    }
                });
                nameFrame.add(panel);
                nameFrame.setVisible(true);

                count++;
            }catch(RuntimeException e){
                wrong = true;
                error = e.getMessage();
            }
        }while(count != 5);

    }
}
