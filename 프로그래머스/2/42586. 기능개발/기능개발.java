import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            int remaining = 100 - progresses[i];
            int days = (remaining + speeds[i] - 1) / speeds[i];
            q.add(days);
        }

        while (!q.isEmpty()) {
            int curr = q.poll();
            int count = 1;

            while (!q.isEmpty() && q.peek() <= curr) {
                q.poll();
                count++;
            }

            answer.add(count);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}