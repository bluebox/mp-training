import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Restaurant } from 'src/app/interfaces/restaurant';
import { RestaurantService } from 'src/app/services/restaurant.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  restaurant:Restaurant[]=[];

  constructor(private res:RestaurantService) { }

  ngOnInit(): void {
    this.getData()
   
  }

  getData()
  {
    this.res.getRes().subscribe(data=>{
      console.log(data);this.restaurant=data
    ;console.log(this.restaurant)})


  }
  sigininForm=new FormGroup(
    {
      restaurant_id:new FormControl('', Validators.required),
      restaurant_name:new FormControl('', Validators.required),
      restaurant_address1:new FormControl('', Validators.required),
      restaurant_address2:new FormControl(''),
      restaurant_city:new FormControl('', Validators.required),
      restaurant_state:new FormControl('', Validators.required),
      restaurant_code:new FormControl('', Validators.required),
      restaurant_username:new FormControl('', Validators.required),
      restaurant_password:new FormControl('', Validators.required),
      restaurant_phn:new FormControl('', Validators.required),

      restaurant_email:new FormControl('', Validators.required),
      
     
      
      open_timing:new FormControl('', Validators.required),
      close_timing:new FormControl('', Validators.required),
      
      // userPass2:new FormControl('', Validators.required)
    }
  )


  onSubmit()
  {
    if (this.sigininForm.valid) {
      this.res.postUser(this.sigininForm.value).subscribe((data)=>{
        console.log(data)
      })
      console.log('form submitted');
      console.log(this.sigininForm.value)
    } else {
      console.log(' notttt form submitted');
    }
    
    this.sigininForm.reset()
  }
}
