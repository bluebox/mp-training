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
maze = [[' ',' ',' '],
        [' ',' ',' '],
        [' ','#','#'],
        [' ','#',' ']]
print(maze_runer(maze, (0,0),(0,0)))