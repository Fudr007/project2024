import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * The Dice class represents a dice in the game.
 *
 * It allows the player to roll the dice and get a random number between 1 and 6.
 */
public class Dice {

    private JFrame diceFrame = new JFrame();

    private JPanel panel = new JPanel();

    /**
     * The JButton that triggers the dice roll.
     */
    private JButton numberButton = new JButton("next number");

    /**
     * The JLabel that displays the random number.
     */
    private JLabel randomNumber = new JLabel("Press the button");

    /**
     * The thrown number, which is the result of the dice roll.
     */
    public int thrownNumber = 0;

    /**
     * The openDice method opens the dice frame and allows the player to roll the dice.
     */
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

    /**
     * The getNumber method returns a random number between 1 and 6.
     *
     * @return a random number between 1 and 6
     */
    private int getNumber(){
        Random r = new Random();
        return r.nextInt(6)+1;
    }

    /**
     * The getThrownNumber method returns the thrown number.
     *
     * @return the thrown number
     */
    public int getThrownNumber(){
        return thrownNumber;
    }

    /**
     * The clearNumber method clears the thrown number.
     */
    public void clearNumber(){
        this.thrownNumber = 0;
    }

    /**
     * The setName method sets the name of the dice frame.
     *
     * @param player the player who rolled the dice
     */
    public void setName(Player player){
        diceFrame.setName(player.getName());
    }
}