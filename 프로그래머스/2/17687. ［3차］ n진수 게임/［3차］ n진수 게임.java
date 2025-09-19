class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder gameSequence = new StringBuilder();

        int requiredNumbers = t * m;
        for (int i = 0; gameSequence.length() < requiredNumbers; i++) {
            gameSequence.append(Integer.toString(i, n));
        }

        for (int i = 0; i < t; i++) {
            int tubeIndex = (i * m) + (p - 1);
            char tubeTurnNumber = gameSequence.charAt(tubeIndex);
            answer.append(Character.toUpperCase(tubeTurnNumber));
        }

        return answer.toString();
    }
}