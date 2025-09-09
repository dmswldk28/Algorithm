class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;
        
        for (int[] size : sizes) {
            int w = size[0];
            int h = size[1];

            if (w > h) {
                maxWidth = Math.max(maxWidth, w);
                maxHeight = Math.max(maxHeight, h);
            } else {
                maxWidth = Math.max(maxWidth, h);
                maxHeight = Math.max(maxHeight, w);
            }

        }

        return maxWidth*maxHeight;
    }
}