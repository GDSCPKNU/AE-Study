import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

class Solution {
    public static class Process {
        int priority;
        int position;

        public Process(int priority, int position){
            this.priority = priority;
            this.position = position;
        }

        public int getPriority() {
            return this.priority;
        }

        public int getPosition() {
            return this.position;
        }
    }

    // [2, 1, 3, 2]	 2	1
    public int solution(int[] priorities, int location) {
        Queue<Process> processQueue = new LinkedList<>();

        // location 프로세스의 실행 순서?
        for (int i = 0; i < priorities.length; i++) {
            processQueue.offer(new Process(priorities[i], i));
        }

        int time = 0;
        // 맨 앞 프로세스를 꺼낸다음 꺼내어진 프로세스가 가장 높은 우선순위를 가지는 프로세스 였다면 continue
        // 꺼낸 프로세스가 최댓값이 아니면 맨 뒤에 넣음 >> 찾을때까지 반복
        while (!processQueue.isEmpty()) {
            Process proc = processQueue.poll();
            // 꺼낸 값이 최대 우선순위가 아니다 > 다시 넣는다.
            if (!isMaxPriorityProcess(processQueue, proc)) {
                processQueue.offer(proc);
                continue;
            }
            time++;
            if (proc.getPosition() == location) break;
        }
        return time;
    }

    public boolean isMaxPriorityProcess(Queue<Process> processQueue, Process proc) {
        int maxPriority = proc.getPriority();
        for (Process p : processQueue) {
            if (p.getPriority() > maxPriority) return false;
        }
        return true;
    }
}