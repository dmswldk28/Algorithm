class Solution {
    public int solution(int n, int k) {
        String kJinsu = Integer.toString(n, k);
        int cnt = 0;
        
        String[] parts = kJinsu.split("0+");
        
        for (String part : parts) {
            if (!part.isEmpty()) { // 빈 문자열은 스킵
                try {
                    long num = Long.parseLong(part);
                    if (isPrime(num)) {
                        cnt++;
                    }
                } catch (NumberFormatException e) {
                    // 예외 처리 (매우 큰 숫자인 경우)
                }
            }
        }
        
        return cnt;
    }
    
    private boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}