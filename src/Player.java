import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Player {

    private String name;
    private int orderNumber;
    private boolean atHome = false;
    private int howManyAtHome = 0;
    private ArrayList<Figure> figures = new ArrayList<>(4);
    private int whichFigure = 0;
    private Color color;

    public Player(String name, int orderNumber) {
        this.name = name;
        this.orderNumber = orderNumber;
        StaticM.addFigures(figures);
    }

    public Player() {
        figures = StaticM.addFigures(figures);
        setStartingPosition();
    }

    public String getName() {
        return name;
    }

    public String chooseName() {
        JFrame frame = new JFrame("Set Player Name " + getOrderNumber());
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

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean isAtHome() {
        int count = 0;
        for (int i = 0; i < figures.size(); i++) {
            count += figures.get(i).getPathPosition();
        }
        if (count == 170) {
            return atHome = true;
        } else {
            return atHome = false;
        }
    }

    public boolean isMovable(int which) {
        int shift = StaticM.playerShift(Player.this);
        if (shift == 1) {
            shift = 0;
        }
        if (figures.get(which).getPathPosition() >= (41 + (orderNumber * shift)) && figures.get(which).getPathPosition() <= 44 + (orderNumber * shift)) {
            return false;
        } else {
            return true;
        }
    }

    public int howMuchMovable(int which) {
        if (isMovable(which)) {
            int shift = StaticM.playerShift(Player.this);
            if (shift == 1) {
                shift = 0;
            }
            if (figures.get(which).getPathPosition() >= 38 + shift) {
                return (44+shift)-(figures.get(which).getPathPosition()+shift);
            } else {
                return 6;
            }
        } else {
            return 0;
        }
    }

    public void setHowManyAtHome(){
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (!isMovable(figures.get(i).getPathPosition())){
                count++;
            }
        }
        this.howManyAtHome = count;
    }

    public int getHowManyAtHome() {
        return howManyAtHome;
    }

    public int whichFigure(){
        JFrame frame = new JFrame("Which figure do you want to move "+getName()+"?");
        JTextField textField = new JTextField(1);
        JButton button = new JButton("Enter");
        frame.setSize(300, 150);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        frame.add(panel);

        button.addActionListener(e -> {
            String input = textField.getText();
            if (input.matches("^[1-4]")) {
                int index = Integer.parseInt(input);
                setWhichFigure(index);
                frame.dispose();
            } else {
                button.setText("Just members 1 to 4");
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
        frame.setVisible(true);
        int remember = getWhichFigure();
        System.out.println(getWhichFigure());
        setWhichFigure(0);
        System.out.println(remember);
        return remember;
    }

    public int getWhichFigure() {
        return whichFigure;
    }

    public void setWhichFigure(int whichFigure) {
        this.whichFigure = whichFigure;
    }

    public void kickOutFigure(int whichFigure){
        figures.get(whichFigure).setPathPosition(0);
        figures.get(whichFigure).setXY(figures.get(whichFigure).getStartringx(), figures.get(whichFigure).getStartringy());
    }

    public void setStartingPosition(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("homes.txt"));
            String line;
            int count = 0;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (orderNumber == Integer.parseInt(parts[2])){
                    figures.get(count).setStartringYX(Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
                    count++;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found, " +
                    "check if you have every file that is needed to run this program and try again");
            System.exit(0);
        }

    }

    public Figure getFigure(int whichFigure) {
        return figures.get(whichFigure);
    }

    public Color getColor() {
        return color;
    }

    public void setColor() {
        this.color = StaticM.color(-orderNumber);
    }
}