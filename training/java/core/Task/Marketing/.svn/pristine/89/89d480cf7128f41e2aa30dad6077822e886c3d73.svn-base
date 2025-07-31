import { combineReducers } from 'redux';
import  countriesReducer  from './CountriesReducer'
import  statesReducer  from './StatesReducer'
import  citiesReducer  from './CitiesReducer'
import  storesReducer  from './StoresReducer'
import  formJsonReducer  from './FormJsonReducer'
import loyaltyReducer from './LoyaltyReducer';
import categoryReducer from './CategoryReducer';
import pathlabStoresReducer from './PathlabStoreReducer';
export const CLEAR_ALL = 'CLEAR_ALL';

const appReducer = combineReducers({
    countriesReducer,
    statesReducer,
    citiesReducer,
    storesReducer,
    formJsonReducer,
    loyaltyReducer,
    categoryReducer,
    pathlabStoresReducer
    
});

export const rootReducer = (state, action) => {
    if (action.type === CLEAR_ALL) {
        Object.keys(state).forEach(key => {
            window.localStorage.removeItem(`persist:${key}`);
        });
        state = {};
    }
    return appReducer(state, action);
}
