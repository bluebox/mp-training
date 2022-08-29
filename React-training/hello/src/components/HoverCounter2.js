import React, { Component } from 'react'

export class HoverCounter2 extends Component {
    // constructor(props) {
    //   super(props)
    
    //   this.state = {
    //      count:0
    //   }
    // }
    // handleHover = () => {
    //     this.setState(prevState => {
    //         return ({count: prevState.count +1})
    //     })
    // }
  render() {
    const {count, handleClick} = this.props
    return (
      <div>
        <button onMouseOver={handleClick}>Hoverd {count} times</button>
      </div>
    )
  }
}

export default HoverCounter2