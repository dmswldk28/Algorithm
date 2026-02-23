import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(nums, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        if (nums[0].equals("0")) {
            return "0";
        }

        StringBuilder answer = new StringBuilder();
        for (String s : nums) {
            answer.append(s);
        }

        return answer.toString();
    }
}