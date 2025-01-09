import java.util.*;
public class Path {
    private ArrayList<Point> path;
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
    public Path getPath() {
        return path;
    }
    
}