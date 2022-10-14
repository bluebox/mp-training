import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Route, Router } from '@angular/router';

import { GeneralService } from 'src/app/general.service';
import { Component, OnInit, NgModule, Input } from '@angular/core';

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
  constructor(private service : GeneralService, private route : Router, private modalService: NgbModal) {
      this.date = new Date();
  }

  review! : FormGroup

  ngOnInit(): void {
    this.review = new FormGroup({
      customer_review : new FormControl('', Validators.required)
      }
    )

    this.getOrderHistory()

    this.service.trip.subscribe(res => {
      (this.trip = res)
    })
  }

  return : any[] = []
  pick : any[] = []


  getOrderHistory(){
    this.service.orderHistory().subscribe(data => {(this.response=data),
      console.log(this.response)

      for(let i=0; i<this.response.length; i++){

        this.response[i].stringReturnDate = this.response[i]['return_date']
        this.response[i].stringPickDate = this.response[i]['pickup_date']

        this.return.push(this.response[i].return_date)
        this.pick.push(this.response[i].pickup_date)


        this.response[i]['return_date'] = new Date(this.response[i]['return_date'])
        this.response[i]['pickup_date'] = new Date(this.response[i]['pickup_date'])
        }
    } );
    console.log(this.return)
  }
  getBill(id : any){
    this.service.getBill(id).subscribe(data => {(this.billResp=data),
      console.log(this.billResp),
      window.sessionStorage.setItem('rent_id', JSON.stringify(this.billResp)) })

      this.route.navigate(['view-bill'])
    }

  getTripDetails(id : any){
    this.service.getTrip(id).subscribe(data => {(this.tripResp=data),
    console.log(this.tripResp)})
  }

  addReview(content : any, rent_id : any, returnDate : any){
  // let retDate = new Date(returnDate.substring(0,4), returnDate.substring(5,7) - 1, returnDate.substring(8,returnDate.length))
  // let date = new Date()
  // console.log('.lkjsadbv;kjabhsdv')

  // if(retDate >= date)
  // {
  //   alert("ride not yet completed")
  // }

    this.open(content, rent_id)

}
cancelMessage : any
  cancelOrder(id : any){
    this.service.cancelOrder(id).subscribe(data => {(this.cancelMessage=data), alert(this.cancelMessage.message), this.ngOnInit()})
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



}
