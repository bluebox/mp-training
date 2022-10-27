import React from 'react';
import './App.css';
import ClassCounterOne from './components/ClassCounterOne';
import ClassMouse from './components/ClassMouse';
import ComponentC from './components/ComponentC';
import Counter from './components/Counter';
import DataFetching from './components/DataFetching';
import EffectMouse from './components/EffectMouse';
import HookCounter from './components/HookCounter';
import HookCounterFour from './components/HookCounterFour';
import HookCounterThree from './components/HookCounterThree';
import HookCounterTwo from './components/HookCounterTwo';
import HookEffectOne from './components/HookEffectOne';
import IntervalClassCounter from './components/IntervalClassCounter';
import IntervalHookCounter from './components/IntervalHookCounter';
import MouseContainer from './components/MouseContainer';
import CounterOne from './components/reducerHook/CounterOne';
import CounterTwo from './components/reducerHook/CounterTwo';

export const UserContext = React.createContext()
export const ChannelContext = React.createContext()
function App() {
  return (
    <div className="App">
      <CounterTwo />
      {/* < UserContext.Provider value={'Irfan'}>
        <ChannelContext.Provider value={'Ayan'}>
      <ComponentC />
      </ChannelContext.Provider>
      </UserContext.Provider> */}
      {/* <DataFetching /> */}
      {/* <IntervalHookCounter /> */}
      {/* <IntervalClassCounter /> */}
      {/* <MouseContainer /> */}
      {/* <EffectMouse /> */}
      {/* <ClassMouse /> */}
      {/* <HookEffectOne /> */}
      {/* <ClassCounterOne /> */}
      {/* <HookCounterFour /> */}
      {/* <HookCounterThree/> */}
      {/* <HookCounterTwo /> */}
      {/* <HookCounter /> */}
      {/* <Counter /> */}
    </div>
  );
}

export default App;
