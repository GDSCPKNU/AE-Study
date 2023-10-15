
function solution(numbers) {

    let answer = numbers.sort(function(a,b) { 
        a = a.toString();
        b = b.toString();
        return (b+a) - (a+b);
    }).join('');

    return answer[0] === '0' ? '0' : answer   // 모든 숫자가 0인 경우 000 이 나오지 않게 0을 출력
}
//console.log(solution([6, 10, 2]));
//console.log(solution([0, 0, 0]));
//console.log(solution([3, 30, 34, 5, 9]));

// 문자열을 그대로 연결한 수(b+a) - 바꿔 연결한 수(a+b)가 양수이면 b를 a앞에 둠, 음수면 반대로 둠
// ex) b(3) + a(30) - a(30) + b(3) => 330 - 303 = 양수
// 3 30 순서 그대로
