import {GET_PATHLAB_STORES, ADD_PATHLAB_STORES } from "../constants";

const INITIAL_STATE = {};

export default (state =  INITIAL_STATE, action) => {
    switch(action.type) {
        case GET_PATHLAB_STORES : return [...state[action.payload]];
        case ADD_PATHLAB_STORES : return {...state, ...action.payload};
        default: return state;
    }
}