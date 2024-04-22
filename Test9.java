import java.util.*;

public class Test9 {
    static char[][] map;
    static int M;
    static int N;
    static boolean[][] visited;

    public static void main(String[] args) {
        try (Scanner scanObj = new Scanner(System.in)) {
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
        }
        String result = hittaStigar(map, M, N);
        System.out.println(result);
    }

    private static String hittaStigar(char[][] map, int m, int n) {
        boolean[] characters = new boolean[128];   // testar med en bool array med storlek 128 istället för hashset

        for (int j = 0; j < n; j++) {
            char ch = map[0][j];
            if (!characters[ch]) {
                dfs(m, n, 0, j, ch);
                characters[ch] = true;
            }
        }

        StringBuilder paths = new StringBuilder();
        for (char c = 0; c < 128; c++) {
            if (characters[c]) {
                paths.append(c);
            }
        }

        return paths.toString();
    }

    private static void dfs(int m, int n, int startRow, int startCol, char targetChar) {
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{startRow, startCol});

        while (!stack.isEmpty()) {
            int[] position = stack.pop();
            int r = position[0];
            int c = position[1];

            if (r == m - 1) {
                return;
            }

            if (visited[r][c]) {
                continue;
            }

            visited[r][c] = true;

            int[] dr = {0, 0, -1, 1};
            int[] dc = {1, -1, 0, 0};
            for (int i = 0; i < dc.length; i++) {
                int newRow = r + dr[i];
                int newCol = c + dc[i];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                        && map[newRow][newCol] == targetChar) {
                    stack.push(new int[]{newRow, newCol});
                }
            }
        }
    }
}
