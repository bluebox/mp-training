import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.css']
})
export class ChildComponent implements OnInit {

  @Output() newValue:EventEmitter<any> = new EventEmitter<any> ();

  @Input() data:any='';
  constructor() { }

  ngOnInit(): void {

  }

  send():void{

    this.newValue.emit({'name':'ajay','rollno':'19011F0015'});
  }

}
