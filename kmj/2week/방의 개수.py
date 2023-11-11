# ''' 
# 100,000개의 움직일 수 있는 판을 만드는 것은 10초를 넘는다. 
# 그렇기에 틀의 제한없이 자유롭게 움직이되, 
# 1. 움직인 경로를 기억하도록 하며
# 2. 움직인 경로가 영역을 만들었는가 체크한다.
 
 
# ** 경로를 기억 **
# -> visited를 defaultdict(int)로 구현하여 추가해준다. 
# -> visited_direction 을 defaultdict(int)로 구현하여 하나의 정점을 지날 떄, 방향도 같이 기억해준다. 


# ** 공간 체크 **
# -> 이미 지나간 정점이라면 하나의 공간을 만든다. 


# ** 예외 ** 
# 1. 같은 정점에 같은 간선으로 똑같이 방문하는 경우. 공간이 안생겼지만 같은 정점을 지난다.
# -> 하나의 정점에 방문하게될 때, 어디서 현재 정점으로 방문 했는지를 저장한다.

# 2. 무제한의 모양을 그리게될 수 있다. 같은 정점을 하나 지났지만 두 개의 영역이 생긴다. 
# -> 한 번에 두 칸을 움직이도록 수정 하자.그러면 점을 지나치지 않고 두개의 영역이 생기는 것을 방지할 수 있다.   
# '''
# from collections import defaultdict

# def solution(arrows):
#     answer = 0
#     now = (0, 0)
#     visited = defaultdict(int)
#     visited[now] = 1
#     visited_direction = defaultdict(int)

#     for arrow in arrows:
#         for _ in range(2) :
#             move_direction = arrow_to_direction(arrow)
#             next = (now[0] + move_direction[0], now[1] + move_direction[1])  
        
#             if visited[next] == 1 :
#                 if visited_direction[(now, next)] == 0 :
#                     answer += 1
#             else :
#                 visited[next] = 1
                
#             visited_direction[(now, next)] = 1
#             visited_direction[(next, now)] = 1
            
#             now = next 
            
#     return answer

# def arrow_to_direction(arrow):
#     move_direction = [(-1,0), (-1, 1), (0, 1),
#                       (1, 1), (1, 0), (1, -1),
#                       (0, -1), (-1, -1) ]
#     return move_direction[arrow]



#------- 리펙토링 후 
# 컨벤션을 맞추고, 함수를 더 쪼개기 위해 노력해봤습니다.

from collections import defaultdict


def solution(arrows):
    answer = 0
    now = (0, 0)
    visited_direction = defaultdict(bool)
    visited = defaultdict(bool)
    visited[now] = True

    for arrow in arrows:
        for __ㅌㅇㅊ  in range(2):
            move_direction = arrow_to_direction(arrow)
            next_pos = get_next_position(now, move_direction)

            if visited[next_pos] == 1:
                if visited_direction[(now, next_pos)] == 0:
                    answer += 1
            else:
                visited[next_pos] = 1

            update_visited_direction(now, next_pos, visited_direction)
            now = next_pos
    return answer

def arrow_to_direction(arrow):
    move_directions = [(-1, 0), (-1, 1), (0, 1), (1, 1),
                       (1, 0), (1, -1),(0, -1), (-1, -1)]
    return move_directions[arrow]

def get_next_position(current_pos, move_direction):
    return (current_pos[0] + move_direction[0], current_pos[1] + move_direction[1])

def update_visited_direction(from_pos, to_pos, visited_direction):
    visited_direction[(from_pos, to_pos)] = 1
    visited_direction[(to_pos, from_pos)] = 1