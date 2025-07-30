import { createStore } from 'redux';
import customerReducer from './customers'; // or inline reducer

export const store = createStore(customerReducer);
