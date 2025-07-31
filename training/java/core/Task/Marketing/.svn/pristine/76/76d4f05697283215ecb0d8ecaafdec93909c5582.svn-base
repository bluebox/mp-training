import {ADD_STORES, GET_STORES } from "../constants";

const INITIAL_STATE = {};

export default (state =  INITIAL_STATE, action) => {
    switch(action.type) {
        case GET_STORES : return [...state[action.payload]];
        case ADD_STORES : return {...state, ...action.payload};
        default: return state;
    }
}