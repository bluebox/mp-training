import { Component } from '@angular/core';
import { NewService } from './new.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  // declaring arrays in typescript class
  array: string[] = ["a", "b", "c"]
  
  constructor (public inter:NewService) {
    // using arrays in constructor using general for loop
    for (let i = 0; i < this.array.length; i ++) {
      console.log(this.array[i])
    }

    // using arrays in constructor using for...of loop

    for (var char of this.array) {
      console.log(char)
    }

    // using arrays in constructor using for...in loop

    for (var charIndex in this.array) {
      console.log(this.array[charIndex])
    }

    // declaring enums in typescript class
    // enums can be of two types
    // one is integer enum another is string enum
    // enums are generally used to store auto-increment values

    // if no value is assigned to enum variable, by default it takes 0
    enum values {
      // default 0
      value1,
      value2,
      value3,
    }

    console.log(values.value1)
    enum anotherEnum {
      value1,
      value2 = 2,
      // auto-increments from 2
      value3,
    }
    console.log(anotherEnum.value1)

    // enums can be inistialized by constants or computed member
    enum thirdEnum {
      value1,
      value2 = 2,
      value3 = "sai".length,
    }
    console.log(thirdEnum.value3)

    // string enums
    enum stringEnum {
      firstName = 'sai',
      lastName = "ram",
    }
    console.log(stringEnum.firstName)

    // using interfaces defined in service.ts
    console.log(this.inter.firstInterface.name);
    console.log(this.inter.firstInterface.age);
  }
}
