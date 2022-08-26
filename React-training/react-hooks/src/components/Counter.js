import React, { Component } from 'react'

export class Counter extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         count:0
      }
    }
    handleClick =() =>{
        this.setState({
            count:this.state.count +1
        })
    }
  render() {
    const {count} = this.state
    return (
      <div>
        <button onClick={this.handleClick}>Clicked {count} times</button>
      </div>
    )
  }
}

export default Counter