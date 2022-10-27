import React,{useReducer} from 'react'

const initialState = {
    firstCounter:0,
    secondCounter:10
}
const reducer = (state, action)=>{
    switch(action.type){
        case 'increment1':
            return {...state, firstCounter: state.firstCounter + action.value}
        case 'decrement1':
            return {...state, firstCounter: state.firstCounter - action.value}
        case 'increment2':
            return {...state, secondCounter: state.secondCounter + action.value}
        case 'decrement2':
            return {...state, secondCounter: state.secondCounter - action.value}
        case 'reset':
            return initialState
        default:
            return state
    }
}
function CounterTwo() {
    const [count, dispatch] = useReducer(reducer, initialState)
  return (
    <>
    <div>
    <div>Counter - {count.firstCounter}</div>
    <button onClick={() => dispatch({type: 'increment1', value:1})}>Increment</button>
    <button onClick={() => dispatch({type: 'decrement1', value:1})}>Decrement</button>
    <button onClick={() => dispatch({type: 'increment1', value:5})}>Increment5</button>
    <button onClick={() => dispatch({type: 'decrement1', value:5})}>Decrement5</button>
    </div>
    <div>
    <div>Counter - {count.secondCounter}</div>
    <button onClick={() => dispatch({type: 'increment2', value:1})}>Increment</button>
    <button onClick={() => dispatch({type: 'decrement2', value:1})}>Decrement</button>
    <button onClick={() => dispatch({type: 'increment2', value:5})}>Increment5</button>
    <button onClick={() => dispatch({type: 'decrement2', value:5})}>Decrement5</button>
    </div>
    <button onClick={() => dispatch({type: 'reset'})}>Reset</button>
    </>
  )
}

export default CounterTwo