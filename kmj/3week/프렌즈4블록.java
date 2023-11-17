public class 프렌즈4블록 {

    int answer;

    public static void main(String[] args) {
        프렌즈4블록 solution = new 프렌즈4블록();
        int result = solution.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"});
        System.out.println(result);
    }

    public int solution(int m, int n, String[] board) {
        answer = 0;
        char[][] table = initializeTable(m, n, board);

        while (true) {
            boolean[][] check = new boolean[m][n];

            if (!checkRemove(check, table, n, m)) {
                break;
            }

            countRemoveBlocks(check, table, m, n);
            dropBlocks(table, m, n);
        }

        return answer;
    }

    private void countRemoveBlocks(boolean[][] check, char[][] table, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j]) {
                    table[i][j] = '-';
                    answer++;
                }
            }
        }
    }

    private boolean checkRemove(boolean[][] check, char[][] table, int n, int m) {
        boolean hasRemove = false;

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (table[i][j] == '-') {
                    continue;
                }
                char now = table[i][j];
                if (table[i][j + 1] == now && table[i + 1][j] == now && table[i + 1][j + 1] == now) {
                    check[i][j] = true;
                    check[i][j + 1] = true;
                    check[i + 1][j] = true;
                    check[i + 1][j + 1] = true;
                    hasRemove = true;
                }
            }
        }
        return hasRemove;
    }

    private void dropBlocks(char[][] table, int m, int n) {
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (table[i][j] == '-') {
                    for (int k = i; k < m; k++) {
                        if (table[k][j] != '-') {
                            table[i][j] = table[k][j];
                            table[k][j] = '-';
                            break;
                        }
                    }
                }
            }
        }
    }

    private char[][] initializeTable(int m, int n, String[] board) {
        char[][] table = new char[m][n];

        for (int i = 0; i < m; i++) {
            table[i] = board[m - i - 1].toCharArray();
        }

        return table;
    }
}
