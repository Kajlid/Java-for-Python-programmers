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

        for (int i = 0; i < m; i++) {
            explore(map, visited, characters, i, 0);
            explore(map, visited, characters, i, n - 1);
        }

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
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length || visited[row][col])
            return;

        char currentChar = map[row][col];
        visited[row][col] = true;
        characters.add(currentChar);

        for (int eachRow = 0; eachRow < m; eachRow++) {   
            // if (!visited_letters[0][col])
            if (map[eachRow][0] == map[eachRow][n-1]) { // kolla om högst upp = längst ned (i samma kolumn)
                q.offerLast(new int[]{eachRow, 0});  // lägger sist i kön, som att lägga på en stack
                visited[eachRow][0] = true;  // den är besökt

            while (!q.isEmpty()) {               // så länge kön inte är tom.
                int[] position = q.pollFirst();  // hämtar och tar bort första elementet i stacken
                int row = position[0];
                int column = position[1];

                int[] dr = {0, 0, -1, 1};
                int[] dc = {1, -1, 0, 0};
                for (int i = 0; i < 4; i++) {
                    int newRow = row + dr[i];
                    int newCol = col + dc[i];
                    if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length && map[newRow][newCol] == currentChar) {
                        explore(map, visited, characters, newRow, newCol); }
                }
            }

        }

    }

}

}

