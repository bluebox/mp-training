import React, { Component } from 'react'
import ChildComponent from './ChildComponent'

class ParentComponent extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         parentName: 'parent'
      }

      this.greetParent = this.greetParent.bind(this)
    }
    greetParent(){
        alert(`Hello ${this.state.parentName}`)
    }
  render() {
    return (
      <div>
        <ChildComponent greetHandler = {this.greetParent}/>
        {/* <button onClick={()=>this.greetParent()}>Click</button> */}
        </div>
    )
  }
}

export default ParentComponent