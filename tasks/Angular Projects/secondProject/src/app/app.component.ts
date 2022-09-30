import { AfterViewInit, Component, ElementRef, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { Component1Component } from './component1/component1.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
 
  
  title = 'secondProject';
  name1='shyam';
  role='Admin';
  @ViewChild('user',{read:ViewContainerRef}) vcr!:ViewContainerRef; 
  ngOnInit(): void {
    console.log(this.name.nativeElement.innerText='nvjndinp');
    
  }
  // ngAfterViewInit(): void {
  //   const componentRef = this.vcr.createComponent(Component1Component)
  //   componentRef.instance.age=100;
  // }
  @ViewChild('name',{static:true}) name!:ElementRef;
}
