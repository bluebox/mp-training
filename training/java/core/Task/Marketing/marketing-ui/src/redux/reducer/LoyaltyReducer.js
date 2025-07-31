import { SET_LOYALTIES } from "../constants";

const INITIAL_STATE = {};

export default (state= INITIAL_STATE, action) => {
    switch(action.type) {
        case SET_LOYALTIES: 
        return {
            ...action.payload
        };
        default : return state;
    }
}