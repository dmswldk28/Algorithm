import java.util.*;

// 각각의 문자열을 규칙에 맞춰서(영문자로 된 글자 쌍만 유효) HashMap에 <단어, 등장횟수> 넣기
// 하나 맵 순회하면서 다른 맵에도 있으면
// 교집합 -> 등장횟수 더 적은걸로 ++ , 합집합 -> 등장횟수 더 많은걸로 ++ , 다른 맵에서 지워주기
// 다른 맵에 없으면 합집합에 추가하고 등장횟수 ++
// 한쪽 맵 다 돌았으면 나머지 맵 합집합에만 추가
// System.out.println();
class Solution {
    public int solution(String str1, String str2) {
        double pro = 0;
        double plus = 0;
        
        Map<String, Integer> map1 = createMap(str1);
        Map<String, Integer> map2 = createMap(str2);
        
        if (map1.isEmpty() && map2.isEmpty()) {
            return 65536;
        }
        
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(map2.containsKey(key)){
                if(value > map2.get(key)){
                    pro += map2.get(key);
                    plus += value;
                    map2.remove(key);
                }else{
                    plus += map2.get(key);
                    pro += value;
                    map2.remove(key);
                }
            }else{
                plus += value;
            }
            
        }
        
        for (String key : map2.keySet()) {
            Integer value = map2.get(key);
            plus += value;
        }

        System.out.println("pro = " + pro + ", plus = " + plus);
        
        return (int)((pro / plus) * 65536);
    }
    
    static Map<String, Integer> createMap(String str){
        Map<String, Integer> map = new HashMap<>();
        int length = str.length();
        
        for(int i=0; i<length-1; i++){
            char c1 = str.charAt(i);
            char c2 = str.charAt(i+1);
            
            if(Character.isLetter(c1) && Character.isLetter(c2)) {
                String s = ("" + c1 + c2).toLowerCase();
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        
        return map;
    }
}