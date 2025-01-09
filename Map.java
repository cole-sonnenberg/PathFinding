import java.util.*;
import java.io.*;

public class Map {
    private char[][] map;
    private int xSize;
    private int ySize;

    public Map(String filename) {
        Scanner s = new Scanner(new FileInputStream(filename));
        xSize = s.nextInt();
        ySize = s.nextInt();
        s.nextLine();
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                map[x][y] = Character.toChar(s.next());
            }
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
        if (map[x][y] == '#') {
            return true;
        }
        return false;
    }

    public Path getShortestPath(Point start, Point goal) {
        
    }
}