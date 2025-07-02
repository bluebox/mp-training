import
def heuristic(curr_pos, end_pos):
    return abs(curr_pos[0]-end_pos[0]),abs(curr_pos[1]-end_pos[1])

def maze_runner(matrix):