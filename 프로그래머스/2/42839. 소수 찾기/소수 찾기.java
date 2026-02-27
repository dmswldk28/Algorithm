import java.util.HashSet;
import java.util.Iterator;

class Solution {
    HashSet<Integer> numberSet = new HashSet<>();

    public int solution(String numbers) {
        int count = 0;

        recursive("", numbers);

        Iterator<Integer> it = numberSet.iterator();
        while (it.hasNext()) {
            int number = it.next();
            if (isPrime(number)) {
                count++;
            }
        }

        return count;
    }

    public void recursive(String comb, String others) {
        if (!comb.equals("")) {
            numberSet.add(Integer.parseInt(comb));
        }

        for (int i = 0; i < others.length(); i++) {
            recursive(comb + others.charAt(i), 
                      others.substring(0, i) + others.substring(i + 1));
        }
    }

    public boolean isPrime(int num) {
        if (num < 2) return false;

        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}