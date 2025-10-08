import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(int[][] edges) {
        // key: 정점 번호, value: [inDegree, outDegree]
        Map<Integer, int[]> degreeMap = new HashMap<>();

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            degreeMap.computeIfAbsent(from, k -> new int[2])[1]++; // index 1: outDegree
            degreeMap.computeIfAbsent(to, k -> new int[2])[0]++;   // index 0: inDegree
        }

        int createdNode = -1;
        int donutCount = 0;
        int stickCount = 0;
        int eightCount = 0;

        for (Map.Entry<Integer, int[]> entry : degreeMap.entrySet()) {
            int node = entry.getKey();
            int inDegree = entry.getValue()[0];
            int outDegree = entry.getValue()[1];

            if (inDegree == 0 && outDegree >= 2) {
                createdNode = node;
                continue;
            }

            // 1) 막대 모양 그래프: outDegree가 0인 정점(그래프의 끝)이 존재.
            if (outDegree == 0) {
                stickCount++;
            }
            // 2) 8자 모양 그래프: inDegree >= 2 이고 outDegree >= 2인 정점(결합점)이 존재.
            else if (inDegree >= 2 && outDegree >= 2) {
                eightCount++;
            }
            // 3) 도넛 모양 그래프는 나머지이며, 총 그래프 수에서 막대와 8자의 수를 빼서 구한다.
        }

        int totalGraphs = degreeMap.get(createdNode)[1]; // 생성 정점의 outDegree
        donutCount = totalGraphs - stickCount - eightCount;

        return new int[]{createdNode, donutCount, stickCount, eightCount};
    }
}