import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  ngOnInit(): void {
    this.dataService.getCouponsList().subscribe(data => this.couponList = data)

  }

  editCoupon(id: number) {
    this.route.navigate(['admin/coupons/addCoupon', id])
  }

}
