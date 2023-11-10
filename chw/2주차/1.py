from collections import deque


def calculate_dist(nodes: int, graph: list) -> list:
    que = deque([(1, 0)])
    dist = [0] * (nodes + 1)
    dist[1] = -1

    while que:
        node, curr_dist = que.popleft()

        for next in graph[node]:
            if not dist[next]:
                dist[next] = curr_dist + 1
                que.append((next, curr_dist + 1))

    return dist


def get_max_dist_length(dist_info: list) -> int:
    max_dist = max(dist_info)
    return len([dist for dist in dist_info if dist == max_dist])


def solution(n, edge):
    graph = [[] for _ in range(n + 1)]

    for node1, node2 in edge:
        graph[node1].append(node2)
        graph[node2].append(node1)

    dist_info = calculate_dist(n, graph)

    return get_max_dist_length(dist_info)
