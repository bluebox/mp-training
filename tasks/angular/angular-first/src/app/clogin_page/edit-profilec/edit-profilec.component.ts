import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-edit-profilec',
  templateUrl: './edit-profilec.component.html',
  styleUrls: ['./edit-profilec.component.css']
})
export class EditProfilecComponent implements OnInit {

  constructor(private fb: FormBuilder, private service: ServiceService,private router : Router) { }
  data: any = sessionStorage.getItem('cuser');
  client_id: any = JSON.parse(this.data);
  editForm!: FormGroup;
  ngOnInit(): void {
    this.editForm = this.fb.group({
      'client_id': this.client_id.client_id,
      'client_name': new FormControl(this.client_id.client_name, Validators.required),
      'email_id': new FormControl(this.client_id.email_id, [Validators.required, Validators.email]),
      'phone_number': new FormControl(this.client_id.phone_number, [Validators.required, Validators.maxLength(12)]),
      'password': new FormControl(this.client_id.password, [Validators.required]),
      'client_country': new FormControl(this.client_id.client_country, [Validators.required])


    });
  }

  editFormSubmit() {
    // console.log(this.editForm.value);

    this.service.editClientDetails(this.client_id.email_id, this.editForm.value).subscribe((data: any) => { sessionStorage.setItem('cuser', JSON.stringify(data)) ;alert('edited successfully') ;this.router.navigate(['client_login_page']) }, (err: any) => { console.log(err); });
  }

  get first_name(){
    return this.editForm.get('client_name');
  }

  get client_country(){
    return this.editForm.get('client_country');
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
