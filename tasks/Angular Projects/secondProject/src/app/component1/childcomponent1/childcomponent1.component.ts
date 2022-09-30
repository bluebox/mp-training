import { Component,Input,Output, OnInit, OnChanges, EventEmitter, ChangeDetectionStrategy, SimpleChanges, OnDestroy } from '@angular/core';
import { Roomlist } from '../component1';

@Component({
  selector: 'app-childcomponent1',
  templateUrl: './childcomponent1.component.html',
  styleUrls: ['./childcomponent1.component.scss'],
  changeDetection : ChangeDetectionStrategy.OnPush
})
export class Childcomponent1Component implements OnInit, OnDestroy {
  @Input() rooms1: Roomlist[]=[];
  @Input() title:string='';
  @Output() selectedRoom = new EventEmitter<Roomlist>();
  constructor() { }
  
 ngOnChanges(changes: SimpleChanges): void {
   console.log(changes)
   if(changes['title']){
    this.title=changes['title'].currentValue;
   }
 }
  ngOnInit():void {
  }
  ngOnChange(){
    console.log("bvhuibuhj")
  }
  selectRoom(rooms: Roomlist){
    this.selectedRoom.emit(rooms);
  }
  ngOnDestroy(){
    console.log('onDestroy is called')
  }
}
