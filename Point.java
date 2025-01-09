public class Point {
    public static final int EAST = 0;
    public static final int NORTH = 1;
    public static final int WEST = 2;
    public static final int SOUTH = 3;

    private int x;
    private int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Point move(int d) {
        if (d == EAST) {
            return new Point(x+1, y);
        }
        if (d == NORTH) {
            return new Point(x, y-1);
        }
        if (d == WEST) {
            return new Point(x-1, y);
        }
        if (d == SOUTH) {
            return new Point(x, y+1);
        }
        return null;
    }
}