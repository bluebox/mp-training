import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-food',
  templateUrl: './add-food.component.html',
  styleUrls: ['./add-food.component.css']
})
export class AddFoodComponent implements OnInit {

  constructor(private fb:FormBuilder) { }

  ngOnInit(): void {
  }


  foodForm=this.fb.group(
    {
    food_id:['', Validators.required],
    food_name:['', Validators.required],
    food_price :['', Validators.required],
    food_desc :['', Validators.required],
    food_photo :['', Validators.required],
    is_available :['', Validators.required],
    }
  )

  onSubmit(){}
}
