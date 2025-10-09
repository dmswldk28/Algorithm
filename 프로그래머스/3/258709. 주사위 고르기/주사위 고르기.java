import java.util.*;

class Solution {
    public int[] solution(int[][] dice) {
        int n = dice.length;
        int half = n / 2;

        // 조합 생성용
        List<Integer> current = new ArrayList<>();
        Best best = new Best();

        // 모든 조합 탐색
        combine(0, n, half, current, dice, best);

        // 1-based 인덱스로 반환
        int[] ans = new int[best.pick.size()];
        for (int i = 0; i < best.pick.size(); i++) {
            ans[i] = best.pick.get(i) + 1;
        }
        return ans;
    }

    // 최적 결과 저장용
    static class Best {
        long wins = -1;
        List<Integer> pick = new ArrayList<>();
    }

    // 조합 생성 (사전식): idx부터 n-1 중 half개 선택
    private void combine(int idx, int n, int half, List<Integer> pick, int[][] dice, Best best) {
        if (pick.size() == half) {
            evaluate(pick, n, dice, best);
            return;
        }
        if (idx >= n) return;

        // 선택
        pick.add(idx);
        combine(idx + 1, n, half, pick, dice, best);
        pick.remove(pick.size() - 1);

        // 미선택
        combine(idx + 1, n, half, pick, dice, best);
    }

    // 현재 pick(=A의 주사위들)에 대해 승수 계산 후 갱신
    private void evaluate(List<Integer> pick, int n, int[][] dice, Best best) {
        // complement(B) 구하기
        boolean[] chosen = new boolean[n];
        for (int i : pick) chosen[i] = true;
        List<Integer> other = new ArrayList<>();
        for (int i = 0; i < n; i++) if (!chosen[i]) other.add(i);

        // 합 분포 생성
        int[] aSums = buildSums(pick, dice);
        int[] bSums = buildSums(other, dice);

        Arrays.sort(bSums);

        long winCount = 0L;
        for (int a : aSums) {
            // bSums에서 a보다 작은 개수 = lowerBound(bSums, a)
            int less = lowerBound(bSums, a);
            winCount += less;
        }

        if (winCount > best.wins) {
            best.wins = winCount;
            best.pick = new ArrayList<>(pick);
        }
    }

    // 선택된 주사위들의 "모든 가능한 합" 분포를 만듦
    private int[] buildSums(List<Integer> indices, int[][] dice) {
        // 시작은 합 0 하나
        List<Integer> sums = new ArrayList<>();
        sums.add(0);

        for (int idx : indices) {
            int[] faces = dice[idx];
            List<Integer> next = new ArrayList<>(sums.size() * 6);
            for (int s : sums) {
                for (int f = 0; f < 6; f++) {
                    next.add(s + faces[f]);
                }
            }
            sums = next;
        }

        // 리스트를 배열로 변환
        int[] arr = new int[sums.size()];
        for (int i = 0; i < sums.size(); i++) arr[i] = sums.get(i);
        return arr;
    }

    // lowerBound: 정렬된 arr에서 target 이상이 처음 나오는 위치(= target보다 작은 원소의 개수)
    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
