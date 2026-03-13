import java.util.*;

class Solution {
    // 이동할 네 방향 (상, 하, 좌, 우)
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[]{0, 0});
        
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1) {
                    maps[nx][ny] = maps[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        int answer = maps[n - 1][m - 1];
        
        return answer > 1 ? answer : -1;
    }
}