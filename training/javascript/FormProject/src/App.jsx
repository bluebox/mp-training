import { useSelector } from "react-redux";
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import Home from "./components/Home";
import Create from "./components/Create";
import Configurations from "./components/Configurations";
import Record from "./components/Record";
import Login from "./components/Login";
import Logout from "./components/Logout";

function App() {
  const isLoggedIn = useSelector((state) => state.auth);

  return (
    <Router>
      {isLoggedIn && (
        <nav>
          <Link to="/">Home</Link> | <Link to="/create">Create</Link> | <Link to="/configurations">Configurations</Link> | <Link to="/logout">Logout</Link>
        </nav>
      )}
      <Routes>
        {!isLoggedIn ? (
          <Route path="*" element={<Login />} />
        ) : (
          <>
            <Route path="/" element={<Home />} />
            <Route path="/create" element={<Create />} />
            <Route path="/edit/:id" element={<Create />} />
            <Route path="/configurations" element={<Configurations />} />
            <Route path="/logout" element={<Logout />} />
            <Route path="/login" element={<Navigate to="/" />} />
          </>
        )}
      </Routes>
    </Router>
  );
}

export default App;

