import java.util.*;
public class Path {
    public static final int EAST = 0;
    public static final int NORTH = 1;
    public static final int WEST = 2;
    public static final int SOUTH = 3;

    private ArrayList<Point> path;
    boolean valid = true;
    //Constructor
    public Path(Point p) {
        path = new ArrayList<>();
        path.add(p);
    }

    //Safe copy
    public Path(Path other) {
        this.path = new ArrayList<Point>(other.getPath());
    }

    public void invalidate() {
        valid = false;
    }

    //Get Methods
    public ArrayList<Point> getPath() {
        return path;
    }
    
    //Movement methods
    public void addNewPoint(Point p) {
        path.add(p);
    }

}