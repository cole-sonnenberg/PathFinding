import java.util.*;
import java.io.*;

public class Map {
    private String[][] map;
    private int xSize;
    private int ySize;

    public Map(String filename) {
        try {
            Scanner s = new Scanner(new FileInputStream(filename));
            xSize = s.nextInt();
            ySize = s.nextInt();
            s.nextLine();
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    map[x][y] = s.next();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\nError in reading map");
        }
    }

    public String toString() {
        String s = new String();
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                s += map[x][y];
            }
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

    public Path getShortestPath(Point start, Point goal, Path oldPath) {
        Path path = new Path(oldPath);
        Path[] splitPaths = new Path[4];
        boolean stuck = true;
        int shortestPathLength;
        int shortestPath;
        path.addNewPoint(start);
        for (int d = 0; d < 4; d++) {
            if (!isWall(start.move(d)) && !path.getPath().contains(start.move(d))) {
                stuck = false;
                splitPaths[d] = getShortestPath(start.move(d), goal, path);
            }
        }
        if (stuck) {
            path.invalidate();
            return path;
        }
        return null; //remove
    }
}