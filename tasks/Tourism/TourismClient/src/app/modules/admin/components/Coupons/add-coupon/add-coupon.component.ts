import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrls: ['./add-coupon.component.css']
})
export class AddCouponComponent implements OnInit {

  constructor(private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.dataservice.getCoupon(parseInt(res['id'])).subscribe(data=> {
          let couponString = JSON.stringify(data)
          let couponObj = JSON.parse(couponString)
          this.CouponForm.get('couponcode')?.setValue(couponObj.couponcode);
          this.CouponForm.get('discount')?.setValue(couponObj.discount);
          this.CouponForm.get('description')?.setValue(couponObj.description);
          let date = (new Date(couponObj.valid_till)).toLocaleDateString()
          let dateArray = date.split('/')
          this.CouponForm.get('valid_till')?.setValue(`${dateArray[2]}-${dateArray[0]}-${dateArray[1]}`);
        });
      }
    })
  }
  id!: number

  CouponForm: FormGroup = new FormGroup({
    couponcode : new FormControl('', [Validators.required]),
    discount : new FormControl('', [Validators.required, Validators.email]),
    description : new FormControl('', [Validators.required]),
    valid_till : new FormControl('', [Validators.required]),
  })


  ngOnInit(): void {
  }

  addCouponObj() {
    if(this.id){
      this.dataservice.editCoupon(this.CouponForm.value, this.id).subscribe(data=>{
        console.log(data)
        this.router.navigate(['admin/coupons/couponList'])
      })
    }else{
      this.dataservice.addCoupon(this.CouponForm.value).subscribe(data=>{
        console.log(data)
        this.router.navigate(['admin/coupons/couponList'])
      })
    }
  }

}
