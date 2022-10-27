import React, { Component } from 'react'

class Counter extends Component {
    constructor(props){
        super(props)
        this.state = {
            count: 0
        }
    }

    increment(){
        // this.setState({
        //     count:this.state.count+1
        // },()=>{console.log('callback value',this.state.count)})
        // console.log(this.state.count) //called before state update from sync always place code inside callback function

        this.setState(prevState => ({
            count : prevState.count + 1
        }))
    }

    decrement(){
        // this.setState({
        //     count:this.state.count-1
        // },()=>{console.log('callback value',this.state.count)})
        // console.log(this.state.count) //called before state update from sync always place code inside callback function

        this.setState(prevState => ({
            count : prevState.count - 1
        }))
    }

    reset(){
        // this.setState({
        //     count:0
        // },()=>{console.log('callback value',this.state.count)})
        // console.log(this.state.count) //called before state update from sync always place code inside callback function

        this.setState(prevState => ({
            count : 0
        }))
    }

    incrementFive(){
        this.increment()
        this.increment()
        this.increment()
        this.increment()
        this.increment() // all set of code run in single go o/p +1
    }


  render() {
    return (
        <>
      <div>Count - {this.state.count}</div>
      <button onClick={()=>{
        this.increment()
      }}>Increment</button>
      <button onClick={()=>{
        this.decrement()
      }}>Decrement</button>
      <button onClick={()=>{
        this.reset()
      }}>Reset</button>
       <button onClick={()=>{
        this.incrementFive()
      }}>Increment Five</button>
      </>
    )
  }
}

export default Counter;