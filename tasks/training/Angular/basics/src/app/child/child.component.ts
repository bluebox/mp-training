import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.css']
})
export class ChildComponent implements OnInit {
  @Input() passingData: string[] =[]
  @Output() outEvent = new EventEmitter()

  show: boolean =true

  constructor() {
    console.log("In consTRUCTOR")
   }

  ngOnInit(): void {
    console.log("in ngOnInIt()")
  }
  ngOnChanges(){
    console.log("ngOnChange()")
  }
  ngDoCheck(){
    console.log("doCheck()")
  }
  ngDestroy(){
    console.log("destroyed()")
  }

  onClick(){
    this.outEvent.emit()

    console.log("in onClick()" )
  }

  destroy(){
    this.show= false
  }

}
