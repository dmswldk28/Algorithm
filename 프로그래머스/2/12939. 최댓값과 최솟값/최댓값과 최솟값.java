import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        String[] arr = s.split(" ");
        
        for(int i=0; i<arr.length; i++){
            max = Math.max(max, Integer.parseInt(arr[i]));
            min = Math.min(min, Integer.parseInt(arr[i]));
        }
        
        sb.append(min);
        sb.append(" ");
        sb.append(max);
        
        return sb.toString();
    }
}