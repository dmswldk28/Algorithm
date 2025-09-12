class Solution {
    static int cnt = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        
        return cnt;
    }
    
    static void dfs(int n, int idx, int[] numbers, int target){
        if(idx == numbers.length) {
            if(n == target) cnt++;
            return;
        }
        
        dfs(n+numbers[idx], idx+1, numbers, target);
        dfs(n-numbers[idx], idx+1, numbers, target);
    }
}