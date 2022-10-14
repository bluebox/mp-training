import { Component, OnInit } from '@angular/core';
import { GeneralService } from 'src/app/general.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-customerregister',
  templateUrl: './customerregister.component.html',
  styleUrls: ['./customerregister.component.css']
})
export class CustomerregisterComponent implements OnInit {

  customer_group!: FormGroup
  error: string=''
  submit !: boolean
  constructor(private service : GeneralService,private route:Router) { }


  ngOnInit(): void {
    this.customer_group = new FormGroup({
      name: new FormControl('', [Validators.pattern('^[a-zA-Z]{4,100}$'), Validators.required]),
      contact_no : new  FormControl('', Validators.required),
      email : new FormControl('', [Validators.email, Validators.required]),
      dl_no : new FormControl('', Validators.required),
      password : new FormControl('', [Validators.pattern('^[:;,\-@0-9a-zA-Zâéè.\s]{8,20}$'), Validators.required]),
      password2 : new FormControl('', [Validators.pattern('^[:;,\-@0-9a-zA-Zâéè.\s]{8,20}$'), Validators.required]),
      address : new FormControl('', [Validators.maxLength(100), Validators.required] )
    })
    console.log()
  }

  get formData() {
    return this.customer_group.controls
  }
  register_customer(){

    let userObj = this.customer_group.value
    if(userObj.password == userObj.password2){
      delete userObj.password2
      // this.service.registerCustomer(userObj).subscribe(data=> {console.log(data)
      // alert("Registration Successfull")
      // this.route.navigate(['customerlogin'], (err) => alert("Enter valid details"))})

      this.service.registerOwner(userObj).subscribe((data : any) =>{
        this.route.navigate(['customerlogin']), alert('Registration successfull')}
          ,(err) => {alert('Invalid details')} )}

    else{
      this.error = "password did not match"
      console.log("miss match")
    }
  }
}
