
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 연구소 {
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = 0;
        makeWallDfs(graph, N, M, 0);
        System.out.println(answer);

    }

    private static void makeWallDfs(int[][] graph, int N, int M, int wallAmount) {
        if (wallAmount == 3) {
            int[][] spreadGraph = spreadVirusDfs(graph, N, M);
            answer = Math.max(answer, countSafeArea(spreadGraph));
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0) {
                    graph[i][j] = 1;
                    makeWallDfs(graph, N, M, wallAmount + 1);
                    graph[i][j] = 0;
                }
            }
        }
    }

    private static int countSafeArea(int[][] graph) {
        int safeArea = 0;

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }

    private static int[][] spreadVirusDfs(int[][] graph, int N, int M) {
        boolean[][] visited = new boolean[N][M];
        int[][] spreadGraph = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                spreadGraph[i][j] = graph[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && spreadGraph[i][j] == 2) {
                    virusBfs(spreadGraph, visited, i, j);
                }
            }
        }
        return spreadGraph;
    }

    private static void virusBfs(int[][] graph, boolean[][] visited, int x, int y) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};
        Deque<int[]> dq = new LinkedList<>();
        dq.addLast(new int[]{x, y});

        while (!dq.isEmpty()) {
            int[] nowPoints = dq.removeLast();
            int nowX = nowPoints[0];
            int nowY = nowPoints[1];

            visited[nowX][nowY] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if (isInRange(graph, visited, nextX, nextY)) {
                    if (!visited[nextX][nextY] && graph[nextX][nextY] == 0) {
                        graph[nextX][nextY] = 2;
                        dq.addLast(new int[]{nextX, nextY});
                    }
                }
            }
        }
    }

    private static boolean isInRange(int[][] graph, boolean[][] visited, int nx, int ny) {
        return nx >= 0 && nx < graph.length && ny >= 0 && ny < graph[0].length && !visited[nx][ny] && graph[nx][ny] == 0;
    }
}