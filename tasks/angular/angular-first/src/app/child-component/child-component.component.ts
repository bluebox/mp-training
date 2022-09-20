import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-child-component',
  templateUrl: './child-component.component.html',
  styleUrls: ['./child-component.component.css']
})
export class ChildComponentComponent implements OnInit{
  @Input() item = "";

  @Output() outputItem = new EventEmitter();

  emitter(){
    this.outputItem.emit('emitter function')
    
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
  flag : boolean = true;
  onclick() {
    this.flag = false;
  }
}
