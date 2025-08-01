import React, { Component, createContext,useContext } from 'react'

export default class ClassBasedExample extends Component {
  constructor(props){
    super(props)
    this.state={count:0};
  }
  increment=()=>{
     this.setState(prevState => ({
      count: prevState.count + 1
    }));
  }
  decrement = () => {
    this.setState(prevState => ({
      count: prevState.count - 1
    }));
  };
  render() {
    return (
      <div>
        <h1>{this.props.value}</h1>
        <h1>{this.state.count}</h1>
        <button onClick={this.increment}>increment</button>
        <button onClick={this.decrement}>decrement</button>
      </div>
    )
  }
}
