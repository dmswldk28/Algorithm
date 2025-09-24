import java.util.*;
import java.util.stream.Collectors;

class Solution {
    
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
    
    private int calculateFee(int totalTime, int basicTime, int basicFee, int unitTime, int unitFee) {
        // 기본 시간 이하라면 기본 요금
        if (totalTime <= basicTime) return basicFee;
        
        // 초과 시간
        int overTime = totalTime - basicTime;
        
        // 초과 요금 계산 (올림 처리)
        // Math.ceil((double)overTime / unitTime) : 초과 단위 시간 수 (올림)
        // (int)Math.ceil(...) : 정수형으로 변환
        int unitCount = (int) Math.ceil((double) overTime / unitTime);
        
        return basicFee + unitCount * unitFee;
    }

    public int[] solution(int[] fees, String[] records) {
        // fees 배열 파싱
        int basicTime = fees[0];   // 기본 시간(분)
        int basicFee = fees[1];    // 기본 요금(원)
        int unitTime = fees[2];    // 단위 시간(분)
        int unitFee = fees[3];     // 단위 요금(원)
        
        // <차량 번호, 입차 시각(분)> : 현재 주차장에 있는 차량의 입차 시각을 저장
        Map<String, Integer> inTimeMap = new HashMap<>();
        
        // <차량 번호, 누적 주차 시간(분)> : 차량별 총 주차 시간을 저장
        Map<String, Integer> totalTimeMap = new HashMap<>();
        
        // 1. 입/출차 기록 처리
        for (String record : records) {
            String[] parts = record.split(" ");
            String time = parts[0];
            String carNumber = parts[1];
            String type = parts[2];
            
            int currentMinutes = timeToMinutes(time);
            
            if (type.equals("IN")) {
                inTimeMap.put(carNumber, currentMinutes);
            } else {
                int inMinutes = inTimeMap.get(carNumber);
                int parkedTime = currentMinutes - inMinutes;
                
                // 누적 주차 시간에 더함
                totalTimeMap.put(carNumber, totalTimeMap.getOrDefault(carNumber, 0) + parkedTime);
                
                // 주차장 Map에서 제거 (출차했으므로)
                inTimeMap.remove(carNumber);
            }
        }
        
        // 2. 미출차 차량 처리 (23:59, 즉 23*60 + 59 = 1439분)
        final int END_OF_DAY = 1439;
        
        for (Map.Entry<String, Integer> entry : inTimeMap.entrySet()) {
            String carNumber = entry.getKey();
            int inMinutes = entry.getValue();
            
            int parkedTime = END_OF_DAY - inMinutes;
            
            // 누적 주차 시간에 더함
            totalTimeMap.put(carNumber, totalTimeMap.getOrDefault(carNumber, 0) + parkedTime);
        }
        
        // 3. 차량 번호 오름차순으로 정렬
        // Map의 KeySet을 List로 변환 후 정렬
        List<String> sortedCarNumbers = totalTimeMap.keySet().stream()
                                            .sorted()
                                            .collect(Collectors.toList());
        
        // 4. 요금 계산
        int[] result = new int[sortedCarNumbers.size()];
        
        for (int i = 0; i < sortedCarNumbers.size(); i++) {
            String carNumber = sortedCarNumbers.get(i);
            int totalTime = totalTimeMap.get(carNumber);
            
            // 요금 계산 함수 호출
            result[i] = calculateFee(totalTime, basicTime, basicFee, unitTime, unitFee);
        }
        
        return result;
    }
}