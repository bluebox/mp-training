import React, { Component } from 'react'
import UpdatedComponent from './WithCounter'
class HoverCounter extends Component {
   
  render() {
    const{handleClick,count,name} = this.props
    return (
      <div>
        <h2 onMouseOver={handleClick}>{name} Hovered {count} times</h2>
      </div>
    )
  }
}

export default UpdatedComponent(HoverCounter)