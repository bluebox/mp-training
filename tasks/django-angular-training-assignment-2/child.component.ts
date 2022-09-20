import { PropertyRead } from '@angular/compiler';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { OnDestroy } from '@angular/core';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.css']
})
export class ChildComponent implements OnDestroy {

  // component level variable
  childName:String = "angeerasa"

  // output decorator is used to send component level variables to parent component
  // this variables are on;y sent when any event occurs
  // to catch that 
  @Output() triggerEvent = new EventEmitter()

  // input decorator is used to access parent level variables
  @Input() inputs:String | undefined

  // this is the first method to be called once the object to this class is created
  // object is created when this component is used
  constructor() {
    console.log(this.inputs)
  }

  // this method is called when component is destroyed
  // this is used to perform clean up after this component is deleted
  ngOnDestroy() {
    console.log("this is destroy")
  }

  // this hook is called when any changes are made to the imported variables
  // imported variables in the sense, variables that are binded through one way or two way
  // this is the next hook that runs after constructor
  ngOnChanges() {
    console.log("this is onchange")
  }

  // emits component level variable to parent component
  eventTrigger() {
    this.triggerEvent.emit(this.childName)
  }

  // this is used to initialize imported variables
  ngOnInit(): void {
    console.log(this.inputs)
    console.log("init method runned");
  }

}
