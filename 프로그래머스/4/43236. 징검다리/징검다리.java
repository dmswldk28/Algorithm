import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        int[] points = new int[rocks.length + 1];
        System.arraycopy(rocks, 0, points, 0, rocks.length);
        points[points.length - 1] = distance;

        int left = 1, right = distance, answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int removed = 0;
            int prev = 0;

            for (int p : points) {
                if (p - prev < mid) {
                    removed++;
                    if (removed > n) break;
                } else {
                    prev = p;
                }
            }

            if (removed > n) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }
}