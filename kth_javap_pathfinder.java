import java.util.Scanner;
import java.util.List;   // List är en collection
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class kth_javap_pathfinder {

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
        // Scanner scanObj = new Scanner(System.in);
        int M = 10;
        int N = 10;

        char[][] map = {
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
        };
        

        List<Character> result = hittaStigar(map, M, N);
        System.out.println(result);

    }

    public static List<Character> hittaStigar(char[][] map, int m, int n) {
        boolean[][] visited_letters = new boolean[m][n];

        Deque<int[]> q = new LinkedList<>();

        List<Character> paths = new ArrayList<>();

        for (int col = 0; col < n; col++) {
            if (!visited_letters[0][col]) {
                q.offerLast(new int[]{0, col});  // lägger sist i kön
                visited_letters[0][col] = true;

                while (!q.isEmpty()) {
                    int[] position = q.pollFirst();  // hämtar och tar bort första elementet i en deque
                    int row = position[0];
                    int column = position[1];


                    int[] directionsRow = {-1, 1, 0, 0};  // upp, ner, stanna, stanna
                    int[] directionsColumn = {0, 0, -1, 1};   // stanna, stanna, vänster, höger 

                    for (int i = 0; i < 4; i++) {
                        int newRow = row + directionsRow[i];
                        int newCol = column + directionsColumn[i];

        
                        if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited_letters[newRow][newCol] && map[newRow][newCol] == map[row][col]) {     // om både rad och kolumn är mellan 0-totalt antal rader/kolumner
                            if(!visited_letters[newRow][newCol] && map[newRow][newCol] == map[row][col]) {
                                q.offerLast(new int[]{newRow, newCol});  // Lägg till grannposition i kön
                                visited_letters[newRow][newCol] = true;

                                // Om grannpositionen är i den sista raden, lägg till tecknet i stigen
                                if (newRow == m - 1) {
                                    paths.add(map[newRow][newCol]);
                                }
                            }

                            
                        }





                    }

                }

            }

        }
        // kolla upp, ska inte vara en lista, utan sträng
        if (paths.isEmpty()) {
            paths.add('0');
        } else {
            Collections.sort(paths);
        }
        
        return paths;

    }

}
