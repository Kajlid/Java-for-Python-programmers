import java.util.*;

public class Test8 {
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

    public static String hittaStigar(char[][] map, int m, int n) {
        Set<Character> characters = new HashSet<>();
        boolean[][] visited = new boolean[m][n];

        for (int j = 0; j < n; j++) {
            if (map[0][j] == map[m - 1][j]) {
                char ch = map[0][j];
                if (dfs(map, m, n, visited, 0, j, ch)) {
                    characters.add(ch);
                }
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

    private static boolean dfs(char[][] map, int m, int n, boolean[][] visited, int startRow, int startCol, char targetChar) {
        // Deque<Integer> stack = new ArrayDeque<>();
        // stack.push(startRow * n + startCol);
        Deque<int[]> stack = new LinkedList<>();
        stack.offerLast(new int[]{m, n}); 

        while (!stack.isEmpty()) {
            int[] position = stack.pop();   // ta ut senaste
            // int r = position / n;
            // int c = position % n;
            int r = position[0];
            int c = position[1];

            if (r == m - 1) {     // om sista
                return true;
            }

            if (visited[r][c]) {      // om redan besökt
                continue; 
            }

            visited[r][c] = true;

            int[] dr = {0, 0, -1, 1};
            int[] dc = {1, -1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int newRow = r + dr[i];
                int newCol = c + dc[i];
                if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[newRow].length
                        && map[newRow][newCol] == targetChar) {
                            // stack.push(newRow * n + newCol
                            stack.offerLast(new int[]{newRow, newCol});
                }
            }
        }

        return false;
    }
}
