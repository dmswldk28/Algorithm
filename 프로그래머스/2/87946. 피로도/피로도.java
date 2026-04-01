class Solution {
    private int cnt = 0;
    private boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        
        dfs(0, k, dungeons);
        
        return cnt;
    }

    private void dfs(int d, int f, int[][] dungeons) {
        cnt = Math.max(cnt, d) ;

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && f >= dungeons[i][0]) {
                visited[i] = true;
                
                dfs(d + 1, f - dungeons[i][1], dungeons);
                
                visited[i] = false;
            }
        }
    }
}