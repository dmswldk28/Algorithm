class Solution {
    /**
     * 조이스틱을 사용하여 name을 완성하는 최소 조작 횟수를 계산합니다.
     * @param name 완성해야 할 알파벳 이름
     * @return 최소 조작 횟수
     */
    public int solution(String name) {
        int N = name.length();
        int total_change = 0; // 알파벳 상하 변경 조작 횟수의 합
        int min_move = N - 1; // 커서 좌우 이동 조작 횟수의 최솟값 (초기값: 오른쪽으로 끝까지 이동)

        for (int i = 0; i < N; i++) {
            char current = name.charAt(i);

            // 1. 알파벳 변경 횟수 계산 (상하 조작)
            // 'A' -> char로 이동하는 최소 횟수: min(위로 이동, 아래로 이동)
            int change_up = current - 'A';
            int change_down = 'Z' - current + 1;
            total_change += Math.min(change_up, change_down);

            // 2. 커서 이동 횟수 계산을 위한 'A'가 아닌 다음 문자 인덱스 찾기
            int next_idx = i + 1;
            while (next_idx < N && name.charAt(next_idx) == 'A') {
                next_idx++;
            }

            // 3. 왕복 이동 경로의 최소값 계산 및 갱신 (좌우 조작)
            // 현재까지 오른쪽으로 이동한 횟수: i
            // 남은 문자열(next_idx부터 끝까지)을 왼쪽으로 이동하여 방문하는 횟수: N - next_idx

            // case 1: 오른쪽으로 갔다가 (i), 다시 돌아와 왼쪽으로 이동 ((N - next_idx)까지)
            // 이동 횟수 = i (오른쪽) + i (되돌아오기) + (N - next_idx) (왼쪽)
            int move1 = i * 2 + (N - next_idx);
            
            // case 2: 왼쪽으로 갔다가 (N - next_idx), 다시 돌아와 오른쪽으로 이동 (i까지)
            // 이동 횟수 = (N - next_idx) (왼쪽) + (N - next_idx) (되돌아오기) + i (오른쪽)
            int move2 = (N - next_idx) * 2 + i;
            
            // 현재까지의 최소 이동 횟수와 새로운 왕복 경로 중 더 작은 값을 선택
            min_move = Math.min(min_move, Math.min(move1, move2));
        }

        // 전체 최소 조작 횟수 = 최소 알파벳 변경 횟수 + 최소 커서 이동 횟수
        return total_change + min_move;
    }
}