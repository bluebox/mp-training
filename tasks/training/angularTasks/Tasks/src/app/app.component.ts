import { Component } from '@angular/core';
import { Vehicle } from '../interfaces/vehicle'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  constructor(){

  //creating arrays
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

    var car1:Car = new Car(25000,'petrol',4);
    console.log(car1)


    console.log(week)
  }
}
 // implementing Vehicle interface
class Car implements Vehicle{
    price: number
    fuel: string
    wheels:number
  constructor( price: number, fuel: string, wheels:number){
    this.price=price;
    this.fuel=fuel;
    this.wheels=wheels
  }
}
 // creating enumaration
enum week{
sunday,monday,tuesday,wednesday,thursday,friday,saturday
}
