package week5;

import java.util.PriorityQueue;

public class 더_맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> food = new PriorityQueue<>();

        for (int s : scoville) {
            food.offer(s);
        }

        while (food.peek() < K) {
            if (food.size() == 1) {
                return -1;
            }

            int leastSpicyFood = food.poll();
            int secondLeastSpicyFood = food.poll();

            int mixed = mix(leastSpicyFood, secondLeastSpicyFood);
            food.offer(mixed);
            answer++;
        }

        return answer;
    }

    public int mix(int leastSpicyFood, int secondLeastSpicyFood) {
        return leastSpicyFood + (secondLeastSpicyFood * 2);
    }
}
