import javax.swing.*;
import java.awt.*;

public class Settings {
    private JFrame settingsFrame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton button2 = new JButton("2 players");
    private JButton button3 = new JButton("3 players");
    private JButton button4 = new JButton("4 players");
    private JButton backButton = new JButton("Back to menu");

    public void openSettings(Game game){
        settingsFrame.setSize(400, 600);
        settingsFrame.setResizable(false);
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());
        panel.add(button2, BorderLayout.NORTH);
        panel.add(button3, BorderLayout.CENTER);
        panel.add(button4, BorderLayout.SOUTH);
        //panel.add(backButton, BorderLayout.SOUTH);
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

        /*backButton.addActionListener(e -> {
            settingsFrame.dispose();
            gameMechanics.openMenu();
        });*/

        settingsFrame.setVisible(true);
    }

    public void frameVisible(){
        settingsFrame.setVisible(true);
    }

    public void frameInvisible(){
        settingsFrame.setVisible(false);
    }
}
