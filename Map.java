import java.util.*;
import java.io.*;

public class Map {
    private String[][] map;
    private int xSize;
    private int ySize;
    private Point start;
    private Point goal;

    public Map(String filename) {
        try {
            Scanner s = new Scanner(new FileInputStream(filename));
            xSize = s.nextInt();
            ySize = s.nextInt();
            System.out.println(xSize + " " + ySize);
            s.nextLine();
            map = new String[xSize][ySize];
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    map[x][y] = s.next();
                    if (map[x][y].equals("@")) {
                        start = new Point(x, y);
                    }
                    if (map[x][y].equals("G")) {
                        goal = new Point(x, y);
                    }
                    //System.out.print(map[x][y] + " ");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\nError in reading map from file");
        }
    }

    public String toString() {
        String s = new String();
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                s += map[x][y] + " ";
            }
            s += "\n";
        }
        return s;
    }

    public boolean isWall(Point p) {
        int x = p.getX();
        int y = p.getY();
        if (x < 0 || x >= xSize) {
            return true;
        }
        if (y < 0 || y >= ySize) {
            return true;
        }
        if (map[x][y].equals('#')) {
            return true;
        }
        return false;
    }

    public Path getShortestPath() {
        System.out.println(start + " " + goal);
        return getShortestPath(start, goal, new Path());
    }

    public Path getShortestPath(Point a, Point b, Path oldPath) {
        Path path = new Path(oldPath);
        Path[] splitPaths = new Path[4];
        boolean stuck = true;
        int shortestPathLength = -1;
        int shortestPath = -1;
        path.addNewPoint(a);
        if (a.equals(b)) {
            return path;
        }
        for (int d = 0; d < 4; d++) {
            if (!isWall(a.move(d)) && !path.getList().contains(a.move(d))) {
                stuck = false;
                try {
                    splitPaths[d] = getShortestPath(a.move(d), b, path);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        if (stuck) {
            path.invalidate();
            return path;
        }
        for (int d = 0; d < 4; d++) {
            if ((shortestPath == -1 && splitPaths[d].getValidity()) || (splitPaths[d].getList().size() < shortestPathLength && splitPaths[d].getValidity())) {
                shortestPath = splitPaths[d].getList().size();
                shortestPath = d;
            }
        }
        if (shortestPath == -1) {
            path.invalidate();
            return path;
        } else {
            return splitPaths[shortestPath];
        }
    }
}