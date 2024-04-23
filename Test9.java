import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test9 {
    static char[][] map;
    static int M;   
    static int N;
    static boolean[][] visited; 

    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        // Testa med bufferedReader
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] MN;
        try {
            MN = reader.readLine().split(" ");
            M = Integer.parseInt(MN[0]);
            N = Integer.parseInt(MN[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        M = scanObj.nextInt();
        N = scanObj.nextInt();
        scanObj.nextLine();

        map = new char[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String line = scanObj.nextLine();
            // String line = reader.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        
        String result = hittaStigar(map, M, N);
        System.out.println(result);
    }

    private static String hittaStigar(char[][] map, int m, int n) {
        StringBuilder paths = new StringBuilder(); 

        for (int j = 0; j < n; j++) {
            char ch = map[0][j];    
            if (dfs(map, m, n, 0, j, ch)) {
                paths.append(ch); 
            }
        }

        if (paths.length() == 0) {
            return "0";
        } else {
            char[] sortedChars = paths.toString().toCharArray();
            Arrays.sort(sortedChars);
            return new String(sortedChars); 
        }
    }

    private static boolean dfs(char[][] map, int m, int n, int startRow, int startCol, char targetChar) {
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{startRow, startCol});

        while (!stack.isEmpty()) {
            int[] position = stack.pop();
            int r = position[0];
            int c = position[1];

            if (r == m - 1) {
                return true;
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

        return false;
    }
}
