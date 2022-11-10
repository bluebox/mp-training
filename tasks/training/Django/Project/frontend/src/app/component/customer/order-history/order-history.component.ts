import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Route, Router } from '@angular/router';

import { GeneralService } from 'src/app/general.service';
import { Component, OnInit, NgModule, Input } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {
  response: any=[]
  billResp : any
  tripResp : any
  customer = window.sessionStorage.getItem('customer_id');
  closeResult!: string;
  trip : any
  date : any

  currentRate= 4
  constructor(private service : GeneralService, private route : Router, private modalService: NgbModal) {
      this.date = new Date();
      // console.log(this.date)
  }

  return : any[] = []
  pick : any[] = []


  review! : FormGroup

  page: number = 1;
  pageItems : any;
  totalPages : any

  getPageItems(num: number){
    this.subscription = this.service.orderHistory(this.page + num).subscribe(
      (data: any) => {
        console.log(data);
        this.pageItems = data.pageItems
        this.page = data.page
        this.totalPages = data.totalPages


        for(let i=0; i<this.pageItems.length; i++){


          this.pageItems[i].stringReturnDate = this.pageItems[i]['return_date']
            this.pageItems[i].stringPickDate = this.pageItems[i]['pickup_date']

            this.return.push(this.pageItems[i].return_date)
            this.pick.push(this.pageItems[i].pickup_date)


            this.pageItems[i]['return_date'] = new Date(this.pageItems[i]['return_date'])
            this.pageItems[i]['pickup_date'] = new Date(this.pageItems[i]['pickup_date'])
            // console.log(this.pageItems[i]['return_date'])
          }

      },

      err => alert(err.error.detail),


    )


}

  ngOnInit(): void {

      this.getPageItems(0)

      this.review = new FormGroup({
      customer_review : new FormControl('', Validators.required)
      }
    )
    this.service.trip.subscribe(res => {
      (this.trip = res)
    })
  }



  // getOrderHistory(){
  //   this.service.orderHistory(this.page ).subscribe(data => {(this.response=data),
  //     console.log(this.response)

  //     for(let i=0; i<this.response.length; i++){

  //       this.response[i].stringReturnDate = this.response[i]['return_date']
  //       this.response[i].stringPickDate = this.response[i]['pickup_date']

  //       this.return.push(this.response[i].return_date)
  //       this.pick.push(this.response[i].pickup_date)


  //       this.response[i]['return_date'] = new Date(this.response[i]['return_date'])
  //       this.response[i]['pickup_date'] = new Date(this.response[i]['pickup_date'])
  //       }
  //   } );
  //   console.log(this.return)
  // }


  getBill(id : any){
    this.service.getBill(id).subscribe(data => {
      this.billResp=data;
      console.log(this.billResp)
      // this.route.navigate(['view-bill', id])
      this.route.navigate(['view-bill', id])

     })
    }

  getTripDetails(id : any){
    this.service.getTrip(id).subscribe(data => {(this.tripResp=data),
    console.log(this.tripResp)})
  }

  addReview(content : any, rent_id : any, returnDate : any){


    this.open(content, rent_id)

}
cancelMessage : any

  cancelOrder(id : any){
    if(confirm("Are you sure you want to cancel!!!?"))
    {
      this.service.cancelOrder(id).subscribe(data => {(this.cancelMessage=data),this.ngOnInit(), alert(this.cancelMessage.msg) })
    }
  }

  open(content: any, rent_id : any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      this.service.customerReview( result,this.review.value).subscribe(data => { console.log(this.review.value)});
      alert("Review updated"), this.ngOnInit()
    }),

      (reason: any) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    };
  }


  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
  ratechange(currentRate : number){

  }

  deleteSubscription!: Subscription
  subscription!: Subscription



}
