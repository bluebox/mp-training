import React, { Component } from 'react'
import UpdatedComponent from './WithCounter'
class ClickCounter extends Component {
 
  render() {
    const{handleClick,count,name} = this.props
    return (
      <div>
       
        <button onClick={handleClick}>{name} Clicked {count} times</button>
      </div>
    )
  }
}

export default UpdatedComponent(ClickCounter)