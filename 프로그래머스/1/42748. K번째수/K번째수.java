import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int size = commands.length;
        int[] answer = new int[size];
        
        for(int i=0; i<size; i++){
            int tmpsize = commands[i][1] - commands[i][0] + 1;
            int[] tmp = new int[tmpsize];
            
            int idx = commands[i][0] - 1;
            
            for(int k = 0; k<tmpsize; k++){
                tmp[k] = array[idx++];
            }
            
            Arrays.sort(tmp);
            
            answer[i] = tmp[commands[i][2] - 1];
        }
        
        return answer;
    }
}