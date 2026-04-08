import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int count = 0;
        int jobsIdx = 0;
        int currentTime = 0;

        int[][] sortedJobs = new int[jobs.length][3];
        for (int i = 0; i < jobs.length; i++) {
            sortedJobs[i][0] = jobs[i][0]; // 요청 시점
            sortedJobs[i][1] = jobs[i][1]; // 소요 시간
            sortedJobs[i][2] = i;          // 작업 번호
        }
        
        Arrays.sort(sortedJobs, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[2] - o2[2];
            return o1[0] - o2[0];
        });

        // 정렬 기준: 소요 시간 -> 요청 시점 -> 작업 번호
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            return o1[2] - o2[2];
        });

        while (count < jobs.length) {
            while (jobsIdx < jobs.length && sortedJobs[jobsIdx][0] <= currentTime) {
                pq.add(sortedJobs[jobsIdx++]);
            }

            if (pq.isEmpty()) {
                currentTime = sortedJobs[jobsIdx][0];
            } else {
                int[] job = pq.poll();
                answer += (currentTime + job[1]) - job[0];
                currentTime += job[1];
                count++;
            }
        }

        return answer / jobs.length;
    }
}