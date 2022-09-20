import { Component } from '@angular/core';
import { Person } from './Person';



enum Months {
  jan=1,
  feb,
  march,
  apr
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'basics';
  date: Date
  flag: boolean
  arr: string[] // normal method of declaring array
  arr2: Array<any> //generic type
  ena: Months // enum


  //20/09/22
  sendingData: string[]= ["Angular", "React", "Django", "Spring"]



  customer : Person ={  //interface
    name:"Dhanush",
    age: 25
  }


  constructor(){
    this.date = new Date()
    this.flag= true
    this.arr=["tom "," jerry"," doremon "]
    this.arr2= [1,10,"Rama"," Bheem"]
    console.log("SUM="+ this.add(10,30))
    this.ena=Months.march
    console.log("enum" +this.ena) // printing enum value
    //console.log.(Months.apr)

  }

  getTime(){
    // date: Date = new Date();

    if(this.flag){
      this.flag=false
      console.log("Date = " + this.date);
    }
    else{
      this.flag=true
    }
  }
  reloadCurrentPage() {
    window.location.reload();
   }

   add(var1:number, var2:number): number{

      return var1+var2

   }

   //20/09/2022
   outDataRcv(data : string){
    console.log(data)
  }
}





