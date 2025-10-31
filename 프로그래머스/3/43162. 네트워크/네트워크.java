class Solution {
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for(int i=0; i<n; i++){
            if(!visited[i]){
                answer++;
                visited[i] = true;
                dfs(i, n, computers);
            }
        }
        
        return answer;
    }
    
    static void dfs(int k, int n, int[][] computers){
        for(int i=0; i<n; i++){
            if(!visited[i] && computers[k][i] == 1){
                visited[i] = true;
                dfs(i, n, computers);
            }
        }
    }
}