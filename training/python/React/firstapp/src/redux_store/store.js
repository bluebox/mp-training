import { createStore} from 'redux';

import studentReducer from './studentReducer';


const store = createStore(studentReducer);

export default store;
