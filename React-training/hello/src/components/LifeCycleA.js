import React, { Component } from 'react'
import LifeCycleB from './LifeCycleB'

class LifeCycleA extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         name: 'irfan'
      }
      console.log('LifeCycleA constructor')
    }

    static getDerivedStateFromProps(props, state){
        console.log('LifeCycleA getDerivedStateFromProp')
        return null
    }
    componentDidMount(){
        console.log('LifeCycleA componentDidMount')
    }

    shouldComponentUpdate(){
        console.log('LifeCycleA shouldComponentUpdate')
        return true
    }

    getSnapshotBeforeUpdate(){
        console.log('LifeCycleA getSnapshotBeforeUpdate')
        return null
    }
    componentDidUpdate(){
        console.log('LifeCycleA componentDidUpdate')
    }
    changeState=()=>{
        this.setState({
            name:'ayan'
        })
    }
  render() {
    console.log('LifeCycleA render')
    return (
<div>
    
      <div>
        LifeCycleA
      </div>
      <button onClick={this.changeState}>Change state</button>
      <LifeCycleB />
      </div>
    )
  }
}

export default LifeCycleA