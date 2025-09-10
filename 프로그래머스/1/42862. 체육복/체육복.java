import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] arr = new int[n+1];
        Arrays.fill(arr, 1);
        
        for(int r : reserve){
            arr[r]++;
        }

        for(int l : lost){
            arr[l]--;
        }
        
        for(int l=1; l<=n; l++){
            if(arr[l] == 0){
                if(arr[l-1] == 2){
                    arr[l-1]--;
                    arr[l]++;
                }else if(l+1<=n && arr[l+1] == 2){
                    arr[l+1]--;
                    arr[l]++;
                }
            }
        }
        
        for(int i=1; i<=n; i++){
            if(arr[i] > 0) answer++;
        }
        
        return answer;
    }
}