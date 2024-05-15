import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public JFrame menuFrame = new JFrame();
    private JButton settingsButton = new JButton("Settings");
    private JButton exitButton = new JButton("Exit");
    private JButton startButton = new JButton("Start");
    private int action = -1;

    public void openMenu(){
        menuFrame.setSize(400, 600);
        menuFrame.setResizable(false);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
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
}
