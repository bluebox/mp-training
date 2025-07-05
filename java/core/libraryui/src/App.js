import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './App.css';
import RootLayout from './RootLayout';
import AddBook from './Components/AddBook';
import Home from './Components/Home';
import AddMember from './Components/AddMember';
import UpdateBook from './Components/UpdateBook';
import UpdateMember from './Components/UpdateMember';
import IssueBook from './Components/IssueBook';
import ReturnBook from './Components/ReturnBook';
import GetBooks from './Components/GetBooks';
import GetMembers from './Components/GetMembers';
import GetIssuedRecords from './Components/GetIssuedRecords';

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
        {
          path:"/addBook",
          element:<AddBook />,
        },
        {
          path:"/addMember",
          element:<AddMember />,
        },
         {
          path:"/updateBook",
          element:<UpdateBook />
        },
         {
          path:"/updateMember",
          element:<UpdateMember />
        },
         {
          path:"/issueBook",
          element:<IssueBook />
        },
         {
          path:"/returnBook",
          element:<ReturnBook />
        },
        {
          path:"/getBooks",
          element:<GetBooks />
        },
        {
          path:"/getMembers",
          element:<GetMembers />
        },
         {
          path:"/getIssuedRecords",
          element:<GetIssuedRecords />
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
