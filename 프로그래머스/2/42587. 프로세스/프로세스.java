import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int l = location;

        Queue<Integer> que = new LinkedList<>();
        for (int p : priorities) {
            que.add(p);
        }

        Arrays.sort(priorities);
        int size = priorities.length - 1;

        while (!que.isEmpty()) {
            int currentP = que.poll();

            if (currentP == priorities[size - answer]) {
                answer++;
                
                if (l == 0) {
                    break;
                }
                l--;
            } else {
                que.add(currentP);
                
                l = (l == 0) ? que.size() - 1 : l - 1;
            }
        }

        return answer;
    }
}