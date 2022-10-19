import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  editForm !: FormGroup;
  data : any = localStorage.getItem('fuser')
  parse_data : any = JSON.parse(this.data);
  constructor(private fb : FormBuilder ,private service : ServiceService ,private router : Router ) { }

  
  ngOnInit(): void {
    
    this.editForm = this.fb.group({
      'first_name'  : new FormControl (this.parse_data.first_name,Validators.required),
      'last_name'  : new FormControl (this.parse_data.last_name,Validators.required),
      'email_id' : new FormControl (this.parse_data.email_id,[Validators.required,Validators.email]),
      'phone_number'  : new FormControl (this.parse_data.phone_number,[Validators.required,Validators.maxLength(12)]),
      'country'  : new FormControl (this.parse_data.country,[Validators.required]),
      'password' : new FormControl (this.parse_data.password,[Validators.required,Validators.maxLength(25)]),

    });
  }
  editFormSubmit(){
    this.service.editFreelancer(this.parse_data.email_id ,this.editForm.value).subscribe((data: any) => { localStorage.setItem('fuser', data); alert('edited successfully'); this.router.navigate(['freelance_login_page']); }); 
  }

  get first_name(){
    return this.editForm.get('first_name');
  }
  get last_name(){
    return this.editForm.get('last_name');
  }

  get country(){
    return this.editForm.get('country');
  }

  get email_id(){
    return this.editForm.get('email_id');
  }

  get phone_number(){ 
    return this.editForm.get('phone_number'); 
  }

  get password(){
    return this.editForm.get('password');}

}
