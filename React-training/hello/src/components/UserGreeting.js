import React, { Component } from 'react'

class UserGreeting extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         isLoggedIn: true
      }
    }
  render() {
    // return this.state.isLoggedIn && <div>Welcome Irfan</div>
    return(
        this.state.isLoggedIn ?(
        <div> welcome Irfan</div>
        )
        :
        (
        <div>welcome Guest</div>
        )
    )
    // let message
    // if(this.state.message){
    //     message = <div>Welcome Irfan</div>
    // }else{
    //     message = <div>Welcome Guest</div>
    // }
    // return(
    //     <div>{message}</div>
    // )
    // if(this.state.isLoggedIn){
    //   return  <div>
    //         Welcome Irfan
    //     </div>
    // } else {
    //   return  <div>Welcome Guest</div>
    // }
    // return (
    //     <>
    //     <div>Welcome Irfan</div>
    //     <div>Welcome Guest</div>
    //     </>
      
    // )
  }
}

export default UserGreeting