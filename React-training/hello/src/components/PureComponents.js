import React, { PureComponent } from 'react'

 class PureComponents extends PureComponent {
  render() {
    console.log("Pure component render")
    return (
      <div>PureComponents {this.props.name}</div>
    )
  }
}

export default PureComponents