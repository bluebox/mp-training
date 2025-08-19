def maze_runner(maze, start_pos, end_pos):
    i, j = start_pos
    x, y = end_pos
    l = [start_pos]
    n = len(maze)
    m = len(maze[0])
    no_path = []
    while (i != x or j != y) and l:
        if j != m - 1 and ((i, j + 1) not in l) and ((i, j + 1) not in no_path) and maze[i][j + 1] != "#":

            j += 1
            l.append((i, j))
        elif i != n - 1 and ((i + 1, j) not in l) and ((i + 1, j) not in no_path) and maze[i + 1][j] != "#":
            i += 1
            l.append((i, j))
        elif maze[i][j - 1] != "#" and ((i, j - 1) not in l) and ((i, j - 1) not in no_path) and j != 0:
            j -= 1
            l.append((i, j))
        elif maze[i - 1][j] != "#" and ((i - 1, j) not in l) and ((i - 1, j) not in no_path) and i != 0:
            i -= 1
            l.append((i, j))
        else:
            if len(l) == 1:
                l = None
            else:
                no_path.append((i, j))
                l.remove(l[-1])
                i, j = l[-1]
    return l


maze = [["-", "#", "#", "#", " ", "-"],
        ["-", "-", "-", "#", "-", "-"],
        ["#", "#", "-", "-", "#", "#"],
        ["#", "#", "-", "#", "#", "#"],
        ["#", "#", "-", "-", "-", "-"],
        ["#", "#", "#", "#", " ", "-"]
        ]


print(maze_runner(maze, (0, 0), (5, 4)))