import React, { Component } from 'react'
import ComponentF from './ComponentF'
import UserContext from './UseContext'

class ComponentE extends Component {
  render() {
    return (
      <>
      <div>
        ComponentE Context {this.context}
      </div>
      <ComponentF />
      </>
    )
  }
}
ComponentE.contextType = UserContext

export default ComponentE