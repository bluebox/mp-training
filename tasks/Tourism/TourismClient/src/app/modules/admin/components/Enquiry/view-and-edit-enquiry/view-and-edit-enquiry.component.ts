import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-view-and-edit-enquiry',
  templateUrl: './view-and-edit-enquiry.component.html',
  styleUrls: ['./view-and-edit-enquiry.component.css']
})
export class ViewAndEditEnquiryComponent implements OnInit {

  routesubscription!: Subscription;
  getEnquirysubscription!: Subscription;
  editEnquirysubscription!: Subscription;

  constructor(private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.routesubscription = this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.getEnquirysubscription = this.dataservice.getEnquiry(parseInt(res['id'])).subscribe(
          data=> {
          let enquiryString = JSON.stringify(data)
          let enquiryObj = JSON.parse(enquiryString)
          this.EnquiryForm.get('name')?.setValue(enquiryObj.name);
          this.EnquiryForm.get('email')?.setValue(enquiryObj.email);
          this.EnquiryForm.get('mobile')?.setValue(enquiryObj.mobile);
          this.EnquiryForm.get('subject')?.setValue(enquiryObj.subject);
          this.EnquiryForm.get('message')?.setValue(enquiryObj.message);
          this.EnquiryForm.get('status')?.setValue(enquiryObj.status);
        },
        err => alert(err.error.detail)
        );

      }
    })
  }
  id!:number

  EnquiryForm: FormGroup = new FormGroup({
    name : new FormControl('', [Validators.required]),
    email : new FormControl('', [Validators.required, Validators.email]),
    mobile : new FormControl('', [Validators.required]),
    subject : new FormControl('', [Validators.required]),
    message : new FormControl('', [Validators.required]),
    status : new FormControl('', [Validators.required]),
  })

  ngOnInit(): void {
  }

  submitEnquiryForm(){
    this.editEnquirysubscription = this.dataservice.editEnquiry(this.EnquiryForm.value, this.id).subscribe(
      data=>{
      console.log(data)
      this.router.navigate(['admin/enquiries/enquiryList'])
    },
    err => alert(err.error.detail)
    )
  }

  ngOnDestroy(){
    if(this.routesubscription){
      this.routesubscription.unsubscribe()
    }
    if(this.getEnquirysubscription){
      this.getEnquirysubscription.unsubscribe()
    }
    if(this.editEnquirysubscription){
      this.editEnquirysubscription.unsubscribe()
    }
  }

}
