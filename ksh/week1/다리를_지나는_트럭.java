package week1;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

class 다리를_지나는_트럭 {
    public static class Truck{
        int weight;
        int location;

        Truck(int weight, int location){
            this.weight = weight;
            this.location = location;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> bridge = new LinkedList<>();

        Stack<Truck> waitingTrucks = new Stack<>();

        for(int i = truck_weights.length-1; i >= 0; i--){
            waitingTrucks.push(new Truck(truck_weights[i], 0));
        }

        int answer = 0;
        int currentWeightOnBridge = 0; // 현재 다리가 견디고 있는 무게

        while(!waitingTrucks.isEmpty() || !bridge.isEmpty()){
            answer++; // 반복마다 시간 경과

            if(!bridge.isEmpty()) {
                Truck t = bridge.peek();
                // 현재 시간 - 트럭이 올라갔던 시간 = 트럭이 지나온 거리
                if(answer - t.location >= bridge_length) {
                    currentWeightOnBridge -= t.weight;
                    bridge.poll();
                }
            }

            // 대기중인 트럭이 있으면
            if(!waitingTrucks.isEmpty()){
                // 다리에 올라갈 트럭이 더 있는지, 올렸을 때 길이를 초과하진 않는지
                if(currentWeightOnBridge + waitingTrucks.peek().weight <= weight &&
                        bridge.size() + 1 <= bridge_length){
                    Truck nextTruck = waitingTrucks.pop();
                    nextTruck.location = answer; // 트럭이 다리에 올라간 시점을 기록
                    currentWeightOnBridge += nextTruck.weight;
                    bridge.offer(nextTruck);
                }
            }
        }
        return answer;
    }
}
