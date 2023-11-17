# from collections import deque


# def solution(n, vertex):
#     visited = [0] * (n+1)
#     visited[1] = -1
#     edges = [[] for __ in range(n+1)]

#     for i in vertex:
#         edges[i[0]].append(i[1])
#         edges[i[1]].append(i[0])
        
#     q = deque()
#     q.append([1,0])
    
#     while q:
#         now_node,now_depth = q.popleft()
        
#         for next_node in edges[now_node]:
            
#             if not visited[next_node]:
#                 visited[next_node] = now_depth + 1 
#                 q.append([next_node, now_depth + 1] )
                
#     answer = 0 
#     for i in visited:
#         if i == max(visited):
#             answer += 1
#     return answer


#------- 리펙토링 후 
# 컨벤션을 맞추고, 함수를 더 쪼개기 위해 노력해봤습니다.

from collections import deque


def solution(n, vertex):
    edges = initialize_graph(n, vertex)
    visited = bfs(edges)
    answer = count_max_depth_nodes(visited)
    return answer

def initialize_graph(n, vertex):
    edges = [[] for __ in range(n+1)]

    for v in vertex:
        edges[v[0]].append(v[1])
        edges[v[1]].append(v[0])

    return edges

def bfs(edges):
    n = len(edges) - 1
    visited = [0] * (n+1)
    visited[1] = -1

    q = deque()
    q.append([1, 0])

    while q:
        now_node, now_depth = q.popleft()

        for next_node in edges[now_node]:
            if not visited[next_node]:
                next_depth = now_depth + 1
                visited[next_node] = next_depth
                q.append([next_node, next_depth])   
    return visited

def count_max_depth_nodes(visited):
    max_depth = max(visited)
    return visited.count(max_depth)