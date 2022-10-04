import { compileNgModule } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validator, Validators } from '@angular/forms';
import { DataserveService } from 'src/app/dataserve.service';
import { Customer, CustomerGroup } from 'src/app/data';
import { NgForm } from '@angular/forms'; 
import { Router, ActivatedRoute } from '@angular/router'; 
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  
  constructor(private postData: DataserveService,private router: Router, private route: ActivatedRoute ) { 
    console.log('SignupComponent');
  }
  ngOnInit(): void {
    // this.postData.getDetailsnew(this.data).subscribe(data => console.log(data))
  }
  profileForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(6),Validators.maxLength(8)]),
    first_name: new FormControl('', Validators.required),
    last_name: new FormControl(''),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),


  });

  data = { 
  
    "uname": "yFF",
    "f_name": "yGGG",
    "l_name": "yGG",
    "mail": "yFFFF@gmail.com",
    "password": "123FFF"
}

  onSubmit(){
    // console.log(value1);
    // alert(value1)
    if (this.profileForm.valid){
    this.postData.postCustDetailsnew(this.profileForm.value).subscribe(newdata => console.log(newdata))
    // this.postData.getCustDetailsnew().subscribe(newdata => console.log(newdata))

    this.router.navigate(['login/']);
    // getDetailsnew()
    }
  }
 

}
