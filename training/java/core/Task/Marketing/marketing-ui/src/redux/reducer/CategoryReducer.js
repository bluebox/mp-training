import { SET_CATEGORIES } from "../constants";

const INITIAL_STATE = {};

export default (state= INITIAL_STATE, action) => {
    switch(action.type) {
        case SET_CATEGORIES: 
        return {
            ...action.payload
        };
        default : return state;
    }
}

