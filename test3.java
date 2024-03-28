import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.List;   // List är en collection
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class test3 {

    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);

        int M = scanObj.nextInt();
        int N = scanObj.nextInt();
        scanObj.nextLine();

        char[][] map = new char[M][N];

        for (int i = 0; i < M; i++) {
            String line = scanObj.nextLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        String result = hittaStigar(map, M, N);
        System.out.println(result);

    }

    private static String hittaStigar(char[][] map, int m, int n) {
        StringBuilder paths = new StringBuilder(); // Use StringBuilder for efficiency

        Set<Character> characters = new HashSet<>();

        for (int col = 0; col < n; col++) {
            Deque<int[]> stack = new LinkedList<>();   // istället för stack, mer flexibelt.
            boolean[][] visited_letters = new boolean[m][n];  // besökta "celler"
            Set<Character> currentPathChars = new HashSet<>(); // kontrollera celler i nuvarande stig.
            if (map[0][col] == map[m - 1][col]) {
                stack.push(new int[]{0, col});  // Lägg startpositionen på en stack.
                visited_letters[0][col] = true;     // börjar med rad 0, varje kolumn-cell.

                while (!stack.isEmpty()) {
                    int[] position = stack.pop();
                    int row = position[0];
                    int column = position[1];

                    if (row >= 0 && row < m && column >= 0 && column < n && !visited_letters[row][column]) {
                        visited_letters[row][column] = true;

                        // om tillåtet och inte redan existerande, lägg till i setet.
                        if (!currentPathChars.contains(map[row][column])) {
                            currentPathChars.add(map[row][column]);
                            characters.add(map[row][column]);
                        }

                        stack.push(new int[]{row - 1, column});    // Lägg granpunkter på toppen av dequen (blir som en stack).
                        stack.push(new int[]{row + 1, column});
                        stack.push(new int[]{row, column - 1});
                        stack.push(new int[]{row, column + 1});
                    }
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
