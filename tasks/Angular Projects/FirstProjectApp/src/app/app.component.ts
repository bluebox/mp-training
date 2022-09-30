import { AfterViewInit, Component, ElementRef, Inject, ViewChild, ViewContainerRef} from '@angular/core';
import { Component1Component } from './component1/component1.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  // template: `<h1>Hello every one</h1>
  // <p>Shyam is Awesome</p>`,
  styleUrls: ['./app.component.scss']
  // styles:[`h1{ color : green; }`]
})
export class AppComponent implements AfterViewInit {
  title = 'FirstProjectApp';

constructor(){

}
@ViewChild('user', { read: ViewContainerRef}) vcr!: ViewContainerRef;
ngAfterViewInit() { 
  const componentRef = this.vcr.createComponent(Component1Component);
}
@ViewChild('name', { static:true}) name!: ElementRef;
ngOnInit() { 
 console.log( this.name.nativeElement.innerText='hi diude shyam');
 
}
}
