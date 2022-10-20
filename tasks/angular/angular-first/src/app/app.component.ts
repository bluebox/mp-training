import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-first';
  date : Date
  array : Array<string>
  item : string = 'food'
  temp= false;
  constructor(){
    this.date = new Date()
    this.array = ['red','yellow','blue']

  }

  emitter( data : object) : void {
    console.warn(data);
    
  }
  firstMethod(){
    console.log(this.date)
  }
  colorChange(){
    let color = document.getElementsByClassName('box');

  }

  outputitem(): void{
    console.log('output');
    
  }
  tuple : [number | string,string,boolean] = ["string or number",'name',false];

  red = true;
  green = false;
  blue = false;
  status = "red";

  enableDisableRule() {
    if (this.red ){
      this.red =false
      this.green = true
      this.status = 'green';
    }
    else if (this.green){
      this.green = false;
      this.blue = true;
      this.status = 'blue';
    }
    else if (this.blue){
      this.blue = false
      this.red = true
      this.status = 'red'
    }
  }

}
