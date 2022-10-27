import React, { Component } from "react";

export default class Message extends Component {

  constructor(){
    super()
    this.state = {
      message : "Welcome Irfan"
    }
  }

  changeMessage(){
    this.setState({
      message : "Thank you"
    })
  }
  render() {
    return (
      <>
      <h1>{this.state.message}</h1>
      <button onClick={()=>{
        this.changeMessage()
      }}>Change Text</button>
      </>
    )
  }
}
