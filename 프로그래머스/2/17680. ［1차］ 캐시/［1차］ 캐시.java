import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if (cacheSize == 0) {
            return 5 * cities.length;
        }

        Map<String, String> cache = new LinkedHashMap<String, String>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > cacheSize;
            }
        };

        for (String city : cities) {
            city = city.toUpperCase();

            // cache hit
            if (cache.containsKey(city)) {
                cache.get(city);
                answer++;
            } else { // cache miss
                cache.put(city, city);
                answer += 5;
            }
        }
        return answer;
    }
}