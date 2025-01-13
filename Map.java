import java.util.*;
import java.io.*;

public class Map {
    public static final int EAST = 0;
    public static final int NORTH = 1;
    public static final int WEST = 2;
    public static final int SOUTH = 3;
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
            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < xSize; x++) {
                    map[x][y] = s.next();
                    if (map[x][y].equals("@")) {
                        start = new Point(x, y);
                        map[x][y] = ".";
                    }
                    if (map[x][y].equals("G")) {
                        goal = new Point(x, y);
                        map[x][y] = ".";
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
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (x == start.getX() && y == start.getY()) {
                    s += "@ ";
                } else if (x == goal.getX() && y == goal.getY()) {
                    s += "G ";
                } else {
                    s += map[x][y] + " ";
                }
            }
            s += "\n";
        }
        return s;
    }

    public boolean isWall(Point p) {
        boolean DEBUG = false;
        int x = p.getX();
        int y = p.getY();
        if (x < 0 || x >= xSize) {
            return true;
        }
        if (y < 0 || y >= ySize) {
            return true;
        }
        if (map[x][y].equals("#")) {
            if (DEBUG) {
                System.out.println(p + " IS A WALL");
            }
            return true;
        }
        return false;
    }

    public Path getShortestPath() {
        System.out.println("Start: " + start + "     Goal: " + goal);
        return getShortestPath(start, goal, new Path());
    }

    public Path getShortestPath(Point a, Point b, Path oldPath) {
        boolean DEBUG = false;
        Path path = new Path(oldPath);
        Path[] splitPaths = new Path[4];
        boolean stuck = true;
        boolean alreadyVisited;
        int shortestPathLength = -1;
        int shortestPath = -1;
        path.addNewPoint(a);
        if (DEBUG) {
            System.out.println(path);
        }
        if (a.equals(b)) {
            if (DEBUG) {
                System.out.println("Goal Found");
            }
            return path;
        }
        for (int d = 0; d < 4; d++) {
            alreadyVisited = false;
            for (Point p : path.getList()) {
                if (p.equals(a.move(d))) {
                    alreadyVisited = true;
                    break;
                }
            }
            if (DEBUG) {
                System.out.println("Testing " + d);
            }
            if (!isWall(a.move(d)) && !alreadyVisited) {
                if (DEBUG) {
                    System.out.println("Going " + d);
                }
                stuck = false;
                try {
                    splitPaths[d] = getShortestPath(a.move(d), b, path);
                } catch (Exception e) {
                    System.out.println("Error caught\n" + e.getMessage());
                    System.exit(1);
                }
            }
        }
        if (stuck) {
            path.invalidate();
            return path;
        }
        for (int d = 0; d < 4; d++) {
            if (splitPaths[d] != null && ((shortestPath == -1 && splitPaths[d].getValidity()) || (splitPaths[d].getList().size() < shortestPathLength && splitPaths[d].getValidity()))) {
                shortestPathLength = splitPaths[d].getList().size();
                shortestPath = d;
            }
        }
        if (shortestPath == -1) {
            path.invalidate();
            return path;
        } else {
            if (DEBUG) {
                System.out.println("Selecting " + shortestPath);
            }
            return splitPaths[shortestPath];
        }
    }

    public void movePlayer(Path shortestPath) {
        boolean DEBUG = false;
        if (DEBUG) {
            System.out.println(start);
        }
        if (shortestPath.getList().size() == 1) {
            return;
        }
        if (shortestPath.getList().get(1).equals(start.move(EAST))) {
            start = start.move(EAST);
            shortestPath.getList().remove(1);
            return;
        } else if (DEBUG) {
            System.out.println(start.move(EAST) + " is not equal to " + shortestPath.getList().get(1));
        }
        if (shortestPath.getList().get(1).equals(start.move(NORTH))) {
            start = start.move(NORTH);
            shortestPath.getList().remove(1);
            return;
        } else if (DEBUG) {
            System.out.println(start.move(NORTH) + " is not equal to " + shortestPath.getList().get(1));
        }
        if (shortestPath.getList().get(1).equals(start.move(WEST))) {
            start = start.move(WEST);
            shortestPath.getList().remove(1);
            return;
        } else if (DEBUG) {
            System.out.println(start.move(WEST) + " is not equal to " + shortestPath.getList().get(1));
        }
        if (shortestPath.getList().get(1).equals(start.move(SOUTH))) {
            start = start.move(SOUTH);
            shortestPath.getList().remove(1);
            return;
        } else if (DEBUG) {
            System.out.println(start.move(SOUTH) + " is not equal to " + shortestPath.getList().get(1));
        }
    }

    public boolean finished() {
        boolean DEBUG = false;
        if (start.equals(goal)) {
            return true;
        } else {
            if (DEBUG) {
                System.out.println(start + " is not equal to " + goal);
            }
            return false;
        }
    }
}