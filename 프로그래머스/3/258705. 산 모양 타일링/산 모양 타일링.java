import java.util.*;

class Solution {
    static final int MOD = 10007;

    public int solution(int n, int[] tops) {
        // a: i번째 칸까지 빈틈 없이 채운 경우의 수
        // b: i번째 칸까지 채웠을 때 다음 칸으로 이어지는 오버행 1개가 남아있는 경우의 수
        int a = 1; // 아무것도 채우지 않은 시작 상태(0칸 처리) = 1가지
        int b = 0;

        for (int i = 0; i < n; i++) {
            int na, nb;
            if (tops[i] == 0) {
                // a' = 2*a + 1*b
                // b' = 1*a + 1*b
                na = ( (2 * a) % MOD + b ) % MOD;
                nb = ( a + b ) % MOD;
            } else {
                // a' = 3*a + 2*b
                // b' = 1*a + 1*b
                na = ( (3 * a) % MOD + (2 * b) % MOD ) % MOD;
                nb = ( a + b ) % MOD;
            }
            a = na;
            b = nb;
        }

        return (a + b) % MOD;
    }
}
