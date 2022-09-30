import { ChangeDetectionStrategy } from '@angular/compiler';
import { Component, OnInit, DoCheck, OnChanges, ViewChild, AfterViewChecked, AfterViewInit, ViewChildren, QueryList, SkipSelf } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { component1, Roomlist } from './component1';
import { Componenet1Service } from './services/componenet1.service';

@Component({
  selector: 'app-component1',
  templateUrl: './component1.component.html',
  styleUrls: ['./component1.component.scss'],
  
})
export class Component1Component implements OnInit, AfterViewInit, DoCheck,AfterViewChecked{
  title: string = 'shyam';
 roomlist: Roomlist []=[];
  @ViewChild(HeaderComponent, {static:true})  headerComponent!: HeaderComponent;
  @ViewChildren(HeaderComponent)  headeChildrenComponent!: QueryList<HeaderComponent>;
  constructor(@SkipSelf() private roomservice:Componenet1Service) { }
  ngAfterViewChecked(): void {
   
  }

   ngOnChange(){
  console.log('on change has occcured')
   }
   ngDoCheck(){ 
    console.log('on check is  has occcured')
     }
    
selectedRoom!:Roomlist;
  ngOnInit() {
   this.roomlist=this.roomservice.getComponent1();
 
  }
  ngAfterViewInit() {
    
    // console.log(this.headerComponent);
    this.headerComponent.title='rooms';
    console.log(this.headeChildrenComponent.last.title='last title');
    // this.headeChildrenComponent.get(0)
  }
  name='shyam';
  age =20;
  bool=false
  fun1(){
    this.bool=!this.bool
    this.title='This is changed the basics';
    
  }
  room:component1={
    totalRooms:30,
    availableRooms:20,
    bookedRooms:6
  }
  selectRoom(room:Roomlist){
    console.log(room)
    this.selectedRoom=room;
  }
addRoom(){
  const room:Roomlist={
    roomNumber:105,
    roomType:'dwdd',
    amenities:'kjnnkfg',
    price:5070,
    photos:'',
    checkinTime:new Date('12-Nov-2022'),
    chectOutTime:new Date('22-Nov-2022'),

  }


  // this.roomlist.push(room)
  this.roomlist=[...this.roomlist, room]
}

}
