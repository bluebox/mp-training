import { Component } from '@angular/core';
import { Vehicle } from '../interfaces/vehicle'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements Vehicle{
  title = 'Tasks of angular';
  price: number = 2;
  fuel: string = 'petrol';
  wheels:number = 4;
  constructor(){    // declaring arrays with single and multi types
    var nums : number[] = [1,2,3,4,5]

    for(var i=0;i<nums.length;i++){
      console.log(nums[i])
    }

    var multi : (string | number)[] = ['hyderabad',1,'mumbai','bangalore',3]

    for(var j in multi){
      console.log(multi[j])
    }

    var cities : Array<string> = ['hyderabad','bangalore','pune','chennai','mumbai']

    for(var i=0;i<cities.length;i++){
      console.log(cities[i])
    }
  }
}
