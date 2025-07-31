import { ADD_COUNTRY, GET_COUNTRIES, SET_COUNTRIES } from "../constants/index";

const INITIAL_STATE = {
    countries:{}
};

export default (state= INITIAL_STATE, action) => {
    switch(action.type) {
        case GET_COUNTRIES:
            return { ...state };
        case SET_COUNTRIES: return {
            ...state,
            countries:action.payload
        };
        default : return state
    }
}