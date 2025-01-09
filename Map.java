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

    public boolean isWall(int x, int y) {
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

    public Path getShortestPath(Point start, Point goal) {
        
    }

    public Path getShortestPath(Path path, Point goal) {

    }
}