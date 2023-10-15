
function solution(info, edges) {
    let maxSheep = 1; 
    let graph = {}; 
  
    for (let [from, to] of edges) {
      graph[from] ? graph[from].push(to) : graph[from] = [to];
    }
  
    function DFS(currentNode, sheep, wolf, visitedNodes) {

      if (sheep <= wolf) return; // 늑대가 많거나 적으면 종료
  
      maxSheep = Math.max(maxSheep, sheep);
      let nextNodes = graph[currentNode] ? [...visitedNodes, ...graph[currentNode]] : [...visitedNodes]; //0,1,2
      nextNodes.splice(nextNodes.indexOf(currentNode), 1); // 방문 노드 제거
  
      for (let nextNode of nextNodes) {
        if (info[nextNode] === 0) {
          DFS(nextNode, sheep + 1, wolf, nextNodes);
        } else {
          DFS(nextNode, sheep, wolf + 1, nextNodes);
        }
      }
    }
  
    DFS(0, 1, 0, [0]);
  
    return maxSheep;
  }
  