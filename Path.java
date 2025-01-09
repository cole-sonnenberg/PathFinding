import java.util.*;
public class Path {
    private ArrayList<Point> path;
    boolean valid;
    //Constructor
    public Path(Point p) {
        path = new ArrayList<>();
        path.add(p);
    }

    //Safe copy
    public Path(Path other) {
        this.path = new ArrayList<Point>(other.getPath());
    }

    //Get Methods
    public ArrayList<Point> getPath() {
        return path;
    }
    
    //Movement methods
    public Path addUp(Point p) {
        if (!isWall(p))
    }

}