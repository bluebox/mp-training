def maze_runner(maze_, start__pos, end__pos):
    rows, cols = len(maze_), len(maze_[0])
    directions = [(-1, 0), (0, -1), (1, 0), (0, 1)]
    visited = set()
    stack = [(start__pos, [start__pos])]

    while stack:
        (row, col), path = stack.pop()

        if (row, col) == end__pos:
            return path

        if (row, col) in visited:
            continue
        visited.add((row, col))

        for dr, dc in directions:
            nr, nc = row + dr, col + dc
            if 0 <= nr < rows and 0 <= nc < cols and maze_[nr][nc] != '#' and (nr, nc) not in visited:
                stack.append(((nr, nc), path + [(nr, nc)]))
    return []

maze = [
    ['S', '#', '#', ' '],
    [' ', '#', '#', ' '],
    [' ', '#', ' ', 'E'],
    [' ', ' ', ' ', '#']
]

start_pos = (0, 0)
end_pos = (2, 3)

result = maze_runner(maze, start_pos, end_pos)
if result:
    print("Path to exit:", result)
else:
    print("Runner is stuck!")
