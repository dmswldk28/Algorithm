import java.util.*;

class Solution {
    static class Node {
        String word;
        int edge;

        Node(String word, int edge) {
            this.word = word;
            this.edge = edge;
        }
    }

    public int solution(String begin, String target, String[] words) {
        boolean hasTarget = false;
        for (String w : words) {
            if (w.equals(target)) {
                hasTarget = true;
                break;
            }
        }
        if (!hasTarget) return 0;

        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];

        queue.add(new Node(begin, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.word.equals(target)) {
                return current.edge;
            }

            for (int i = 0; i < words.length; i++) {
                if (visited[i] || !canConvert(current.word, words[i])) {
                    continue;
                }

                visited[i] = true;
                queue.add(new Node(words[i], current.edge + 1));
            }
        }

        return 0;
    }

    private boolean canConvert(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }
}