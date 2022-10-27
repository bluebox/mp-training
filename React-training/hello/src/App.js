
// import { Suspense } from 'react';
import './App.css';
import ClickCounter from './components/ClickCounter';
import ClickCounter2 from './components/ClickCounter2';
import ComponentC from './components/ComponentC';
import Count from './components/Count';
import ErrorBoundary from './components/ErrorBoundary';
import FocusInput from './components/FocusInput';
import FragmentDemo from './components/FragmentDemo';
import FrParentInput from './components/FrParentInput';
import Hero from './components/Hero';
import HoverCounter from './components/HoverCounter';
import HoverCounter2 from './components/HoverCounter2';
import ParentComp from './components/ParentComp';
import Portal from './components/Portal';
import PureComponents from './components/PureComponents';
import RefsDemo from './components/RefsDemo';
import Table from './components/Table';
import { UserProvider } from './components/UseContext';
// import Form from './components/Form';
// import LifeCycleA from './components/LifeCycleA';
// import InLine from './components/InLine';
// import EventBind from './components/EventBind';
// import NameList from './components/NameList';
// import ParentComponent from './components/ParentComponent';
// import UserGreeting from './components/UserGreeting';
// import ClassClick from './components/ClassClick';
// import FunctionClick from './components/FunctionClick';
// import Counter from './components/Counter';
// import Hello from './components/Hello';
// import Greet from './components/Greet';
// import Welcome from './components/Welcome';
// import Message from './components/Message';
// import Stylesheet from './components/Stylesheet';
// import './appStyles.css';
// import styles from './appStyles.module.css';
function App() {
  return (
    <div className="App">
      <UserProvider value="Irfan">
      <ComponentC />
      </UserProvider>
      
      {/* <Count
      render={(count,handleClick)=>(
        <ClickCounter2 count={count} handleClick={handleClick} />
  )} />
        <Count
      render={(count,handleClick)=>(
        <HoverCounter2 count={count} handleClick={handleClick} />
  )} /> */}
      {/* <ClickCounter2 />
      <HoverCounter2 /> */}
      {/* <HoverCounter />
      <ClickCounter /> */}
      {/* <ErrorBoundary>
      <Hero heroName="Batman"/>
      </ErrorBoundary>
      <ErrorBoundary>
      <Hero heroName="superman"/>
      </ErrorBoundary>
      <ErrorBoundary>
      <Hero heroName="Joker"/>
      </ErrorBoundary> */}
      {/* <Portal /> */}
      {/* <FrParentInput /> */}
      {/* <FocusInput /> */}
      {/* <RefsDemo /> */}
      {/* <ParentComp /> */}
      {/* <Table /> */}
      {/* <FragmentDemo /> */}
      {/* <LifeCycleA /> */}
      {/* <Form /> */}
      {/* module.css doesn't apply to child component */}
      {/* <h1 className='error'>Error</h1> 
      <h1 className={styles.success}>Success</h1>
      <InLine /> */}
      {/* <Stylesheet primary={true}/> */}
      {/* <NameList /> */}
      {/* <UserGreeting /> */}
      {/* <ParentComponent/> */}
      {/* <EventBind /> */}
      {/* <FunctionClick />
      <ClassClick /> */}
      {/* <Greet name="irfan" heroname="captain america"/> */}
      {/* <Counter /> */}
      {/* <Message /> */}
     {/* <Greet name="irfan" heroname="captain america">
      This is a children prop
     </Greet>
     <Greet name="ayan" heroname="ironman">
      <button>Action</button>
     </Greet>
     <Greet name="john" heroname="thor"/>
     <Welcome name="john" heroname="ironman"/>
     <Welcome name="Irfan" heroname="thor" />
     <Welcome name="Ayan" heroname="captain america"/> */}
     {/* <Hello/> */}
    </div>
  );
}

export default App;
