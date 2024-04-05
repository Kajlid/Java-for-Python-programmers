import java.util.*;

public class Test7 {
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

        for (int j = 0; j < n; j++) {
            if (map[0][j] == map[m - 1][j]) {
                char ch = map[0][j];
                if (pathExists(map, m, n, ch)) {
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

    private static boolean pathExists(char[][] map, int m, int n, char targetChar) {
        boolean[] visited = new boolean[m * n];
        Deque<Integer> q = new LinkedList<>();

        for (int j = 0; j < n; j++) {
            if (map[0][j] == targetChar) {
                q.offerLast(j);
                visited[j] = true;
            }
        }

        while (!q.isEmpty()) {
            int position = q.pollFirst();
            int r = position / n;
            int c = position % n;

            if (r == m - 1) {
                return true;
            }

            int[] dr = {0, 0, -1, 1};
            int[] dc = {1, -1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int newRow = r + dr[i];
                int newCol = c + dc[i];
                int newPos = newRow * n + newCol;
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                        && map[newRow][newCol] == targetChar && !visited[newPos]) {
                    q.offerLast(newPos);
                    visited[newPos] = true;
                }
            }
        }

        return false;
    }
}
