from collections import deque

graph = [[] for _ in range(5)]


def add(a, b):
    graph[a].append(b)
    graph[b].append(a)


def dispaly():
    for row in graph:
        print(row)


def bfs():
    vis = set()
    ans = deque()
    vis.add(0)
    ans.append(0)
    while len(ans):
        val = ans.popleft()
        print(val, end=' ')
        for i in graph[val]:
            if i not in vis:
                vis.add(i)
                ans.append(i)


add(0, 1)
add(1, 2)
add(2, 3)
add(3, 4)
dispaly()
bfs()
