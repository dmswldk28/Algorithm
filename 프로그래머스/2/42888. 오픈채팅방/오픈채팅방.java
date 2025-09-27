import java.util.*;
// System.out.println();

class Solution {
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        
        for (String rec : record) {
            String[] cmd = rec.split(" ");
            String action = cmd[0];
            String userId = cmd[1];
            
            if (action.equals("Enter") || action.equals("Change")) {
                String nickname = cmd[2];
                map.put(userId, nickname);
            }
        }
        
        for (String rec : record) {
            String[] cmd = rec.split(" ");
            String action = cmd[0];
            String userId = cmd[1];
            
            String nickname = map.get(userId);
            
            if (action.equals("Enter")) {
                answer.add(nickname + "님이 들어왔습니다.");
            } else if (action.equals("Leave")) {
                answer.add(nickname + "님이 나갔습니다.");
            }
        }
        
        return answer.toArray(new String[0]);
    }
}