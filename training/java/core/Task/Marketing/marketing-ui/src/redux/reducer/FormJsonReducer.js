import { ADD_FORM, GET_FORM } from "../constants";

const INITIAL_STATE = {};

export default (state = INITIAL_STATE, action) => {
    switch(action.type) {
        case GET_FORM: return { ...state};
        case ADD_FORM : return  {...state, ...action.payload};
        default: return  {...state};
    }
}