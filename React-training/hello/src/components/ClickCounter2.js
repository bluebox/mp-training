import React, { Component } from 'react'

class ClickCounter2 extends Component {
    // constructor(props) {
    //   super(props)
    
    //   this.state = {
    //      count:0
    //   }
    // }
    // handleClick=()=>{
    //     this.setState(prevState => {
    //        return {count: prevState.count+1} 
    //     })
    // }
  render() {
    const {count,handleClick} = this.props
    return (
      <div>
        <button onClick={handleClick}>Clicked {count} times</button>
      </div>
    )
  }
}

export default ClickCounter2