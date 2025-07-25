import {createStore, combineReducers} from 'redux';
import userReducer from './reducers';

const originReducer = combineReducers({ users:userReducer});
const store = createStore(originReducer);
export default store;