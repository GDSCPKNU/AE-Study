import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    // 정렬 한다음에 탐색
    public boolean solution(String[] phoneBook) {
        Arrays.sort(phoneBook);

        for (int i = 0; i < phoneBook.length - 1; i++) {
            if (phoneBook[i+1].startsWith(phoneBook[i])) return false;
        }

        return true;
    }
}