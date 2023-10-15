

function solution(bridge_length, weight, truck_weights) {
  const bridge = Array(bridge_length).fill(0);
  let time = 0;

  const sum_truck_weights = () => {
    return bridge.reduce((a, b) => a + b) + truck_weights[0];
  }

  while (truck_weights.length) { 
    bridge.shift();

    if (sum_truck_weights() <= weight) {
      bridge.push(truck_weights.shift());
      time++;
    } else {
      bridge.push(0);
      time++;
    }
  }

  return time + bridge_length;
}

const bridge_length = 100;
const weight = 100;
const truck_weights = [10, 10, 10, 10, 10, 10, 10, 10, 10, 10];

const result = solution(bridge_length, weight, truck_weights);
console.log(result);
