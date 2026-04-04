import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[][] map = new int[101][101];

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2;
            int x2 = rect[2] * 2, y2 = rect[3] * 2;

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (map[i][j] == 2) continue;
                    
                    if (i > x1 && i < x2 && j > y1 && j < y2) {
                        map[i][j] = 2;
                    } else {
                        map[i][j] = 1;
                    }
                }
            }
        }

        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }

    private int bfs(int startX, int startY, int targetX, int targetY) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0});
        boolean[][] visited = new boolean[101][101];
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int dist = curr[2];

            if (x == targetX && y == targetY) {
                return dist / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx <= 100 && ny >= 0 && ny <= 100) {
                    if (map[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }
        return 0;
    }
}