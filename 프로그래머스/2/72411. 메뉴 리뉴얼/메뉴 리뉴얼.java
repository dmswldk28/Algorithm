import java.util.*;

class Solution {
    private Map<String, Integer> courseMap;

    public String[] solution(String[] orders, int[] course) {
        String[] sortedOrders = new String[orders.length];
        for (int i = 0; i < orders.length; i++) {
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            sortedOrders[i] = new String(chars);
        }

        List<String> result = new ArrayList<>();

        for (int k : course) {
            courseMap = new HashMap<>();
            
            for (String order : sortedOrders) {
                if (order.length() >= k) {
                    combination(order, k, 0, new StringBuilder());
                }
            }

            if (!courseMap.isEmpty()) {
                int maxFrequency = 0;
                for (int count : courseMap.values()) {
                    maxFrequency = Math.max(maxFrequency, count);
                }

                if (maxFrequency >= 2) {
                    for (Map.Entry<String, Integer> entry : courseMap.entrySet()) {
                        if (entry.getValue() == maxFrequency) {
                            result.add(entry.getKey());
                        }
                    }
                }
            }
        }

        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    private void combination(String order, int k, int start, StringBuilder current) {
        if (current.length() == k) {
            String menu = current.toString();
            courseMap.put(menu, courseMap.getOrDefault(menu, 0) + 1);
            return;
        }

        for (int i = start; i < order.length(); i++) {
            current.append(order.charAt(i));
            
            combination(order, k, i + 1, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
}