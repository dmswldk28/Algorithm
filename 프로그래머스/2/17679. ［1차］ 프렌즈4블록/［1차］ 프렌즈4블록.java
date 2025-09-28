import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        char[][] grid = new char[m][n];
        for(int i=0; i<m; i++){
            grid[i] = board[i].toCharArray();
        }
        
        while(true){
            boolean[][] toRemove = new boolean[m][n];
            int cnt = 0;
            
            for(int i=0; i<m-1; i++){
                for(int j=0; j<n-1; j++){
                    char block = grid[i][j];
                    
                    if(block == '0') continue;
                    
                    if(block == grid[i][j+1] &&
                       block == grid[i+1][j] &&
                       block == grid[i+1][j+1]){
                        toRemove[i][j] = true;
                        toRemove[i][j+1] = true;
                        toRemove[i+1][j] = true;
                        toRemove[i+1][j+1] = true;
                    }
                }
            }
            
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(toRemove[i][j]){
                        if(grid[i][j] != '0'){
                            cnt++;
                            grid[i][j] = '0';
                        }
                    }
                }
            }
            
            if(cnt == 0) break;
            
            answer += cnt;
            
            for(int j=0; j<m; j++){
                for(int i=m-1; i>=0; i--){
                    if(grid[i][j] == '0'){
                        for(int k = i-1; k>=0; k--){
                            if(grid[k][j] != '0'){
                                grid[i][j] = grid[k][j];
                                grid[k][j] = '0';
                                break;
                            }
                        }
                    }
                }
            }
            
        }

        return answer;
    }
}