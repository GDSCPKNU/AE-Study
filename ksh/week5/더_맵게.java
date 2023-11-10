package week5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 더_맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        // 가장 맵지 않은 음식 찾기
        // 두 번째로 맵지 않은 음식 찾기
        List<Integer> food = toList(scoville);

        int leastSpicyFood;
        int secondLeastSpicyFood;

        while (Collections.min(food) <= K) {
            Collections.sort(food);
            leastSpicyFood = food.remove(0);
            secondLeastSpicyFood = food.remove(0);

            int mixed = mix(leastSpicyFood, secondLeastSpicyFood);
            food.add(mixed);
            answer++;
        }

        return answer;
    }

    public List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for(int element : arr) {
            list.add(element);
        }
        return list;
    }

    public int mix(int leastSpicyFood, int secondLeastSpicyFood) {
        return leastSpicyFood + (secondLeastSpicyFood * 2);
    }
}
