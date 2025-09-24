import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dictionary = new HashMap<>();
        for(int i=0; i<26; i++){
            dictionary.put(String.valueOf((char)('A' + i)), i + 1);
        }
        
        List<Integer> result = new ArrayList<>();
        int nextIndex = 27;
        
        for (int i = 0; i < msg.length();) {
            String w = "";
            String wc = "";
            int k = 1;

            while (i + k <= msg.length()) {
                wc = msg.substring(i, i + k);
                if (dictionary.containsKey(wc)) {
                    w = wc;
                    k++;
                } else {
                    break;
                }
            }

            result.add(dictionary.get(w));

            if (i + k <= msg.length()) {
                dictionary.put(wc, nextIndex++);
            }

            i += w.length();
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}