import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> routes = new HashMap<>();
    List<String> result = new LinkedList<>();

    public String[] solution(String[][] tickets) {
        for (String[] ticket : tickets) {
            routes.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }

        dfs("ICN");

        return result.toArray(new String[0]);
    }

    private void dfs(String departure) {
        PriorityQueue<String> arrivals = routes.get(departure);
        
        while (arrivals != null && !arrivals.isEmpty()) {
            dfs(arrivals.poll());
        }
        
        result.add(0, departure);
    }
}