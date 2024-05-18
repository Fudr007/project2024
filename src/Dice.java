import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Dice {
    private JFrame diceFrame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton numberButton = new JButton("next number");
    private JLabel randomNumber = new JLabel("Press the button");
    public int thrownNumber = 0;

    public void openDice(){
        diceFrame.setSize(500, 100);
        diceFrame.setResizable(false);
        diceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        panel.setLayout(new BorderLayout());
        panel.add(randomNumber, BorderLayout.NORTH);
        panel.add(numberButton, BorderLayout.SOUTH);
        diceFrame.add(panel);

        numberButton.addActionListener(e -> {
            randomNumber.setText(""+getNumber());
            thrownNumber = Integer.parseInt(randomNumber.getText());
        });
        diceFrame.setVisible(true);
    }

    private int getNumber(){
        Random r = new Random();
        return r.nextInt(6)+1;
    }

    public void changeName(Player p){
        diceFrame.setName(p.getName()+" is throwing");
    }

    public int getThrownNumber(){
        return thrownNumber;
    }

    public void clearNumber(){
        this.thrownNumber = 0;
    }
}