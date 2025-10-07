class Solution {
    public String solution(String m, String[] musicinfos) {
        // 1. 네오의 멜로디를 치환하여 단순화
        String targetMelody = replaceSharpNotes(m);

        // 결과 저장용 변수: [재생 시간 (분), 입력 순서, 제목]
        // 재생 시간이 가장 길고, 입력 순서가 빠른 곡을 저장
        String[] bestMatch = {"-1", "101", "(None)"}; // [재생시간, 입력순서, 제목]

        for (int i = 0; i < musicinfos.length; i++) {
            String info = musicinfos[i];
            String[] parts = info.split(",");
            String startTimeStr = parts[0];
            String endTimeStr = parts[1];
            String title = parts[2];
            String sheet = parts[3];

            // 1. 악보 치환
            String replacedSheet = replaceSharpNotes(sheet);
            
            // 2. 재생 시간 계산 (분 단위)
            int playMinutes = calculatePlayTime(startTimeStr, endTimeStr);
            
            // 3. 실제 재생된 악보 생성
            String playedMelody = getPlayedMelody(replacedSheet, playMinutes);

            // 4. 멜로디 포함 여부 확인
            if (playedMelody.contains(targetMelody)) {
                int currentPlayTime = playMinutes;
                int bestPlayTime = Integer.parseInt(bestMatch[0]);
                int currentOrder = i + 1; // 입력 순서는 1부터 시작
                int bestOrder = Integer.parseInt(bestMatch[1]);

                // 5. 최적의 곡 선택
                // 조건: (1) 재생 시간이 더 길거나, 
                //      (2) 재생 시간이 같으면서 입력 순서가 더 빠를 경우
                if (currentPlayTime > bestPlayTime) {
                    bestMatch[0] = String.valueOf(currentPlayTime);
                    bestMatch[1] = String.valueOf(currentOrder);
                    bestMatch[2] = title;
                } else if (currentPlayTime == bestPlayTime && currentOrder < bestOrder) {
                    bestMatch[0] = String.valueOf(currentPlayTime);
                    bestMatch[1] = String.valueOf(currentOrder);
                    bestMatch[2] = title;
                }
            }
        }

        return bestMatch[2];
    }
    
    private String replaceSharpNotes(String melody) {
        return melody.replaceAll("C#", "H")
                     .replaceAll("D#", "I")
                     .replaceAll("F#", "J")
                     .replaceAll("G#", "K")
                     .replaceAll("A#", "L")
                     .replaceAll("B#", "M")
                     .replaceAll("E#", "N");
    }

    private int timeToMinutes(String timeStr) {
        String[] hm = timeStr.split(":");
        int hour = Integer.parseInt(hm[0]);
        int minute = Integer.parseInt(hm[1]);
        return hour * 60 + minute;
    }

    private int calculatePlayTime(String startTimeStr, String endTimeStr) {
        int startMinutes = timeToMinutes(startTimeStr);
        int endMinutes = timeToMinutes(endTimeStr);
        return endMinutes - startMinutes;
    }

    private String getPlayedMelody(String sheet, int playMinutes) {
        int sheetLength = sheet.length();
        
        if (playMinutes <= 0) {
            return ""; // 재생 시간이 0분 이하면 빈 문자열 반환
        }
        
        if (playMinutes <= sheetLength) {
            // 재생 시간이 짧거나 같으면 처음부터 재생 시간만큼만
            return sheet.substring(0, playMinutes);
        } else {
            // 재생 시간이 길면 반복 재생
            StringBuilder sb = new StringBuilder();
            // 반복 횟수
            int repeatCount = playMinutes / sheetLength;
            // 남은 길이
            int remainder = playMinutes % sheetLength;
            
            // 반복 부분 추가
            for (int i = 0; i < repeatCount; i++) {
                sb.append(sheet);
            }
            
            // 나머지 부분 추가
            sb.append(sheet.substring(0, remainder));
            
            return sb.toString();
        }
    }
}