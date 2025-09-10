import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        
        int[] arr = new int[n+1];
        
        for(int r : reserve){
            arr[r]++;
        }

        for(int l : lost){
            arr[l]--;
        }
        
        for(int l=1; l<=n; l++){
            if(arr[l] == -1){
                if(arr[l-1] == 1){
                    arr[l-1]--;
                    arr[l]++;
                }else if(l+1<=n && arr[l+1] == 1){
                    arr[l+1]--;
                    arr[l]++;
                }else{
                    answer--;
                }
            }
        }
        
        return answer;
    }
}