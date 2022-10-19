import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-edit-cancellation',
  templateUrl: './edit-cancellation.component.html',
  styleUrls: ['./edit-cancellation.component.css']
})
export class EditCancellationComponent implements OnInit {

  routeSubscription!: Subscription;
  getCancellationSubscription!: Subscription;
  editCancellationSubscription!: Subscription;
  addCancellationSubscription!: Subscription;
  getBookingSubscription!: Subscription;
  bookingList: any;

  constructor(private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.routeSubscription = this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.getCancellationSubscription = this.dataservice.getCancellation(parseInt(res['id'])).subscribe(
          data=> {
          let cancellationString = JSON.stringify(data)
          let cancellationObj = JSON.parse(cancellationString)
          this.CancellationForm.get('bookingid')?.setValue(cancellationObj.bookingid);
          this.CancellationForm.get('refund_status')?.setValue(cancellationObj.refund_status);
          this.CancellationForm.get('cancellation_charges')?.setValue(cancellationObj.cancellation_charges);
          this.CancellationForm.get('reason_for_cancellation')?.setValue(cancellationObj.reason_for_cancellation);
        },
        err => alert(err.message)
      );
      }
    })
  }
  id!: number

  CancellationForm: FormGroup = new FormGroup({
    bookingid : new FormControl('', [Validators.required]),
    refund_status : new FormControl('', [Validators.required]),
    cancellation_charges : new FormControl('', [Validators.required]),
    reason_for_cancellation : new FormControl(''),
  })

  get formObj(){
    return this.CancellationForm.controls
  }

  ngOnInit(): void {
    this.getBookingSubscription = this.dataservice.getAllBookingList().subscribe(
      data=> {
        this.bookingList = data;
      },
      err => alert(err.error.detail)
    )
  }


  addCancellationObj() {
    if(this.CancellationForm.valid){
      if(this.id){
        this.editCancellationSubscription = this.dataservice.editCancellation(this.CancellationForm.value, this.id).subscribe(
          data=>{
            console.log(data)
            alert("cancellation details updated successfully")
            this.router.navigate(['admin/cancellation/cancellationList'])
          },
          err => alert(err.error.detail)
        )
      }
      // else{
      //   this.addCancellationSubscription = this.dataservice.editCancellation(this.CancellationForm.value).subscribe(
      //     data=>{
      //     console.log(data)
      //     this.router.navigate(['admin/cancellation/cancellationList'])
      //   },
      //   err => alert(err.error.detail)
      // )
      // }
    }
  }

  ngOnDestroy(){
    if(this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
    if(this.getCancellationSubscription){
      this.getCancellationSubscription.unsubscribe()
    }
    if(this.editCancellationSubscription){
      this.editCancellationSubscription.unsubscribe()
    }
    if(this.addCancellationSubscription){
      this.addCancellationSubscription.unsubscribe()
    }
    if(this.getBookingSubscription){
      this.getBookingSubscription.unsubscribe()
    }
  }

}
