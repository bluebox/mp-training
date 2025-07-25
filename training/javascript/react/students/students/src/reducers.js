import { ADD_USER,UPDATE_USER,DELETE_USER } from "./availableActions";

const initialState = [];

const userReducer = (state = initialState,action) =>{
    switch(action.type){
        case ADD_USER:
            return [...state,action.payload]
        case DELETE_USER:
            return state.filter(user => user.id !== action.payload)
        case UPDATE_USER:
            return state.map(usr=>usr.id===action.payload.id?{...usr,...action.payload.data}:usr)
        default:
            return state
    }
}
export default userReducer;