import { ADD_CITIES, GET_CITIES } from "../constants";

const INITIAL_STATE = {};

export default (state =  INITIAL_STATE, action) => {
    switch(action.type) {
        case GET_CITIES : return [...state[action.payload]];
        case ADD_CITIES : return {...state, ...action.payload};
        default: return state
    }
}