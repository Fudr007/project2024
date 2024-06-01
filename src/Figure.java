public class Figure {
    private int x;
    private int y;
    private int startringx;
    private int startringy;
    private int pathPosition;

    @Override
    public String toString() {
        return "Figure{" +
                "x=" + x +
                ", y=" + y +
                ", startringx=" + startringx +
                ", startringy=" + startringy +
                ", pathPosition=" + pathPosition +
                '}';
    }

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
