import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
class Book extends React.Component{
  constructor(bookId,title,author,category,status,availability){
    super();
    this.bookId=1;
    this.title="War of Apes";
    this.author="Michel Morgan";
    this.category={
    Adventure:5,
    Mystery:3,
    Fantasy:4,
    Romance:2
    };
  }
  render(){
    return (
      <div>
        <p>Book ID : {this.bookId}</p>
        <p>Title  : {this.title}</p>
        <p>Author : {this.author}</p>
        <p>Category :<br></br>
          {
            this.category.Adventure>0 && 
            <p>Adventure level : <input type="range" min={0} max={5} value={this.category.Adventure}></input></p>
          }
          {
            this.category.Mystery>0 && 
            <p>Mystery level : <input type="range" min={0} max={5} value={this.category.Mystery}></input></p>
          }
          {
            this.category.Fantasy>0 && 
            <p>Fantasy level : <input type="range" min={0} max={5} value={this.category.Fantasy}></input></p>
          }
          {
            this.category.Thriller>0 && 
            <p>Thriller level : <input type="text" min={0} max={5} value={this.category.Thriller}></input></p>
          }
          {
            this.category.Action>0 && 
            <p>Action level : <input type="range" min={0} max={5} value={this.category.Action}></input></p>
          }
          {
            this.category.Romance>0 && 
            <p>Romance level : <input type="range" min={0} max={5} value={this.category.Romance}></input></p>
          }
        </p>
      </div>
    )
  }
}
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<Book/>);
