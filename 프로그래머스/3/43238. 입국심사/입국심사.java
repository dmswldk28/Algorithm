import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long low = 1;
        long high = (long)times[times.length - 1] * n;
        long answer = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long people_count = 0;

            for (int time : times) {
                people_count += mid / time;
                
                if (people_count >= n) {
                    break;
                }
            }

            if (people_count >= n) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }
}