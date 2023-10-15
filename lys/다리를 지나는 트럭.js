
function solution(bridge_length, weight, truck_weights) {

  let bridge = Array(bridge_length).fill(0); 
  let time = 0; 

  while (truck_weights.length) { // 대기 트럭이 없을 때까지
    bridge.shift();

    // bridge 위에 트럭 무게와 다음 트럭 무게 합이 weight보다 작거나 같으면
    if (bridge.reduce((a, b) => a + b) + truck_weights[0] <= weight) {
      bridge.push(truck_weights.shift());
      time++;
    } else {
      bridge.push(0);
      time++;
    }
  }

  return time + bridge_length;
}

//const bridge_length = 100;
//const weight = 100;
//const truck_weights = [10,10,10,10,10,10,10,10,10,10];

//const result = solution(bridge_length, weight, truck_weights);
//console.log(result); 
