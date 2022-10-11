import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-coupons-list',
  templateUrl: './coupons-list.component.html',
  styleUrls: ['./coupons-list.component.css']
})
export class CouponsListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  couponList: any;
  subscription!: Subscription
  deleteSubscription!: Subscription


  page: number = 1;
  pageSize: number = this.dataService.pageSize;
  length!: number;
  pageItems : any;
  totalPages : any;

  changePage(num: number){
    if(num>0){
      if(this.page < this.length/this.pageSize){
        this.page += num
        this.pageItems = this.couponList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }else{
      if(this.page != 1 ){
        this.page += num
        this.pageItems = this.couponList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }
  }

  ngOnInit(): void {
    this.dataService.getCouponsList().subscribe(
      data => {
        this.couponList = data;
        this.length = this.couponList.length
        this.totalPages = Math.ceil(this.length/this.pageSize)
        this.pageItems = this.couponList.slice(0, this.pageSize)
      },
      err => alert(err.error.detail)
    )

  }

  editCoupon(id: number) {
    this.route.navigate(['admin/coupons/addCoupon', id])
  }

  deleteCoupon(id: number){
    if(confirm("Place will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deleteCoupon(id).subscribe(
        data=>alert(data +' deleted successfully')
      )
    }
  }

  ngOnDestroy() {
    if(this.subscription){
      this.subscription.unsubscribe()
    }
    if(this.deleteSubscription){
      this.deleteSubscription.unsubscribe()
    }
  }

}
