import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  
  @Input() parentData:string="";

  @Output() outputEvent=new EventEmitter()
  outPut(name:string)
  {
    this.outputEvent.emit(name)
  }
  constructor() { }

  ngOnInit(): void {
  }

}
