import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'tryangular';
  constructor(){

  }
  item: string[]=["hello ",'1','2','3'];
  reciver(str1:string){
    console.log(str1);
    
  }
}
