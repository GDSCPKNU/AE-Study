package week1;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class 가장_큰_수 {
    public String solution(int[] numbers) {
        List<String> stringList = new ArrayList<>();
        for(int num : numbers){
            stringList.add(String.valueOf(num));
        }

        // 1. 사전순 내림차순 정렬
        Collections.sort(stringList, Collections.reverseOrder());
        // 람다식 숙지 필요
        Collections.sort(StringList, (a, b) -> (b + a).compareTo(a + b));

        StringBuilder sb = new StringBuilder();
        for(String str : stringList){
            sb.append(str);
        }

        // 정답을 본 부분
        if(sb.charAt(0) == '0') {
            return "0";
            // 예외 처리: 가장 큰 수가 0으로 시작하는 경우
            // 모든 원소가 0인 경우에도 "000..."이 아닌 "0"으로 반환
        }

        return sb.toString();
    }
}