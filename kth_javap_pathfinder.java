import java.util.*;

/* 
 Labb 5:
Kartan består av en matris med M rader och N kolumner, innehållandes bokstäverna A-Z. 
Programmet ska skriva ut alla bokstäver (i bokstavsordning) som förbinder kartans översta och understa rad med varandra via en eller flera stigar. 
Två rutor i kartan anses tillhöra samma stig om de ligger antingen direkt ovanför/under, eller bredvid varandra. Inga diagonaler räknas alltså.

Om det inte existerar några stigar alls, ska programmet skriva ut "0" (en nolla).

Indata: 
    I indata anges först två heltal, som anger antalet rader (M), resp kolumner (N), i den ordningen. 
    På raden därefter följer själva kartan, som M rader med N tecken i varje rad.

Utdata:
    En sträng av bokstäver i alfabetisk ordning, eller "0".

 */

public class Kth_javap_pathfinder {
    // Fält som tillhör hela klassen, static' (tillhör klassen själv), går snabbare - slipper definera flera gånger
    static char[][] map;
    static int M;   
    static int N;
    static boolean[][] visited;  // BitSet tydligen snabbare och bättre än boolean?

    public static void main(String[] args) {
        // OBS Scanner är långsamt och kan orsaka Runtime Error i Kattis. Byt ut mot BufferedReader vid problem.
        Scanner scanObj = new Scanner(System.in); 
        M = scanObj.nextInt();
        N = scanObj.nextInt();
        scanObj.nextLine();

        map = new char[M][N];
        visited = new boolean[M][N];

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
        Set<Character> characters = new HashSet<>();
        Set<Character> bottomRow = new HashSet<>();   // sista raden för att göra en första kontroll (sparar tid)
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                // Om radindex är lika med sista index, lägg in tecknet i setet.
                if (i == m - 1) {
                    bottomRow.add(map[i][j]);
                }
            }
            char ch = map[0][j];    // karaktär på första raden, kolumn j
            Deque<int[]> stack = new ArrayDeque<>();
            
            if (bottomRow.contains(ch) &&   // gör en liten dummy-koll först om sista raden innehåller karaktären
                !characters.contains(ch) &&
                dfs(map, m, n, 0, j, ch, stack)){
                    characters.add(ch); 
            }     
        }


        if (characters.isEmpty()) {
            return "0";
        } else {
            List<Character> sortedChars = new ArrayList<>(characters);
            Collections.sort(sortedChars);
            // StringBuilder is a mutable string format.
            StringBuilder paths = new StringBuilder();
            for (char c : sortedChars) {
                paths.append(c);
            }
            return paths.toString();
        }
    }

    private static boolean dfs(char[][] map, int m, int n, int startRow, int startCol, char targetChar, Deque<int[]> stack) {
        // djupet-först sökning
        stack.push(new int[]{startRow, startCol});
    
        while (!stack.isEmpty()) {
            int[] position = stack.pop();
            int r = position[0];
            int c = position[1];
    
            if (r == m - 1) {
                return true;
            }
            
            // om redan besökt, försök med nästa iteration.
            if (visited[r][c]) {
                continue; 
            }
    
            visited[r][c] = true;
    
            int[] dr = {0, 0, -1, 1};   // stilla, stilla, ner, upp
            int[] dc = {1, -1, 0, 0};   // höger, vänster, stilla, stilla
            for (int i = 0; i < dc.length; i++) {
                int newRow = r + dr[i];
                int newCol = c + dc[i];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                        && map[newRow][newCol] == targetChar) {
                    stack.push(new int[]{newRow, newCol});
                }
            }
        }
    
        return false;
    }
    
}