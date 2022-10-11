import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-contactus',
  templateUrl: './contactus.component.html',
  styleUrls: ['./contactus.component.css']
})
export class ContactusComponent implements OnInit {

  constructor(private dataService: DataServiceService) { }

  subscription!: Subscription

  EnquiryForm: FormGroup = new FormGroup({
    name : new FormControl('', [Validators.required]),
    email : new FormControl('', [Validators.required, Validators.email]),
    mobile : new FormControl('', [Validators.required]),
    subject : new FormControl('', [Validators.required]),
    message : new FormControl('', [Validators.required]),
  })

  submitEnquiryForm() {
    console.log(this.EnquiryForm.value);
    // this.subscription = this.dataService.postEnquiry(this.EnquiryForm.value).subscribe(
    //   data=>{
    //     alert('enquiry submitted successfully')
    //   },
    //   err => alert(err.error.detail)
    // )
  }

  ngOnInit(): void {
    console.log("object");
  }

  ngOnDestroy() {
    if(this.subscription){
      this.subscription.unsubscribe()
    }
  }

}
