import java.util.Arrays;
import java.util.Comparator;

class Solution {

    private String[] splitFileName(String fileName) {
        String head = "";
        String number = "";
        String tail = "";
        
        int n = fileName.length();
        int headEnd = -1;
        
        // 1. HEAD 찾기: 숫자가 아닌 문자열의 끝
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(fileName.charAt(i))) {
                headEnd = i;
                break;
            }
        }
        
        if (headEnd != -1) {
            head = fileName.substring(0, headEnd);
        } else {
            return new String[]{fileName, "", ""}; 
        }

        int numberEnd = headEnd;
        // 2. NUMBER 찾기: 첫 번째 숫자부터 시작하여 연속된 숫자, NUMBER는 최대 5글자
        for (int i = headEnd; i < n; i++) {
            if (Character.isDigit(fileName.charAt(i)) && (i - headEnd < 5)) {
                numberEnd = i + 1;
            } else {
                break;
            }
        }
        
        // NUMBER: HEAD 끝부터 NUMBER 끝까지
        if (numberEnd > headEnd) {
            number = fileName.substring(headEnd, numberEnd);
        }
        
        // 3. TAIL: NUMBER 끝부터 파일명 끝까지
        if (numberEnd < n) {
            tail = fileName.substring(numberEnd);
        }
        
        return new String[]{head, number, tail};
    }

    public String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String file1, String file2) {
                String[] parts1 = splitFileName(file1);
                String[] parts2 = splitFileName(file2);

                String head1 = parts1[0];
                String number1 = parts1[1];
                
                String head2 = parts2[0];
                String number2 = parts2[1];
                
                // 1. HEAD 부분 비교 (대소문자 무시)
                int headCompare = head1.toLowerCase().compareTo(head2.toLowerCase());
                
                if (headCompare != 0) {
                    return headCompare;
                }
                
                // 2. HEAD가 같으면 NUMBER 부분 비교 (숫자 값 비교)
                int num1 = Integer.parseInt(number1);
                int num2 = Integer.parseInt(number2);
                
                int numberCompare = num1 - num2;
                
                if (numberCompare != 0) {
                    return numberCompare;
                }
                
                // 3. HEAD와 NUMBER가 모두 같으면 원래 순서를 유지 (안정 정렬을 위해 0 반환)
                return 0;
            }
        });

        return files;
    }
}