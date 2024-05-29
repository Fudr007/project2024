import javax.swing.*;

public class Game {
    private GameMechanics gameMechanics = new GameMechanics();
    private int who = 1;

    public Game() {
        gameStart();
    }

    public void gameStart() {
        gameMechanics.openMenu();
        gameMechanics.player();
        gameMechanics.getGamePanel().set();

        for (int i = 0; i < gameMechanics.getPlayerCount(); i++) {
            gameMechanics.getPlayers().get(i).setStartingPosition();
        }
        gameMechanics.setFigures();
        gameMechanics.getDice().openDice();

        boolean end = false;
        while (!end) {
            boolean ok = false;
            gameMechanics.getDice().changeName(gameMechanics.getPlayers().get(who - 1));
            while (gameMechanics.getDice().getThrownNumber() == 0) {
                System.out.print("");
            }
            int which = -1;
            if (gameMechanics.getPlayers().get(who - 1).getHowManyAtHome() < 3) {
                while (!ok) {
                    which = gameMechanics.getPlayers().get(who - 1).whichFigure();
                    while (which == -1) {
                        which = gameMechanics.getPlayers().get(who - 1).getWhichFigure();
                    }
                    if (gameMechanics.getPlayers().get(who - 1).howMuchMovable(which) < gameMechanics.getDice().getThrownNumber()) {
                        ok = false;
                        JOptionPane.showMessageDialog(gameMechanics.getGamePanel().getFrame(), "You can't move with this one");
                    } else {
                        ok = true;
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if (gameMechanics.getPlayers().get(who - 1).isMovable(i)) {
                        which = i;
                    }
                }
            }

            gameMechanics.moveFigure(gameMechanics.getPlayers().get(who - 1), gameMechanics.getDice().getThrownNumber(), which);
            //end
            for (int i = 0; i < gameMechanics.getPlayerCount(); i++) {
                if (gameMechanics.getPlayers().get(i).isAtHome()) {
                    end = true;
                    JOptionPane.showMessageDialog(gameMechanics.getGamePanel().getFrame(), gameMechanics.getPlayers().get(i).getName() + " win!!! Congratulations!");
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                        JOptionPane.showMessageDialog(gameMechanics.getGamePanel().getFrame(),"interupted");
                        System.exit(0);
                    }
                }
            }
            if (who == gameMechanics.getPlayerCount()) {
                this.who = 1;
            } else {
                this.who = who + 1;
            }
            gameMechanics.getDice().clearNumber();
        }
    }
}