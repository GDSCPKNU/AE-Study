DIRECTION = [(0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1), (-1, 0), (-1, 1)]


def solution(arrows):
    answer = 0
    x, y = 0, 0

    edge_set = set()
    position_set = set([(x, y)])

    for arrow in arrows:
        for _ in range(2):
            dx = DIRECTION[arrow][0]
            dy = DIRECTION[arrow][1]
            nx, ny = x + dx, y + dy

            if (nx, ny) in position_set and (x, y, nx, ny) not in edge_set:
                answer += 1

            position_set.add((nx, ny))
            edge_set.add((x, y, nx, ny))
            edge_set.add((nx, ny, x, y))
            x, y = nx, ny

    return answer
