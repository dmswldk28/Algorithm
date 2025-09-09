import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if(cacheSize == 0){
            return 5 * cities.length;
        }
        
        // 오래될수록 배열 번호 1, 2, 3,,,
        List<String> list = new LinkedList<>();
        
        for(String city : cities){
            city = city.toUpperCase();
            
            if (list.contains(city)) {
                list.remove(city);
                answer++;
            } else {
                // 캐시 미스
                if (list.size() == cacheSize)  list.remove(0);
                answer += 5;
            }
            list.add(city);
        }
        
        return answer;
    }
}