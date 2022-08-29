import React, { Component } from 'react'
import MemoComp from './MemoComp'
import PureComponents from './PureComponents'
import RegularComp from './RegularComp'

 class ParentComp extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         name: 'irfan'
      }
    }
    componentDidMount(){
        setInterval(()=>{
            this.setState({
                name:'irfan'
            })
        },
        2000)
    }
  render() {
    console.log("******************Parent component render*****************")
    return (
      <div>Parent Component
        <MemoComp name={this.state.name} />
        {/* <RegularComp name={this.state.name}/>
        <PureComponents name={this.state.name}/> */}
      </div>
    )
  }
}

export default ParentComp