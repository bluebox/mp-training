import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-food',
  templateUrl: './add-food.component.html',
  styleUrls: ['./add-food.component.css']
})
export class AddFoodComponent implements OnInit {

  checked = false;
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
    is_available :[''],
    }
  );

 
  
  onSubmit(){
    console.log(this.foodForm.value)
    {
      if (this.foodForm.valid) {
        console.log('form submitted');
      } else {
        console.log(' notttt form submitted');
      }
      
      this.foodForm.reset()
    }
  }
}
