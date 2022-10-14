import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { FoodService } from 'src/app/services/food.service';

@Component({
  selector: 'app-add-food',
  templateUrl: './add-food.component.html',
  styleUrls: ['./add-food.component.css']
})
export class AddFoodComponent implements OnInit {

  checked = false
  constructor(private fb:FormBuilder, private food:FoodService) { }

  ngOnInit(): void {
  }


  foodForm=this.fb.group(
    {
    food_id:['', Validators.required],
    restaurant_id :['', Validators.required],
    food_name:['', Validators.required],
    food_price :['', Validators.required],
    food_desc :['', Validators.required],
    food_photo :['', Validators.required],
    is_available :[false],
    }
  );

 
  
  onSubmit(){
    console.log(this.foodForm.value)
    
    if (this.foodForm.valid) {
      this.food.postFood(this.foodForm.value).subscribe((data)=>{
        console.log(data)
      })
      console.log('form submitted');
      console.log(this.foodForm.value)
    } else {
      console.log('notttt form submitted');
    }
    
    this.foodForm.reset()
  }
  close()
  {
    this.ngOnInit()
    window.location.reload()
  }
    
  }

