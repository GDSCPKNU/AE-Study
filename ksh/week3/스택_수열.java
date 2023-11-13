package week3;

import java.io.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class 스택_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        List<String> answer = new ArrayList<>();

        int count = 1;
        for (int num : arr) {
            if (stack.empty()) {
                for (int i = 0; i < num; i++) {
                    stack.push(count++);
                    answer.add("+");
                }
            }
            while (stack.peek() < num) {
                stack.push(count++);
                answer.add("+");
            }
            if (stack.peek() != num) {
                bw.write("NO");
                bw.close();
                return;
            }
            stack.pop();
            answer.add("-");
        }

        for (String ans : answer) {
            bw.write(ans);
            bw.newLine();
        }

        bw.close();
    }
}








