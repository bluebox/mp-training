import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  name: string=''
  value: any

  // echnologyUsed = new FormControl('Java');

  constructor() {}
  userGroup : FormGroup = new FormGroup(
    {
      firstName: new FormControl(''),
      lastName: new FormControl('')
    }
  )


  ngOnInit(): void {
  }
  submit(){
    console.log("salkjc")
    console.log(this.userGroup.value)
  }

    // setValue(data: any){
  //   // this.TechnologyUsed=(data)
  // }
}
