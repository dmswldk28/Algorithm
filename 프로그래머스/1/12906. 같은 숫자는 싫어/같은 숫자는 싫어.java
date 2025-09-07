import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer;
        
        Stack<Integer> s = new Stack<>();
        s.add(arr[0]);
        
        for(int i=1; i<arr.length; i++){
            int last = s.peek();
            
            if(last != arr[i]){
                s.add(arr[i]);
            }
        }
        
        answer = new int[s.size()];
        
        for(int i=s.size()-1; i>=0; i--){
            answer[i] = s.pop();
        }

        return answer;
    }
}