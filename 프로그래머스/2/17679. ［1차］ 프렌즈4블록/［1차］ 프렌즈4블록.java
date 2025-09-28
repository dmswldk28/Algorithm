import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            grid[i] = board[i].toCharArray();
        }

        int answer = 0;
        
        while (true) {
            boolean[][] toRemove = new boolean[m][n];
            int removedCount = 0;

            for (int r = 0; r < m - 1; r++) {
                for (int c = 0; c < n - 1; c++) {
                    char block = grid[r][c];
                    
                    if (block == '0') continue; 
                    
                    if (block == grid[r][c+1] && 
                        block == grid[r+1][c] && 
                        block == grid[r+1][c+1]) {
                        
                        toRemove[r][c] = true;
                        toRemove[r][c+1] = true;
                        toRemove[r+1][c] = true;
                        toRemove[r+1][c+1] = true;
                    }
                }
            }

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (toRemove[r][c]) {
                        if (grid[r][c] != '0') {
                            removedCount++;
                            grid[r][c] = '0';
                        }
                    }
                }
            }
            
            if (removedCount == 0) {
                break;
            }
            
            answer += removedCount;

            for (int c = 0; c < n; c++) {
                for (int r = m - 1; r >= 0; r--) {
                    if (grid[r][c] == '0') {
                        for (int k = r - 1; k >= 0; k--) {
                            if (grid[k][c] != '0') {
                                grid[r][c] = grid[k][c];
                                grid[k][c] = '0';
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