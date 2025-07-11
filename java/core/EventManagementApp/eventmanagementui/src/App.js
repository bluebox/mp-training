import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './App.css';
import RootLayout from './RootLayout';
import AllEvents from './pages/EventCreation/AllEvents';
import AddEvent from './pages/EventCreation/AddEvent';
import Home from './pages/Home';
import AdminDashBoard from './pages/UserDashBoard/AdminDashBoard';
import FacultyDashBoard from './pages/UserDashBoard/FacultyDashBoard';
import StudentDashBoard from './pages/UserDashBoard/StudentDashBoard';
import UpdateEvent from './pages/EventCreation/UpdateEvent';
import EventById from './pages/EventCreation/EventById';
import AllUsers from './pages/Users/AllUsers';
import AddUser from './pages/Users/AddUser';
import Login from './pages/Authentication/Login';

function App() {
 const router=createBrowserRouter([
    {
      path:"/",
      element:<RootLayout />,
      children:[
         {
          path:"/",
          element:<Home />,
        },
        // Event Cretions
        {
          path:"/events",
          element:<AllEvents />,
        },
         {
          path:"/event",
          element:<EventById />,
        },
        {
          path:"/addEvent",
          element:<AddEvent />,
        },
        {
          path:"/updateEvent",
          element:<UpdateEvent />,
        },
        // User Dashbords
         {
          path:"/admin",
          element:<AdminDashBoard />
        },
         {
          path:"/faculty",
          element:<FacultyDashBoard />
        },
         {
          path:"/student",
          element:<StudentDashBoard />
        },
        // Users 
          {
          path:"/users",
          element:<AllUsers />,
        },
         {
          path:"/addUser",
          element:<AddUser />,
        },
        // Login
         {
          path:"/login",
          element:<Login />,
        }
      ]
    }
  ])
  return (
    <div className="App">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
