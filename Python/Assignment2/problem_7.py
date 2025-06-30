from collections import deque


def maze_runner(maze, start_pos, end_pos):
    rows = len(maze)
    cols = len(maze[0])
    visited = set()
    queue = deque()

    queue.append((start_pos, [start_pos]))
    visited.add(start_pos)

    drow = [0, 1, 0, -1]
    dcol = [1, 0, -1, 0]

    while queue:
        current_pos, path = queue.popleft()

        if current_pos == end_pos:
            return path

        for i in range(4):
            row = current_pos[0] + drow[i]
            col = current_pos[1] + dcol[i]

            if 0 <= row < rows and 0 <= col < cols:
                cell = maze[row][col]
                next_pos = (row, col)

                if (cell == ' ' or cell == 'E') and next_pos not in visited:
                    visited.add(next_pos)
                    queue.append((next_pos, path + [next_pos]))

    return None

maze = [
    ['S', ' ', '#', ' ', 'E'],
    ['#', ' ', '#', ' ', '#'],
    ['#', ' ', ' ', ' ', '#'],
    ['#', '#', '#', ' ', '#']
]

start = (0, 0)
end = (0, 4)

result = maze_runner(maze, start, end)
print(result)
