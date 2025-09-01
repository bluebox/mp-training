import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import {Provider} from "react-redux";
import store from "./utilities/redux/store.js";
import App from "./App.jsx";
import Loading from "./pages/Loading.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Provider store={store}>
      <Loading />
      <App />
    </Provider>
  </StrictMode>
);
