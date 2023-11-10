package week5;

public class 조이스틱 {
    public int solution(String name) {
        // 이름의 길이만큼 초기화 AAAAAA ->
        // 1. 현재 커서의 문자가 M(13)보다 작거나 같으면 다음 알파벳 순서로 찾기
        // 1-1. M보다 크면 이전 알파벳 순서로 찾기
        // 2. 현재 커서에서 알파벳이 완성되면 커서를 오른쪽으로 이동
        // J를 만들고, 어떻게 바로 맨 끝으로 가야겠다고 판단했는가? -> 보류

        int count = 0;
        for (int i = 0; i < name.length(); i++) {
            count = count + find(name.charAt(i));
            System.out.println(find(name.charAt(i)));
            if (!isEndOfString(name, i)) {
                if (name.charAt(i) == 'A')
                    continue;
                count++; // 커서를 옮김
            } // AAAAAA
        }

        return count;
    }

    public int find(char ch) {
        if(ch <= 77)
            return ch - 'A';
        return 1 + 'Z' - ch; // 이전 알파벳부터 찾으려고 아래 방향키를 한번 누르므로 -> 조작횟수 + 1
    }

    public boolean isEndOfString(String str, int idx) {
        if (idx == str.length()-1)
            return true;
        return false;
    }
}

// 알파벳 개수 26개
// A B C D E F G H I J K L |M| |N| O P Q R S T U V W X Y Z
// M : 77
// 아스키 A : 65 / Z : 90
