import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import { AuthProvider } from "./context/AuthContext.jsx";
import { UserProvider } from "./context/UserContext.jsx";
import { Provider } from 'react-redux';
import store from './store/store.jsx'

createRoot(document.getElementById("root")).render(
  <>
   <Provider store={store}>
    <AuthProvider>
     <UserProvider>
      <App/>
     </UserProvider>
    </AuthProvider>
    </Provider>
  </>
);
