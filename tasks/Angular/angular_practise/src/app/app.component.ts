import { Component, OnChanges, OnInit, SimpleChanges, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-root',
 // templateUrl: '<app-server></app-server><app-server></app-servers>',
  templateUrl:'./app.component.html', 
  styleUrls: ['./app.component.css']
}) 
export class AppComponent implements OnInit, OnChanges, OnDestroy{
  name: string='helloo';

  // setValue(){
  //   this.name='venky';
  // }
  childdata!: string;
  parentMethod(data:string){
    this.childdata=data
  }
  ngOnInit(): void {
    console.log("init");
  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log("onchange");
  }
  ngOnDestroy():void{
    console.log("destroy");
  }


} 
