SHEEP = 0
WOLF = 1


def makes_tree(edges: list) -> dict:
    tree = {}

    for parent, child in edges:
        if parent not in tree:
            tree[parent] = [child]
            continue
        tree[parent].append(child)

    return tree


def find_max_sheep(
    info: list, tree: dict, visitable_nodes: list, sheep: int, wolf: int
) -> int:
    if not visitable_nodes:
        return sheep

    max_sheep = sheep

    for i, node in enumerate(visitable_nodes):
        children_list = [] if node not in tree else tree[node]
        next_nodes = visitable_nodes[:i] + visitable_nodes[i + 1 :] + children_list
        if info[node] == SHEEP:
            max_sheep = max(
                max_sheep, find_max_sheep(info, tree, next_nodes, sheep + 1, wolf)
            )
        elif sheep - 1 > wolf:
            max_sheep = max(
                max_sheep, find_max_sheep(info, tree, next_nodes, sheep, wolf + 1)
            )

    return max_sheep


def solution(info, edges):
    ROOT_INDEX = 0
    tree = makes_tree(edges)
    return find_max_sheep(info, tree, [ROOT_INDEX], 0, 0)
