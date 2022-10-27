import React, { Component } from 'react'

export class ClassMouse extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         x:0,
         y:0
      }
    }
    logMouseposition = e =>{
        this.setState({x:e.clientX, y: e.clientY})
    }
    componentDidMount(){
        console.log("hover")
        window.addEventListener('mousemove',this.logMouseposition)
    }
    // componentDidUpdate(prevProps, prevState){
    //     console.log("hover")
    //     window.addEventListener('mousemove',this.logMouseposition)
    // }
  render() {
    return (
      <div>
        x-{this.state.x} Y-{this.state.y}
      </div>
    )
  }
}

export default ClassMouse