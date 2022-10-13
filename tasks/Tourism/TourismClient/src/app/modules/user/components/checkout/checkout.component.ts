import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { TourItem } from 'src/app/Interfaces/TourInterface';
import { AuthService } from 'src/app/services/auth.service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: {showError: true},
    },
  ],
})
export class CheckoutComponent implements OnInit {

  Gender = [
    {value: 'male', viewValue: 'Male'},
    {value: 'female', viewValue: 'Female'},
    {value: 'other', viewValue: 'Other'},
  ];

  paymentTypes = [
    {value: 'upi', viewValue: 'UPI'},
    {value: 'credit_card', viewValue: 'Credit Card'},
    {value: 'debit_card', viewValue: 'Debit Card'},
  ];

  editable: boolean = true;


  TouristForm: FormGroup;

  getTourByIdSubscription!: Subscription
  addPaymentDetailsSubscription!: Subscription
  addBookingDetailsSubscription!: Subscription
  tourObject: any
  discount: number = 0
  coupon: any;
  // totalPrice: number = 0;
  submit!: boolean
  nextsubmit!: boolean

  constructor(private fb:FormBuilder,
    private route: ActivatedRoute,
    private dataService: DataServiceService,
    private auth: AuthService,
    ) {

    this.TouristForm = this.fb.group({
      tourists: this.fb.array([], Validators.required)
    });


  }

  // id!: number;

  ngOnInit(): void {
    this.route.params.subscribe( data => {
      let id = parseInt(data['id'])
      this.getTourByIdSubscription = this.dataService.getTourById(id).subscribe(
        data => {
          let res = JSON.stringify(data)
          let resObj = JSON.parse(res)
          this.tourObject = resObj
        },
        err => alert(err.error.detail)
      )

    })
  }


  updateCoupon(coupon: any){
    console.log(coupon);
    this.coupon = coupon
    if(coupon)
      this.discount = coupon.discount * this.tourObject.price
    else
      this.discount = 0
  }

  // error : any
  // updateCouponCode(){
  //   this.error = ''
  //   console.log(this.CouponForm.value);
  //   if(this.CouponForm.valid){
  //     if(this.tourObject){
  //       let couponArr = this.tourObject.coupons.filter((coupon: { couponcode: any; }) => coupon.couponcode.includes(this.CouponForm.value))
  //       if(couponArr.length != 0){
  //         this.updateCoupon(couponArr[0])
  //       }else{
  //         this.error = 'Invalid Coupon'
  //       }
  //     }
  //   }

  // }


  paymentFormGroup = new FormGroup({
    payment_type: new FormControl('', Validators.required),
    // coupon: new FormControl('')
  });

  CouponForm = new FormGroup({
    couponCode: new FormControl('', Validators.required),
    // coupon: new FormControl('')
  });

  get formObj(){
    return this.CouponForm.controls
  }




  onSubmit() {
    console.log(this.TouristForm.value);
    console.log(this.paymentFormGroup.value);
    if(this.TouristForm.valid && this.paymentFormGroup.valid){
      this.editable=false;
      let total_amount = (this.tourObject.price - this.discount) * this.tourists.length
      this.addPaymentDetailsSubscription = this.dataService.addPaymentDetails({...this.paymentFormGroup.value, coupon_applied:this.coupon?.id, total_price: total_amount}).subscribe(
        data=>{
          let paymentString = JSON.stringify(data)
          let paymentObj = JSON.parse(paymentString)
          if(paymentObj?.id){
            let bookingObj = {
              userid : this.auth.currentUser.id,
              tourid : this.tourObject.id,
              paymentid : paymentObj.id,
              no_of_people : this.tourists.length,
              passenger_details : JSON.stringify(this.TouristForm.get('tourists')?.value)
            }
            this.addBookingDetailsSubscription = this.dataService.addBookingDetails(bookingObj).subscribe(
              data=>{
                alert("Tour Booking successful");
              },
              err => alert(err.error.detail)
            )
          }
        },
        err=> alert(err.error.detail)
      )
    }




    // alert("Payment Successful")
  }

  get tourists() : FormArray {
    return this.TouristForm.get("tourists") as FormArray
  }

  newTourist(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      gender:'',
      age: '',
    })
  }


  addTourist() {
    this.tourists.push(this.newTourist());
  }

  removeTourist(i:number) {
    this.tourists.removeAt(i);
  }

  // TouristDetails(e: any) {

  // }

  ngOnDestroy() {
    if(this.getTourByIdSubscription){
      this.getTourByIdSubscription.unsubscribe();
    }
    if(this.addPaymentDetailsSubscription){
      this.addPaymentDetailsSubscription.unsubscribe();
    }
    if(this.addBookingDetailsSubscription){
      this.addBookingDetailsSubscription.unsubscribe();
    }
  }

}
