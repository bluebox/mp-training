def maze_runner(maze,start_pos,end_pos):
    dir=[(0,1),(1,0),(0,-1),(-1,0)]
    visited=[]
    path=[]
    current=start_pos
    path.append(current)
    while current!=end_pos:
        moved=False
        for dr,dc in dir:
            new_row=current[0]+dr
            new_col=current[1]+dc
            if 0<=new_row<len(maze) and 0<=new_col<len(maze[0]):
                if (maze[new_row][new_col]==' ' or maze[new_row][new_col]=='E') :
                    current=(new_row,new_col)
                    visited.append(current)
                    path.append(current)
                    moved=True
                    break
        if not moved:
            return None
    return path
    


maze=[
    ['S',' ',' '],
    ['#',' ','#'],
    ['#',' ','E']
]
print(maze_runner(maze,(0,0),(2,2)))