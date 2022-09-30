import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.css']
})
export class ChildComponent implements OnInit {

  @Input() received:any='';
  @Output() toParent:EventEmitter<any>=new EventEmitter()

  arr:number[]=[1,2,3,4,5,6,7,8]
  condition:boolean=true;
  constructor() { }

  ngOnInit(): void {
    window.alert('window is loading')
  }
  ngOnDestroy(){
      window.alert('destroying it...')
  }
  ngDoCheck(){
    window.alert('checking it...')

  }
  ngOnContentView(){

    window.alert('contentView it...')
  }
  ngOnContentCheck(){

    window.alert('contentcheck it...')
  }

}
