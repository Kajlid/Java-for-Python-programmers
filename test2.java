import java.util.Scanner;
import java.util.Set;
import java.util.List;   // List är en collection
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class test2 {

    public static String hittaStigar(char[][] map, int m, int n) {
        boolean[][] visited_letters = new boolean[m][n];
    
        Deque<int[]> stack = new LinkedList<>();
    
        StringBuilder paths = new StringBuilder(); // Use StringBuilder for efficiency
    
        Set<Character> characters = new HashSet<>();
    
        for (int col = 0; col < n; col++) {
            if (map[0][col] == map[m - 1][col]) {
                stack.push(new int[]{0, col}); // Push the starting position onto the stack
                visited_letters[0][col] = true;
    
                while (!stack.isEmpty()) {
                    int[] position = stack.pop(); // Pop the top element of the stack
                    int row = position[0];
                    int column = position[1];
    
                    if (row < 0 || row >= m || column < 0 || column >= n || characters.contains(map[row][column])) {
                        continue;
                    }
    
                    characters.add(map[row][column]);
    
                    stack.push(new int[]{row - 1, column}); // Push neighboring positions onto the stack
                    stack.push(new int[]{row + 1, column});
                    stack.push(new int[]{row, column - 1});
                    stack.push(new int[]{row, column + 1});
                }
            }
        }
    
        Character[] sortedChars = characters.toArray(new Character[0]);
        Arrays.sort(sortedChars);
    
        for (Character c : sortedChars) {
            paths.append(c);
        }
    
        return paths.length() == 0 ? "0" : paths.toString();
    }
    
    
}
