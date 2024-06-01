/**
 * The Figure class represent figures in the game
 *
 * It helps to keep track of the figures
 */
public class Figure {

    private int x;
    private int y;

    /**
     * The startringx keeps x starting coordinate of the figure
     */
    private int startringx;

    /**
     * The startringx keeps y starting coordinate of the figure
     */
    private int startringy;

    /**
     * The pathPosition contains figures position on the path
     */
    private int pathPosition = -1;

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getStartringy() {
        return startringy;
    }

    public int getStartringx() {
        return startringx;
    }

    public void setStartringYX(int startringy, int startringx) {
        this.startringy = startringy;
        this.startringx = startringx;
    }

    public int getPathPosition() {
        return pathPosition;
    }

    public void setPathPosition(int pathPosition) {
        this.pathPosition = pathPosition;
    }
}
