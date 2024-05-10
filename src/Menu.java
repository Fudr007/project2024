import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JFrame menuFrame = new JFrame();
    private JButton settingsButton = new JButton("Settings");
    private JButton exitButton = new JButton("Exit");
    private JButton startButton = new JButton("Start");

    public void openMenu(){
        menuFrame.setSize(400, 600);
        menuFrame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(settingsButton, BorderLayout.CENTER);
        panel.add(exitButton, BorderLayout.SOUTH);
        panel.add(startButton, BorderLayout.NORTH);
        menuFrame.add(panel);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menuFrame.setVisible(true);
    }
}
