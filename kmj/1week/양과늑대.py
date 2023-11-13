'''
양과 늑대 (프로그래머스 문제)

만약 더 이상 움직일 수 없다면 
 늑대의 수가 양의 수에 이상일 경우다.
 
 -전략- 
 그래프와 시작할 q 를 초기화한다. 
 bfs를 통해 현재 노드, 양, 늑대, 이동가능 노드를 담은 채 탐색한다.
 이동 가능 노드의 경우에 대해서 접근하게되면 가능한 경로를 추가해주고 
 접근이 완료하여 다음으로 넘어갈 때 삭제해준다. 
'''
from collections import deque
def solution(info, edges):
    graph = [[] for _ in range(len(info))]
    answer = 0
    for edge in edges:
        graph[edge[0]].append(edge[1])
    q = deque([[0,1,0,set()]]) # 현재노드, 양, 늑대, 이동 가능 노드 
    
    
    while q:
        now, sheep, wolf, nextNode = q.popleft()
        answer = max(answer,sheep)
        nextNode.update(graph[now]) 
        
        for next in nextNode:
            #늑대의 경우
            if info[next]:
                if sheep != wolf + 1: #늑대의 수가 양의 수에 이상이면 움직이지 못한다. 
                    q.append([next,sheep,wolf+1, nextNode - {next}])
            else:
                q.append([next,sheep+1,wolf, nextNode - {next}])
    return answer

info = [0,0,1,1,1,0,1,0,1,0,1,1]
edges = [[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]	
print(solution(info,edges))