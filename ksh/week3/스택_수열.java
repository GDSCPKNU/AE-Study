package week3;

import java.io.*;
import java.util.Stack;

public class 스택_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();

        int count = 1;
        for (int num : arr) {
            while (stack.empty() || stack.peek() < num) {
                stack.push(count++);
                sb.append("+\n");
            }
            if (stack.peek() != num) {
                System.out.println("NO");
                return;
            }
            stack.pop();
            sb.append("-\n");
        }
        System.out.println(sb);
    }
}