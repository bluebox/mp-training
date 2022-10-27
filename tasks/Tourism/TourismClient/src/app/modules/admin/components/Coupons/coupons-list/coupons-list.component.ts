import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  pageItems : any;
  totalPages : any


  searchText: FormGroup = new FormGroup({
    text: new FormControl(''),
  });

  getPageItems(num: number, searchText = this.searchText.get('text')?.value){
    this.subscription = this.dataService.getCouponsList(this.page + num, searchText).subscribe(
      (data: any) => {
        console.log(data);
        this.pageItems = data.pageItems
        this.page = data.page
        this.totalPages = data.totalPages
      },
      err => {
        if(err.status == 404){
          alert(err.message)
        }
        else{
          alert(err.error.detail)
        }
      }
    )
  }

  ngOnInit(): void {
    this.getPageItems(0)
  }


  editCoupon(id: number) {
    this.route.navigate(['admin/coupons/addCoupon', id])
  }

  deleteCoupon(id: number){
    if(confirm("Place will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deleteCoupon(id).subscribe(
        data=>alert(data +' deleted successfully'),
        err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
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
