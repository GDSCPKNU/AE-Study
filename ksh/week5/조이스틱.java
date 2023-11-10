package week5;

public class 조이스틱 {
    public int solution(String name) {
        int count = 0;
        int len = name.length();
        int move = len - 1; // 오른쪽으로만 이동했을 때의 기본 이동 횟수

        for (int i = 0; i < len; i++) {
            count += find(name.charAt(i));

            // 다음 알파벳으로 이동
            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }

            // 왼쪽으로 이동하는 경우와 오른쪽으로 이동하는 경우 중 더 작은 값을 선택
            move = Math.min(move, i + len - next + Math.min(i, len - next));
        }

        count += move;

        return count;
    }

    public int find(char ch) {
        if(ch <= 77)
            return ch - 'A';
        return 1 + 'Z' - ch;
    }
}

// 알파벳 개수 26개
// A B C D E F G H I J K L |M| |N| O P Q R S T U V W X Y Z
// M : 77
// 아스키 A : 65 / Z : 90
