import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int initCnt = n / 3;
        int roundsMax = n / 3;

        int[] pos = new int[n + 1];
        for (int i = 0; i < n; i++) pos[cards[i]] = i;

        int lo = 0, hi = roundsMax;
        while (lo < hi) {
            int mid = (lo + hi + 1) >>> 1;
            if (canReach(mid, coin, pos, n, initCnt)) lo = mid;
            else hi = mid - 1;
        }

        return lo + 1;
    }

    private boolean canReach(int r, int coin, int[] pos, int n, int initCnt) {
        int spent = 0;
        int used0 = 0, used1 = 0, used2 = 0; // 지금까지 사용한 쌍 개수

        // c0(처음부터 공짜 쌍)는 k와 무관하므로 미리 계산해 둬도 됨
        int c0Always = 0;
        for (int a = 1; a <= n / 2; a++) {
            int b = (n + 1) - a;
            if (pos[a] < initCnt && pos[b] < initCnt) c0Always++;
        }

        for (int k = 1; k <= r; k++) {
            int end = initCnt + 2 * k - 1; // 이번 라운드까지 본 카드의 최종 인덱스

            int c0 = c0Always, c1 = 0, c2 = 0;
            for (int a = 1; a <= n / 2; a++) {
                int b = (n + 1) - a;

                boolean aInit = pos[a] < initCnt;
                boolean bInit = pos[b] < initCnt;

                boolean aSeen = aInit || pos[a] <= end;
                boolean bSeen = bInit || pos[b] <= end;
                if (!aSeen || !bSeen) continue;

                if (aInit && bInit) {
                    // 이미 c0Always에 반영
                } else if (aInit ^ bInit) {
                    c1++;
                } else {
                    c2++;
                }
            }

            int avail0 = c0 - used0;
            int avail1 = c1 - used1;
            int avail2 = c2 - used2;

            // 이번 라운드에서 1쌍 확보 (0 -> 1 -> 2코인 순서)
            if (avail0 > 0) {
                used0++;
            } else if (avail1 > 0 && spent + 1 <= coin) {
                used1++; spent += 1;
            } else if (avail2 > 0 && spent + 2 <= coin) {
                used2++; spent += 2;
            } else {
                return false; // 이번 라운드에서 한 쌍도 못 꺼내면 실패
            }
        }
        return true;
    }

}
