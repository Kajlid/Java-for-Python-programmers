import java.util.*;

public class test5 {

    public static void main (String[] args) {
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

    public static String hittaStigar(char[][] map, int m, int n) {
        boolean[][] visited = new boolean[m][n];
        Set<Character> characters = new HashSet<>();

        // För varje kolumn på översta raden
        for (int j = 0; j < n; j++) {
            if (map[0][j] == map[m - 1][j]) {
                explore(map, visited, characters, 0, j); 
            }
        }

            /*if (map[0][j] == map[m - 1][j]) {
                characters.add(map[0][j]);
            }*/
        

        if (characters.isEmpty())
            return "0";

        List<Character> sortedChars = new ArrayList<>(characters);
        Collections.sort(sortedChars);
        StringBuilder paths = new StringBuilder();
        for (char c : sortedChars) {
            paths.append(c);
        }
        return paths.toString();
    }

    private static void explore(char[][] map, boolean[][] visited, Set<Character> characters, int row, int col) {
        Deque<int[]> q = new LinkedList<>();
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length || visited[row][col]) {
            return;
        }
    
        char startChar = map[row][col]; // Store the starting character
        visited[row][col] = true;
        characters.add(startChar);
        q.offerLast(new int[]{row, col}); // Add the initial cell to the queue
    
        boolean pathValid = true; // Flag to track if the path is valid
    
        while (!q.isEmpty()) {
            int[] position = q.pollFirst();
            int r = position[0];
            int c = position[1];
    
            int[] dr = {0, 0, -1, 1};
            int[] dc = {1, -1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int newRow = r + dr[i];
                int newCol = c + dc[i];
                if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length && !visited[newRow][newCol]) {
                    char currentChar = map[newRow][newCol];
                    if (currentChar == startChar) {
                        visited[newRow][newCol] = true;
                        q.offerLast(new int[]{newRow, newCol});
                    } else {
                        // If the character differs from the starting character, mark the path as invalid
                        pathValid = false;
                    }
                }
            }
        }
    
        // If the path is not valid, remove the starting character from the characters set
        if (!pathValid) {
            characters.remove(startChar);
        }
    }
    

    /*private static void explore(char[][] map, boolean[][] visited, Set<Character> characters, int row, int col) {
        Deque<int[]> q = new LinkedList<>();
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length || visited[row][col]){
            return;}

        char currentChar = map[row][col];
        char startChar = map[0][col]; // Store the starting character
    
        if (currentChar != startChar) {
            return; 
        }

        visited[row][col] = true;
        characters.add(startChar);
        // characters.add(currentChar);
        q.offerLast(new int[]{row, col}); // Lägg till första/initala cellen i kön

        boolean pathValid = true;

        /*if (!visited[0][col] && map[0][col] == map[row][col]) { // kolla om högst upp = längst ned (i samma kolumn)
                q.offerLast(new int[]{0, col});  // lägger sist i kön, som att lägga på en stack
                visited[0][col] = true;  // den är besökt

        while (!q.isEmpty()) {               // så länge kön inte är tom.
            int[] position = q.pollFirst();  // hämtar och tar bort första elementet i stacken
            int r = position[0];
            int c = position[1];

            int[] dr = {0, 0, -1, 1};
            int[] dc = {1, -1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int newRow = r + dr[i];
                int newCol = c + dc[i];
                if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length && map[newRow][newCol] == currentChar) {
                    explore(map, visited, characters, newRow, newCol);
                }
            }
        
        
    }

} */

}

