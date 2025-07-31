import { ADD_STATES, GET_STATES } from "../constants";

const INITIAL_STATE = {
    
};

export default (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case GET_STATES: return [ ...state[action.payload] ];
        case ADD_STATES: return {...state, ...action.payload };
        default : return state
    }
}