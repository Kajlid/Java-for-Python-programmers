import java.util.Scanner;
import java.util.Set;
import java.util.List;   // List är en collection
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
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

    // public static List<Character> hittaStigar(char[][] map, int m, int n)
    public static String hittaStigar(char[][] map, int m, int n) {
        boolean[][] visited_letters = new boolean[m][n];    // bool matrix

        Deque<int[]> q = new LinkedList<>();

        // String paths = "";
        StringBuilder paths = new StringBuilder();   // test med stringBuilder

        Set<Character> characters = new HashSet<>();   // chars i alla riktningar att leta i, test (current)

        for (int eachRow = 0; eachRow < m; eachRow++) {   
            // if (!visited_letters[0][col])
            if (map[eachRow][0] == map[eachRow][n-1]) { // kolla om högst upp = längst ned (i samma kolumn)
                q.offerLast(new int[]{eachRow, 0});  // lägger sist i kön, som att lägga på en stack
                visited_letters[eachRow][0] = true;  // den är besökt

                while (!q.isEmpty()) {               // så länge kön inte är tom.
                    int[] position = q.pollFirst();  // hämtar och tar bort första elementet i stacken
                    int row = position[0];
                    int column = position[1];

                    // Kolla om tecknet vi är på nu är utanför gränserna eller redan är besökt, hoppa då över resten av iterationen.
                    if (row < 0 || row >= m || column < 0 || column >= n || characters.contains(map[row][column])) {
                        continue;
                    }

                    characters.add(map[row][column]);

                    q.offerLast(new int[]{row - 1, column}); // Ner
                    q.offerLast(new int[]{row + 1, column}); // Upp
                    q.offerLast(new int[]{row, column - 1}); // höger (?)
                    q.offerLast(new int[]{row, column + 1}); // vänster (?)


                    // int[] directionsRow = {-1, 1, 0, 0};  // upp, ner, stanna, stanna
                    // int[] directionsColumn = {0, 0, -1, 1};   // stanna, stanna, vänster, höger 

                }

            }

            else {
                visited_letters[eachRow][0] = true;  // den är besökt
            }


        }

        /*for (int eachCol = 0; eachCol < n; eachCol++) {
            if (map[0][eachCol] == map[m-1][eachCol]) { // kolla om högst upp = längst ned (i samma kolumn)
                q.offerLast(new int[]{0, eachCol});  // lägger sist i kön, som att lägga på en stack
                visited_letters[0][eachCol] = true;  // den är besökt

                while (!q.isEmpty()) {
                    int[] position = q.pollFirst();  // hämtar och tar bort första elementet i stacken
                    int row = position[0];
                    int column = position[1];

                    // Kolla om tecknet vi är på nu är utanför gränserna eller redan är besökt, hoppa då över resten av iterationen.
                    if (row < 0 || row >= m || column < 0 || column >= n || characters.contains(map[row][column])) {
                        continue;
                    }

                    characters.add(map[row][column]);

                    q.offerLast(new int[]{row - 1, column}); // Ner
                    q.offerLast(new int[]{row + 1, column}); // Upp
                    q.offerLast(new int[]{row, column - 1}); // höger (?)
                    q.offerLast(new int[]{row, column + 1}); // vänster (?)


                    // int[] directionsRow = {-1, 1, 0, 0};  // upp, ner, stanna, stanna
                    // int[] directionsColumn = {0, 0, -1, 1};   // stanna, stanna, vänster, höger 

                }

            }

        }*/

        Character[] sortedChars = characters.toArray(new Character[0]);
        Arrays.sort(sortedChars);

        for (Character c : sortedChars) {
            paths.append(c);
        }

        /*if (!paths.isEmpty()) {
            char[] chars = paths.toCharArray();
            Arrays.sort(chars);
            paths = new String(chars);
        } else {
            paths = "0";
        }*/
        

        // return paths.isEmpty() ? "0" : Arrays.sort(paths.toCharArray()).toString();
        return paths.length() == 0 ? "0" : paths.toString();

    }

}

