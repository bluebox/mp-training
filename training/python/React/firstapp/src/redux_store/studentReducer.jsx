const initialState = {
  students: [],
};

export default function studentReducer(state = initialState, action) {
  switch (action.type) {
    case 'CREATE':

      return {
        ...state,
        students: [...state.students, action.payload],
      };

    case 'UPDATE':

      return {
        ...state,
        students: state.students.map(student =>
          student.id === action.payload.id ? action.payload : student
        ),
      };

    case 'DELETE':

      return {
        ...state,
        students: state.students.filter(student => student.id !== action.payload.id),
      };

    default:
      return state; 
  }
}
