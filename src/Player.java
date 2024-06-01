import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * The Player class represents a player in the game.
 *
 * It contains information about the player, such as their name, order number, and figures.
 */
public class Player {

    private String name;

    /**
     * The order number of the player.
     */
    private int orderNumber;

    /**
     * Whether the player is at home.
     */
    private boolean atHome = false;

    /**
     * The number of figures that are at home.
     */
    private int howManyAtHome = 0;

    /**
     * The list of figures belonging to the player.
     */
    private ArrayList<Figure> figures = new ArrayList<>(4);

    /**
     * The index of the figure that the player wants to move.
     */
    private int whichFigure = 0;

    /**
     * The color of the player.
     */
    private Color color;

    public Player() {
        figures = StaticM.addFigures(figures);
        setStartingPosition();
    }

    public String getName() {
        return name;
    }

    /**
     * The chooseName method allows the player to choose their name.
     *
     * @return the chosen name
     */
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

    /**
     * The isValidName method checks if a name is valid.
     *
     * @param name the name to check
     * @return true if the name is valid
     */
    public boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]{1,10}$");
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * The isAtHome method checks if the player is at home.
     *
     * @return true if the player is at home
     */
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

    /**
     * The isMovable method checks if a figure is movable.
     *
     * @param which the index of the figure
     * @return true if the figure is movable
     */
    public boolean isMovable(int which) {
        int shift = StaticM.playerShift(Player.this);
        if (figures.get(which).getPathPosition() >= (41 + (orderNumber * shift)) && figures.get(which).getPathPosition() <= 44 + (orderNumber * shift)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * The howMuchMovable method returns how much a figure can be moved.
     *
     * @param which the index of the figure
     * @return the number of steps the figure can be moved
     */
    public int howMuchMovable(int which) {
        if (isMovable(which)) {
            int shift = StaticM.playerShift(Player.this);
            if (figures.get(which).getPathPosition() >= 38 + shift) {
                System.out.println((44+shift)-(figures.get(which).getPathPosition()));
                return (44+shift)-(figures.get(which).getPathPosition());
            } else {
                System.out.println(6);
                return 6;
            }
        } else {
            System.out.println(0);
            return 0;
        }
    }

    /**
     * The setHowManyAtHome method sets the number of figures that are at home.
     */
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

    /**
     * The whichFigure method allows the player to choose which figure to move.
     */
    public void whichFigure(){
        JFrame frame = new JFrame("Which figure do you want to move "+getName()+"?");
        JTextField textField = new JTextField(1);
        JButton button = new JButton("Enter");
        frame.setSize((name.length()*6+400), 150);
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
        setWhichFigure(0);
    }

    public int getWhichFigure() {
        return whichFigure;
    }

    public void setWhichFigure(int whichFigure) {
        this.whichFigure = whichFigure;
    }

    /**
     * The kickOutFigure method kicks out a figure from its current position.
     *
     * @param whichFigure the index of the figure
     */
    public void kickOutFigure(int whichFigure){
        figures.get(whichFigure).setPathPosition(-1);
        figures.get(whichFigure).setXY(figures.get(whichFigure).getStartringx(), figures.get(whichFigure).getStartringy());
    }

    /**
     * The setStartingPosition method sets the starting position of the figures.
     */
    public void setStartingPosition(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("homes.txt"));
            String line;
            int count = 0;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (orderNumber == Integer.parseInt(parts[2])){
                    figures.get(count).setStartringYX(Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
                    figures.get(count).setXY(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
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