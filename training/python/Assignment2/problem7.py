def maze_runner(maze, start_pos, end_pos):
    rows = len(maze)
    cols = len(maze[0])
    path = [start_pos]
    visited = set([start_pos])
    current = start_pos

    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    while current != end_pos:
        moved = False
        for row, col in directions:
            netrow, netcol = current[0] + row, current[1] + col

            if (0 <= netrow < rows and 0 <= netcol < cols and
                (netrow, netcol) not in visited and
                maze[netrow][netcol] in (' ', 'E')):
                
                current = (netrow, netcol)
                path.append(current)
                visited.add(current)
                moved = True
                break

        if not moved:
            return None

    return path

maze = [
    ['#', '#', '#', '#', '#'],
    ['#', 'S', ' ', '#', '#'],
    ['#', ' ', ' ', ' ', '#'],
    ['#', '#', '#', 'E', '#']
]

start = (1, 1)
end = (3, 3)

result = maze_runner(maze, start, end)
print(result)