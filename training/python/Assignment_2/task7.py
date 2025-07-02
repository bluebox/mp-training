from django.db.models.expressions import result


def maze_runner(maze, start_pos, end_pos,path = None, visited = None):
    #right, down, left, up
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    if path == None:
        path = []
    if visited == None:
        visited = []

    curr_pos = start_pos
    row, col = curr_pos
    if (row < 0 or row >= len(maze) or col < 0 or col >= len(maze[0]) or maze[row][col] == '#'  or start_pos in visited):
        return None

    path.append(curr_pos)
    visited.append(curr_pos)

    if curr_pos == end_pos:
        return path

    for dr, dc in directions:
        new_pos = (row + dr, col + dc)
        res = maze_runner(maze, new_pos, end_pos, path, visited)
        if res:
            return res

    path.pop()
    visited.pop()
    return None

maze = [
    ['S', ' ', '#', '#', ' '],
    [' ', '#', '#', '#', ' '],
    [' ', ' ', ' ', ' ', ' '],
    [' ', '#', '#', '#', ' '],
    [' ', ' ', ' ', 'E', ' ']
]
start_pos = (0, 0)
end_pos = (4, 3)
print(maze_runner(maze, start_pos, end_pos))