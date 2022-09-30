import { HttpClientModule } from '@angular/common/http';
import { AfterViewChecked, AfterViewInit, Component, DoCheck, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { first } from 'rxjs';
import { HeaderComponent } from '../header/header.component';
import { emp } from './component';
import { Component1Service } from './service/component1.service';

@Component({
  selector: 'app-component1',
  templateUrl: './component1.component.html',
  styleUrls: ['./component1.component.scss']
})
export class Component1Component implements OnInit , DoCheck, AfterViewInit, AfterViewChecked {
    name='shyam';
    age=23;
    child=false;
    bool=true;
    title:string ='Shyam';
    @ViewChild(HeaderComponent) headerComponent!:HeaderComponent;
    @ViewChildren(HeaderComponent) headerChildrenComponent!:QueryList<HeaderComponent>;
    myemp:emp[]=[];
    function1() {
      this.child=!  this.child
    }
  
  constructor(private empservices : Component1Service  , private http : HttpClientModule) { 
    console.log("this is consructions")
  }
  ngAfterViewChecked(): void {
    console.log(this.headerComponent.title='raju badd');


  }
  ngAfterViewInit(): void {
    console.log(this.headerComponent.title='raju bai');  // it is works in dev mode 
    console.log(this.headerChildrenComponent.last.title='shyam bai'); 
  }
  ngDoCheck(): void {
   console.log("this is ngDoCheck")
  }
  ngOnInit(): void {
    console.log("this is the ngOnInt")
    console.log(this.empservices.getEmpList());
    this.myemp=this.empservices.getEmpList();
    

    

  }
  
   
  

  myemp1!:emp;
  fun1(i:emp){
    this.bool=!  this.bool  
    this.myemp1=i

  }
}
