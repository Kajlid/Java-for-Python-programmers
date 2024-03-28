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

// inspiration: https://www.geeksforgeeks.org/depth-first-traversal-dfs-on-a-2d-array/ 
public class test2 {

        /*
         Programmet ska skriva ut alla bokstäver (i bokstavsordning) 
         som förbinder kartans översta och understa rad med varandra via en eller flera stigar
         Två rutor i kartan anses tillhöra samma stig om de ligger antingen direkt ovanför/under, eller bredvid varandra. 
         Inga diagonaler räknas alltså.
         Om det inte existerar några stigar alls, ska programmet skriva ut "0" (en nolla).
    
         I indata anges först två heltal, som anger antalet rader (M), resp kolumner (N), i den ordningen. På raden därefter följer själva kartan, som M rader med N tecken i varje rad.
    
         Du kan anta att N och M är heltal, och minst 1 och högst 10 000, samt att kartan består av versaler.
    
    
         */
    
        public static void main (String[] args) {
            // int M = 6;
            // int N = 6;
    
            Scanner scanObj = new Scanner(System.in);  // användare skriver M, sedan mellanslag, sedan N
    
            int M = scanObj.nextInt();
            int N = scanObj.nextInt();
            scanObj.nextLine(); 
    
            char[][] map = new char[M][N];
    
            for (int i = 0; i < M; i++) {
                String line = scanObj.nextLine();         // användare skriver in varje entry för varje rad
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
    
            /*char[][] map = {
                {'A', 'A', 'C', 'A', 'C', 'D'},
                {'A', 'B', 'B', 'A', 'B', 'D'},
                {'A', 'B', 'A', 'A', 'A', 'D'},
                {'A', 'B', 'A', 'B', 'A', 'D'},
                {'A', 'A', 'A', 'B', 'A', 'D'},
                {'B', 'B', 'B', 'B', 'B', 'D'}
            };*/
    
            /*char[][] map = {
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
                {'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K', 'K'}
            };*/
            
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

                    if (row >= 0 && row <= m && column >= 0 && column <= n && !visited_letters[row][column]) {
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

                    // characters.add(map[row][column]);

                    /* 
                    // inte tillåtna värden eller om värdet redan är med, gå då vidare till nästa iteration.
                    if (row < 0 || row >= m || column < 0 || column >= n || characters.contains(map[row][column])) {
                        continue;
                    }
                    */
                    
                    // visited_letters[row][column] = true;
                }
            }
        }
    
        Character[] sortedChars = characters.toArray(new Character[0]);
        Arrays.sort(sortedChars);
    
        for (Character c : sortedChars) {
            paths.append(c);
        }
    
        
    }

    return paths.length() == 0 ? "0" : paths.toString();
    
    
}

}
