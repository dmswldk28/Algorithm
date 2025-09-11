import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if(N == number) return 1;
        
        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
        
        // N을 i개 반복해서 만든 숫자를 dp[i]에 추가
        int n = 0;
        for (int i = 1; i <= 8; i++) {
            n = n * 10 + N;
            dp.get(i).add(n);
        }
        
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j < i; j++) {
                Set<Integer> set1 = dp.get(j);
                Set<Integer> set2 = dp.get(i - j);

                for (int num1 : set1) {
                    for (int num2 : set2) {
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num1 * num2);
                        if (num2 != 0) {
                            dp.get(i).add(num1 / num2);
                        }
                    }
                }
            }
            if (dp.get(i).contains(number)) {
                return i;
            }
        }

        return -1;
    }
}