from collections import defaultdict
def maze_runer(maze, start_pos, end_pos):
    stack = [(start_pos)]
    path = []
    found = False
    parents = dict()
    while(len(stack) >0):
        pos = stack.pop()
        if pos == end_pos and maze[pos[0]][pos[1]] != '#':
            while pos != start_pos:
                path.append(pos)
                pos = parents[pos]
            path.append(start_pos)
            path.reverse()
            return path 
        else:
            if(maze[pos[0]][pos[1]] == '#'):
                continue
            maze[pos[0]][pos[1]] = '#'
            if(pos[1]+1 <len(maze[pos[0]])):
                if((pos[0],pos[1]+1) not in parents):
                    parents[(pos[0],pos[1]+1)] = pos
                stack.append((pos[0],pos[1]+1))
            
            if(pos[0]+1 <len(maze)):
                if((pos[0]+1,pos[1]) not in parents):
                    parents[(pos[0]+1,pos[1])] = pos
                stack.append((pos[0]+1,pos[1]))
                
            if(pos[1]-1 >=0):
                if((pos[0],pos[1]-1) not in parents):
                    parents[(pos[0],pos[1]-1)] = pos
                stack.append((pos[0],pos[1]-1))
                
            if(pos[0]-1 >= 0):
                if((pos[0]-1,pos[1]) not in parents):
                    parents[(pos[0]-1,pos[1])] = pos
                stack.append((pos[0]-1,pos[1]))
            #stack.append((pos[0]+1,pos[1]))
            # stack.append((pos[0],pos[1]-1))
            # stack.append((pos[0]-1,pos[1]))
            
    return None

print(maze_runer([[' ','#',' '],[' ','#','#'],[' ',' ',' ']], (0,0),(2,2)))    


# from typing import List, Tuple, Optional

# Coord = Tuple[int, int]          # (row, col)

# def maze_runner(maze: List[List[str]],
#                 start: Coord,
#                 goal: Coord) -> Optional[List[Coord]]:
#     stack = [start]
#     parent: dict[Coord, Coord] = {}          # child -> parent

#     while stack:
#         r, c = stack.pop()

#         # reached the goal ─ reconstruct and return the whole route
#         if (r, c) == goal:
#             path: List[Coord] = []
#             while (r, c) != start:
#                 path.append((r, c))
#                 r, c = parent[(r, c)]
#             path.append(start)
#             path.reverse()
#             return path                      # ← finished ✔

#         # bounds or wall?
#         if not (0 <= r < len(maze) and 0 <= c < len(maze[0])):
#             continue
#         if maze[r][c] == '#':                # wall or already visited
#             continue

#         maze[r][c] = '#'                     # mark as visited

#         # explore neighbours (R, D, L, U)
#         for nr, nc in [(r, c+1), (r+1, c), (r, c-1), (r-1, c)]:
#             if 0 <= nr < len(maze) and 0 <= nc < len(maze[0]) and maze[nr][nc] != '#':
#                 if (nr, nc) not in parent:   # don’t overwrite older parent
#                     parent[(nr, nc)] = (r, c)
#                 stack.append((nr, nc))

#     return None                               # no route found

# print(maze_runner([[' ','#',' '],[' ','#','#'],[' ',' ',' ']], (0,0),(2,2)))        