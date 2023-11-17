package week3;

import java.io.*;
import java.util.StringTokenizer;

public class 오큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        boolean[] hasBiggerThanMe = new boolean[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    bw.write(arr[j] + " ");
                    hasBiggerThanMe[i] = true;
                    break;
                }
            }
            if (!hasBiggerThanMe[i]) {
                bw.write(-1 + " ");
            }
        }

        bw.close();
    }
}
