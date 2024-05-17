import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {

    private String name;
    private int serialNumber;
    private boolean atHome = false;
    private int howManyAtHome = 0;
    private ArrayList<Integer> figuresPosition = new ArrayList<>(4);

    public Player(String name, int serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
        StaticM.addFigures(figuresPosition);
    }

    public Player() {
        figuresPosition = StaticM.addFigures(figuresPosition);
    }

    public String getName() {
        return name;
    }

    public String chooseName() {

        JFrame frame = new JFrame("Set Player Name "+ getSerialNumber());
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Set name");
        frame.setSize(300, 150);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        button.addActionListener(e -> {
            String input = textField.getText();
            if (isValidName(input)) {
                JOptionPane.showMessageDialog(frame, "Name set to: " + input);
                setName(input);
                frame.dispose();
            } else {
                button.setText("Wrong format, again please");
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        });
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        return textField.getText();
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]{1,10}$");
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isAtHome() {
        int count = 0;
        for (int i = 0; i<figuresPosition.size(); i++){
            count += figuresPosition.get(i);
        }
        if (count == 170) {
            return atHome = true;
        }else{
            return atHome = false;
        }
    }

    public int getFiguresPosition(int i) {
        return figuresPosition.get(i);
    }

    public void setFiguresPosition(int which, int i) {
        figuresPosition.set(which, i);
    }
}