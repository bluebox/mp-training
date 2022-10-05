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

  subscription!: Subscription
  tourObject: any
  discount: number = 0
  coupon: any;
  // totalPrice: number = 0;

  constructor(private fb:FormBuilder,
    private route: ActivatedRoute,
    private dataService: DataServiceService,
    private auth: AuthService,
    ) {

    this.TouristForm = this.fb.group({
      tourists: this.fb.array([]) ,
    });

  }

  // id!: number;

  ngOnInit(): void {
    this.route.params.subscribe( data => {
      let id = parseInt(data['id'])
      this.subscription = this.dataService.getTourById(id).subscribe(data => {
        let res = JSON.stringify(data)
        let resObj = JSON.parse(res)
        this.tourObject = resObj
        console.log(resObj);
        // this.totalPrice=resObj.price
        // let images = resObj.places.map((place: { image: string; }) => place.image)
        // console.log(images);
        // this.tourImages = images
      })

    })
  }


  updateCoupon(coupon: any){
    console.log(coupon);
    this.coupon = coupon
    this.discount = coupon.discount * this.tourObject.price
  }


  paymentFormGroup = new FormGroup({
    payment_type: new FormControl('', Validators.required),
    // coupon: new FormControl('')
  });



  onSubmit() {
    console.log(this.TouristForm.value);
    console.log(this.paymentFormGroup.value);
    this.editable=false;
    let total_amount = (this.tourObject.price - this.discount) * this.tourists.length
    this.dataService.addPaymentDetails({...this.paymentFormGroup.value, coupon:this.coupon.id, total_price: total_amount}).subscribe(data=>{
      let paymentString = JSON.stringify(data)
      let paymentObj = JSON.parse(paymentString)
      if(paymentObj?.id){
        let bookingObj = {
          userid : 2,
          tourid : this.tourObject.id,
          paymentid : paymentObj.id,
          no_of_people : this.tourists.length,
          passenger_details : JSON.stringify(this.TouristForm.get('tourists')?.value)
        }
        this.dataService.addBookingDetails(bookingObj).subscribe(data=>{
          console.log("booking res", data);
        })
      }
    })




    // alert("Payment Successful")
  }

  get tourists() : FormArray {
    return this.TouristForm.get("tourists") as FormArray
  }

  newTourist(): FormGroup {
    return this.fb.group({
      name: '',
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

  TouristDetails(e: any) {

  }

  ngOnDestroy() {
    if(this.subscription){
      this.subscription.unsubscribe();
    }
  }

}
