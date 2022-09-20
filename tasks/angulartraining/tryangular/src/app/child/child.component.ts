import { isNgTemplate } from '@angular/compiler';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.css']
})
export class ChildComponent implements OnInit {
  @Input() itemfromparent: string[]=[];
  @Output() emit = new EventEmitter;

  fun1(str:string){
    this.emit.emit(str)
  }
  
  constructor() { }

  ngOnInit(): void {
  }

}
