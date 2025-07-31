import { createBrowserHistory } from 'history';
import React from 'react';
import ReactDOM from 'react-dom/client';
import { Provider } from 'react-redux';
import { Router } from 'react-router';
import { PersistGate } from 'redux-persist/integration/react';
import App from './App';
import store, { persistor } from './redux/store';
const history = createBrowserHistory();
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Provider store={store}>
            <PersistGate loading={null} persistor={persistor}>
                <Router history={history}>
                    <App />
                </Router>
            </PersistGate>
        </Provider>
  </React.StrictMode>
);