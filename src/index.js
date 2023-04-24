import React, { StrictMode } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import './bootstrap.min.css';
import  {Provider} from 'react-redux';
import store from './store';
import { createRoot } from "react-dom/client";

// const root = ReactDOM.createRoot(document.getElementById('root') );
const rootElement = document.getElementById("root");
const root = createRoot(rootElement);
root.render(
  <Provider store={store}>
      <App />
  </Provider>

);


reportWebVitals(); 
