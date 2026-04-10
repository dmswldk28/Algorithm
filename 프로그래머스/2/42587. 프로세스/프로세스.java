import java.util.*;

class Solution {
    static class Process {
        int index;
        int priority;

        public Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    public int solution(int[] priorities, int location) {
        Queue<Process> queue = new LinkedList<>();
        
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Process(i, priorities[i]));
        }

        Arrays.sort(priorities);
        int length = priorities.length;
        int maxIdx = length - 1;
        
        int executeCount = 0;

        while (!queue.isEmpty()) {
            Process current = queue.poll();

            if (current.priority == priorities[maxIdx]) {
                executeCount++;
                maxIdx--;
                
                if (current.index == location) {
                    return executeCount;
                }
            } else {
                queue.add(current);
            }
        }

        return executeCount;
    }
}