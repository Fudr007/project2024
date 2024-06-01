import javax.swing.*;
import java.awt.*;

/**
 * The Settings is frame where player choose how many players will be playing
 */
public class Settings {
    private JFrame settingsFrame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton button2 = new JButton("2 players");
    private JButton button3 = new JButton("3 players");
    private JButton button4 = new JButton("4 players");

    /**
     * It sets, open the settings and waits for input
     * @param game the game
     */
    public void openSettings(Game game){
        settingsFrame.setSize(400, 600);
        settingsFrame.setResizable(false);
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());
        panel.add(button2, BorderLayout.NORTH);
        panel.add(button3, BorderLayout.CENTER);
        panel.add(button4, BorderLayout.SOUTH);

        settingsFrame.add(panel);
        button2.addActionListener(e -> {
            game.setPlayerCount(2);
            JOptionPane.showMessageDialog(settingsFrame, "Player count set to: 2");
            settingsFrame.setVisible(false);
            game.getMenu().frameVisible();
        });

        button3.addActionListener(e -> {
            game.setPlayerCount(3);
            JOptionPane.showMessageDialog(settingsFrame, "Player count set to: 3");
            settingsFrame.setVisible(false);
            game.getMenu().frameVisible();
        });

        button4.addActionListener(e -> {
            game.setPlayerCount(4);
            JOptionPane.showMessageDialog(settingsFrame, "Player count set to: 4");
            settingsFrame.setVisible(false);
            game.getMenu().frameVisible();
        });

        settingsFrame.setVisible(true);
    }
}
