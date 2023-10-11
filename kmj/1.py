'''세로 크기는 N, 가로 크기는 M
파란 구슬이 구멍에 들어가면 안 된다.
빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 
빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다
빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다
빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다
기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지

두 공의 위치가 동일한 경우는 이미 했던 행위다. (break 지점)

--전략 --

네가지 방향에 대해 미리 변수 선언해준다. (왼쪽 ,오른쪽 ,위,아래)
두 공의 위치를 가진 visited를 선언한다.
[[1][2][3][4]] 1,2파란색  3,4 빨간색 공 위치 
visited = [[[[False]*m for _ in range(n)] for _ in range(m)] for _ in range(n)
-------------------------------------------초기화

네 방향에 대해 빨간색공, 파란색공 위치, depth와 visited를 가지고 반복한다.
만약 depth가 10을 초과한다면 break 

받은 방향에 대해서 현재 파란색,빨간색 공의 위치를 움직인다 (move 작성) 위치와 움직인거리 return 
    벽에서만나는 경우, 이동거리가 긴 공이 끝에서 한칸 뒤로 간다. 
                    이동거리가 짧은 공은 끝까지 이동한다. 
    
'''
from collections import deque

n, m = map(int, input().split())
board = [list(input().strip()) for _ in range(n)] #String 값은  strip() 사용 
visited = [[[[False]*m for _ in range(n)] for _ in range(m)] for _ in range(n)] # 4차원배열 
dx = [-1, 0, 1, 0] #상 우 하 좌
dy = [0, 1, 0, -1]
q = deque()


def init(): # 초기화 함수 (board, Red Blue 좌표, q추가, visited 추가)
    rx, ry, bx, by = [0] * 4
    for i in range(n):
        for j in range(m):
            if board[i][j] == 'R': 
                rx, ry = i, j
            elif board[i][j] == 'B':
                bx, by = i, j
    q.append((rx, ry, bx, by, 1)) 
    visited[rx][ry][bx][by] = True
    
    
def move(x, y, dx, dy):
    # 이동하는데 가볍게 구현해야했다.(이전에 많은 역할을 부여했다.)
    # 이동만 하도록 한다.
    count = 0 
    while board[x+dx][y+dy] != '#' and board[x][y] != 'O':
        x += dx
        y += dy
        count += 1
    return x, y, count


def bfs():
    init() 
    while q: 
        rx, ry, bx, by, depth = q.popleft() 
        if depth > 10: 
            break
        for i in range(len(dx)): 
            next_rx, next_ry, r_count = move(rx, ry, dx[i], dy[i]) 
            next_bx, next_by, b_count = move(bx, by, dx[i], dy[i]) 
                        
            if board[next_bx][next_by] == 'O':  #파란구술이 떨어지는가 먼저 확인한다. 
                continue
            if board[next_rx][next_ry] == 'O': 
                print(depth)
                return
            if next_rx == next_bx and next_ry == next_by :
                if r_count > b_count: 
                    next_rx -= dx[i]
                    next_ry -= dy[i]
                else:
                    next_bx -= dx[i]
                    next_by -= dy[i]
                    
            if not visited[next_rx][next_ry][next_bx][next_by]:
                visited[next_rx][next_ry][next_bx][next_by] = True
                q.append((next_rx, next_ry, next_bx, next_by, depth +1))
    print(-1) 

bfs()
