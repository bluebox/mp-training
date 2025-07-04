import ReactDOM from 'react-dom/client';
import { useState } from 'react';

const [Books,setBooks]=useState(
  bookId=0,
  title="",
  author="",
  category="",
  bookStatus="",
  availability=""
);
class Book extends React.Component{
  constructor(){
    bookId=this.bookId;
    title=this.title;
    author=this.author;
    category=this.category;
    bookStatus=this.bookStatus;
    availability=this.availability;
  }
}
const root=ReactDOM.createRoot(document.getElementById("root"));
root.render();
