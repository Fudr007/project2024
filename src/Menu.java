import javax.swing.*;
import java.awt.*;

/**
 * Its the menu where the player is choosing what to do
 */
public class Menu {
    private JFrame menuFrame = new JFrame();
    private JButton settingsButton = new JButton("Settings");
    private JButton exitButton = new JButton("Exit");
    private JButton startButton = new JButton("Start");

    /**
     * It contains value of the chosen action
     */
    private int action = -1;
    private JPanel panel = new JPanel();

    /**
     * It sets, opens the menu frame and waits for action
     */
    public void openMenu(){
        menuFrame.setSize(400, 600);
        menuFrame.setResizable(false);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());
        panel.add(settingsButton, BorderLayout.CENTER);
        panel.add(exitButton, BorderLayout.SOUTH);
        panel.add(startButton, BorderLayout.NORTH);
        menuFrame.add(panel);
        exitButton.addActionListener(e -> {
            setAction(0);
            System.exit(0);
        });

        startButton.addActionListener(e -> {
            setAction(1);
            menuFrame.setVisible(false);
        });

        settingsButton.addActionListener(e -> {
            setAction(2);
            menuFrame.setVisible(false);
        });
        menuFrame.setVisible(true);
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void frameVisible(){
        menuFrame.setVisible(true);
    }
    public JFrame getFrame() {
        return menuFrame;
    }

}
