import java.util.*;

public class Main {
    public static void main(String[] args) {
        String command;
        Map map = new Map("map1.txt");
        Scanner input = new Scanner(System.in);
        Path shortestPath = map.getShortestPath();
        System.out.println(shortestPath);
        do {
            System.out.println(map);
            command = input.nextLine();
            map.movePlayer(shortestPath);
        } while (!command.equalsIgnoreCase("Q") || !map.finished());
    }
} 