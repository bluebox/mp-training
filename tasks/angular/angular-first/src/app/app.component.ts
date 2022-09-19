import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-first';
  date : Date
  array : Array<string>
  // changeNum : number
  constructor(){
    this.date = new Date()
    this.array = ['red','yellow','blue']

  }
  firstMethod(){
    console.log(this.date)
  }
  colorChange(){
    let color = document.getElementsByClassName('box');
    // color.style.background-color = this.array[this.changeNum]
    // this.changeNum=this.changeNum+1
  }
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
