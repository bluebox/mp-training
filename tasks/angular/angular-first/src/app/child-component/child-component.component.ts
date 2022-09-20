import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-child-component',
  templateUrl: './child-component.component.html',
  styleUrls: ['./child-component.component.css']
})
export class ChildComponentComponent implements OnInit{

  // event binding 
  @Input() item = "";


  //  property binding 
  @Output() outputItem = new EventEmitter();

  emitter(){
    let data = { name:'anil' , age: 25 }
    this.outputItem.emit(data)
    
  }
  // life cycles in angular 
  constructor() {

    console.log('constructor called');
    
   }

  ngOnInit(): void {
    console.log('nginit called');
    
  }

  ngOnChanges() : void{
    console.log('changed');
    
  }

  ngOnDestroy() : void{
    console.log('destroy');
    
  }

  ngOncheck(): void{
    console.log('checks called');
    
  }
  flag : boolean = true;
  onclick() {
    this.flag = false;
  }
}
