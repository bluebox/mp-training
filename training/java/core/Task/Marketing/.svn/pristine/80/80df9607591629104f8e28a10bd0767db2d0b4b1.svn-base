import { applyMiddleware, legacy_createStore as createStore } from "redux";
import { persistReducer, persistStore } from 'redux-persist';
import autoMergeLevel2 from 'redux-persist/lib/stateReconciler/autoMergeLevel2';
import storage from 'redux-persist/lib/storage';
import thunk from "redux-thunk";
import { rootReducer } from "./reducer/index";

const initialState = {};

const persistConfig = {
    key: 'marketing',
    storage: storage,
    stateReconciler: autoMergeLevel2,
    blacklist : []
};

const pReducer = persistReducer(persistConfig, rootReducer);
// export const history = typeof history !== 'undefined' ? syncHistoryWithStore(browserHistory, store) : [];
const middleWare = applyMiddleware(thunk);
const store = createStore(pReducer, initialState, middleWare);

export default store;
export const persistor = persistStore(store);