import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-contactus',
  templateUrl: './contactus.component.html',
  styleUrls: ['./contactus.component.css']
})
export class ContactusComponent implements OnInit {

  constructor() { }

  EnquiryForm: FormGroup = new FormGroup({
    name : new FormControl('', [Validators.required]),
    email : new FormControl('', [Validators.required, Validators.email]),
    mobile : new FormControl('', [Validators.required]),
    subject : new FormControl('', [Validators.required]),
    message : new FormControl('', [Validators.required]),
  })

  submitEnquiryForm() {
    console.log(this.EnquiryForm.value);
  }

  ngOnInit(): void {
  }

}
