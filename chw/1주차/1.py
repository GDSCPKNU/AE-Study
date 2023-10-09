import sys
from collections import deque

input = sys.stdin.readline


def find_ball(graph: list, target: str) -> [int, int]:
    for i in range(row):
        for j in range(col):
            if graph[i][j] == target:
                return i, j


def init_que(graph: list) -> deque:
    red_x, red_y = find_ball(graph, RED)
    blue_x, blue_y = find_ball(graph, BLUE)
    que = deque([(red_x, red_y, blue_x, blue_y, 0)])
    return que


def move_ball(graph, x, y, dir) -> [int, int, int]:
    dx, dy = dir
    nx, ny = x + dx, y + dy
    move_count = 1

    while graph[nx][ny] != WALL and graph[nx][ny] != HOLE:
        nx += dx
        ny += dy
        move_count += 1

    return (
        [nx - dx, ny - dy, move_count]
        if graph[nx][ny] == WALL
        else [nx, ny, move_count]
    )


def is_same_position(red_x, red_y, blue_x, blue_y) -> bool:
    return red_x == blue_x and red_y == blue_y


def solution(graph: list) -> int:
    que = init_que(graph)
    visited = []
    while que:
        red_x, red_y, blue_x, blue_y, count = que.popleft()
        visited.append((red_x, red_y, blue_x, blue_y))

        if count >= 10:
            continue

        for i in range(DIRECITON_LENGTH):
            new_red_x, new_red_y, red_move_count = move_ball(
                graph, red_x, red_y, DIRECITON[i]
            )
            new_blue_x, new_blue_y, blue_move_count = move_ball(
                graph, blue_x, blue_y, DIRECITON[i]
            )
            if graph[new_blue_x][new_blue_y] == HOLE:
                continue
            if graph[new_red_x][new_red_y] == HOLE:
                return count + 1

            if is_same_position(new_red_x, new_red_y, new_blue_x, new_blue_y):
                dx, dy = DIRECITON[i]
                if red_move_count > blue_move_count:
                    new_red_x -= dx
                    new_red_y -= dy
                else:
                    new_blue_x -= dx
                    new_blue_y -= dy

            if (new_red_x, new_red_y, new_blue_x, new_blue_y) not in visited:
                que.append((new_red_x, new_red_y, new_blue_x, new_blue_y, count + 1))

    return -1


if __name__ == "__main__":
    DIRECITON = [(-1, 0), (0, 1), (1, 0), (0, -1)]
    DIRECITON_LENGTH = 4
    PATH, WALL, HOLE, RED, BLUE = ".", "#", "O", "R", "B"
    row, col = map(int, input().split())
    graph = [list(map(str, input().rstrip())) for _ in range(row)]

    print(solution(graph))
