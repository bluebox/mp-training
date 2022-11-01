import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { GeneralService } from 'src/app/general.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class OrdersComponent implements OnInit {
  response: any = [];
  closeResult!: string;
  return: any[] = [];
  pick: any[] = [];
  date: any;
  billResp : any;
  tripResp : any
  // totalKilometer : number =0
  review!: FormGroup;
  odoReading!: FormGroup;


  deleteSubscription!: Subscription
  subscription!: Subscription

  constructor(private service: GeneralService, private modalService: NgbModal, private route : Router) {
    this.date = new Date();
  }


  page: number = 1;
  pageItems : any;
  totalPages : any


  getPageItems(num: number){
    this.subscription = this.service.recievedOrders(this.page + num).subscribe(
      (data: any) => {
        // console.log(data);
        this.pageItems = data.pageItems
        this.page = data.page
        this.totalPages = data.totalPages


        for(let i=0; i<this.pageItems.length; i++){


            this.pageItems[i].stringReturnDate = this.pageItems[i]['return_date']
            this.pageItems[i].stringPickDate = this.pageItems[i]['pickup_date']
            this.pageItems[i].reading = (this.pageItems[i]['odo_end_reading']-this.pageItems[i]['odo_start_reading']);

            this.return.push(this.pageItems[i].return_date)
            this.pick.push(this.pageItems[i].pickup_date)


            this.pageItems[i]['return_date'] = new Date(this.pageItems[i]['return_date'])
            this.pageItems[i]['pickup_date'] = new Date(this.pageItems[i]['pickup_date'])
          }
      },
      err => alert(err.error.detail),
    )
    this.service.bill.subscribe(data=>(this.billResp=data))


}



bill : any


  resp: any;
  ngOnInit(): void {
    this.review = new FormGroup({
      owner_review: new FormControl(''),
    });

    this.odoReading = new FormGroup({
      odo_start_reading: new FormControl(''),
      odo_end_reading: new FormControl(''),
    });

    this.service.bill.subscribe((data) => {
      this.bill = data;

    });

    this.getPageItems(0)
  }

  generateBill(id: any) {
    this.service.generateBill(id).subscribe((data) => {
      (this.resp = data), alert(this.resp.message); this.ngOnInit()
    }, );

  }
  open(content: any, rent_id: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
        this.service
          .ownerReview(result, this.review.value)
          .subscribe((data) => {
            console.log(this.review.value);
          });
        alert('Review updated'), this.ngOnInit();
      });
  }
  
  addReview(content: any, rent_id: any) {
    this.open(content, rent_id);
  }

  openOdo(content: any, id: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
        this.service
          .addOdoReading(result, this.odoReading.value)
          .subscribe((data) => {
            console.log(this.odoReading.value);
          });
          this.ngOnInit();
      });
  }

  getBill(id : any){
    this.service.getBill(id).subscribe(data => {(this.billResp=data),
      console.log(this.billResp)
       })

      this.route.navigate(['own-view-bill', id])
    }

  getTripDetails(id : any){
    this.service.getTrip(id).subscribe(data => {(this.tripResp=data),
    console.log(this.tripResp)})
  }
}
