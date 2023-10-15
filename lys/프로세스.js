/**
p = [2,1,3,2]
i=0
cur = p.shift
1,3,2 돌면서 cur보다 우선순위가 큰게 있는지
있다면 -> cur를 p에 push
없다면 -> 다음 프로세스로, 이때 location이 같다면 i에 +1해서 return
**/
    
function solution(priorities, location) {

    let answer = 0;
    let jobOrder = 0;

    const getHighPriorities = (arr, front) => priorities.some(priority => (currentProcess < priority));

    while (answer === 0) {
        currentProcess = priorities.shift();
        
        if(getHighPriorities(priorities, currentProcess)) {
            priorities.push(currentProcess);
            //console.log(priorities);
            
            if(location === 0) {
                location = priorities.length - 1
            } else {
                location -= 1;
            }
        } else {
            jobOrder++;
            if (location === 0) {
                answer = jobOrder;
            } else {
                location -= 1;
            }
        }
    }
    return answer;
}