import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        //맵에 넣기
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        //맵에서 빼기
        for (String c : completion) {
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }
        
        return map.keySet().iterator().next(); 
    }
}