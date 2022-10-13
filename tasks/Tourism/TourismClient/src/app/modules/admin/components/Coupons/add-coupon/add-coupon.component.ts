import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrls: ['./add-coupon.component.css']
})
export class AddCouponComponent implements OnInit {

  routeSubscription!: Subscription;
  getCouponSubscription!: Subscription;
  editCouponSubscription!: Subscription;
  addCouponSubscription!: Subscription;

  constructor(private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.routeSubscription = this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.getCouponSubscription = this.dataservice.getCoupon(parseInt(res['id'])).subscribe(
          data=> {
          let couponString = JSON.stringify(data)
          let couponObj = JSON.parse(couponString)
          this.CouponForm.get('couponcode')?.setValue(couponObj.couponcode);
          this.CouponForm.get('discount')?.setValue(couponObj.discount);
          this.CouponForm.get('description')?.setValue(couponObj.description);
          let date = (new Date(couponObj.valid_till)).toLocaleDateString()
          let dateArray = date.split('/')
          this.CouponForm.get('valid_till')?.setValue(`${dateArray[2]}-${dateArray[0]}-${dateArray[1]}`);
        },
        err => alert(err.error.detail)
      );
      }
    })
  }
  id!: number

  CouponForm: FormGroup = new FormGroup({
    couponcode : new FormControl('', [Validators.required]),
    discount : new FormControl('', [Validators.required]),
    description : new FormControl('', [Validators.required]),
    valid_till : new FormControl('', [Validators.required]),
  })

  get formObj(){
    return this.CouponForm.controls
  }


  ngOnInit(): void {
  }

  addCouponObj() {
    if(this.CouponForm.valid){
      if(this.id){
        this.editCouponSubscription = this.dataservice.editCoupon(this.CouponForm.value, this.id).subscribe(
          (data: any)=>{
            alert(`Coupon ${data.couponcode} Updated successfully`)
          this.router.navigate(['admin/coupons/couponList'])
        },
        err => alert(err.error.detail)
      )
      }else{
        this.addCouponSubscription = this.dataservice.addCoupon(this.CouponForm.value).subscribe(
          (data: any)=>{
          alert(`Coupon ${data.couponcode} added successfully`)
          this.router.navigate(['admin/coupons/couponList'])
        },
        err => alert(err.error.detail)
      )
      }
    }
  }

  ngOnDestroy(){
    if(this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
    if(this.getCouponSubscription){
      this.getCouponSubscription.unsubscribe()
    }
    if(this.editCouponSubscription){
      this.editCouponSubscription.unsubscribe()
    }
    if(this.addCouponSubscription){
      this.addCouponSubscription.unsubscribe()
    }
  }

}
