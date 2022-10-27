import React, { Component } from 'react'

export class Form extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         username: '',
         comments: '',
         topic:'react'
      }
    }
    handleUsernameChange = (event) => {
        console.log(event.target.value)
        this.setState({
            username: event.target.value
        })
    }

    handleCommentChange=(event)=>{
        this.setState({
            comments:event.target.value
        })
        console.log(this.state.comments)
    }
    handleTopicChange=(event)=>{
        this.setState({
            topic:event.target.value
        })
        console.log(this.state)
    }
    handleSubmit=(event)=>{
        alert(`${this.state.username} ${this.state.comments} ${this.state.topic}`)
        event.preventDefault() // for avoid loosing data
        console.log(this.state)
    }
  render() {
    return (
        <form onSubmit={this.handleSubmit}>
      <div>
        <label>Username</label>
        <input type='text' value={this.state.username} onChange={this.handleUsernameChange}/>
      </div>
      <div>
        <label>Comments</label>
        <textarea value={this.state.comments} onChange={this.handleCommentChange}></textarea>
      </div>
      <div>
        <label>Topic</label>
       <select value={this.state.topic} onChange={this.handleTopicChange}>
        <option value='react'>React</option>
        <option value='angular'>Angular</option>
        <option value='vue'>Vue</option>
       </select>
      </div>
      <button type='submit'>Submit</button>
      </form>
    )
  }
}

export default Form