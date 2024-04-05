import java.util.*;

public class Test6 {
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
    
        for (int j = 0; j < n; j++) {
            if (map[0][j] == map[m - 1][j]) {
                characters.add(map[0][j]);
            }
        }
    
        for (char ch : characters) {
            boolean pathValid = true;
            for (int j = 0; j < n; j++) {
                if (map[0][j] == ch && map[m - 1][j] == ch) {
                    explore(map, visited, ch, 0, j); 
                    if (!visited[m - 1][j]) { 
                        pathValid = false;
                        break;
                    }
                }
            }
            if (!pathValid) { 
                characters.remove(ch);
            }
        }
    
        if (characters.isEmpty()) {
            return "0";
        } else {
            List<Character> sortedChars = new ArrayList<>(characters);
            Collections.sort(sortedChars);
            StringBuilder paths = new StringBuilder();
            for (char c : sortedChars) {
                paths.append(c);
            }
            return paths.toString();
        }
    }
    
    private static void explore(char[][] map, boolean[][] visited, char targetChar, int row, int col) {
        Deque<int[]> q = new LinkedList<>();
        q.offerLast(new int[]{row, col}); 
    
        while (!q.isEmpty()) {
            int[] position = q.pollFirst();
            int r = position[0];
            int c = position[1];
    
            visited[r][c] = true;
    
            int[] dr = {0, 0, -1, 1};
            int[] dc = {1, -1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int newRow = r + dr[i];
                int newCol = c + dc[i];
    
                if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length
                        && map[newRow][newCol] == targetChar && !visited[newRow][newCol]) {
                    q.offerLast(new int[]{newRow, newCol});
                }
            }
        }
    }
    
    
}
