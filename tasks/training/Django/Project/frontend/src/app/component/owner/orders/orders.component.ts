import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { GeneralService } from 'src/app/general.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  response: any=[]
  closeResult !: string;
  return: any[] = []
  pick: any[] = []
  date : any

  review !: FormGroup;

  constructor(private service : GeneralService, private modalService : NgbModal) {
    this.date = new Date();
   }
  resp : any
  ngOnInit(): void {

    this.review = new FormGroup({
      owner_review: new FormControl('')
      }
    )
    this.getOrders()
  }

  getOrders() {

    this.service.recievedOrders().subscribe(data => {
      this.response=data;
      console.log(this.response);

      window.sessionStorage.setItem('order_id', JSON.stringify(data))
      for(let i=0; i<this.response.length; i++){

        this.response[i].stringReturnDate = this.response[i]['return_date']
        this.response[i].stringPickDate = this.response[i]['pickup_date']

        this.return.push(this.response[i].return_date)
        this.pick.push(this.response[i].pickup_date)


        this.response[i]['return_date'] = new Date(this.response[i]['return_date'])

        }

    } );
  }
  generateBill(id : any){
    this.service.generateBill(id).subscribe( data =>{
      this.resp=data, alert(this.resp.message)
  })

  }
  open(content: any, rent_id : any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      this.service.ownerReview( result,this.review.value).subscribe(data => { console.log(this.review.value)});
      alert("Review updated"), this.ngOnInit()
    })

  }
  addReview(content : any, rent_id : any){

      this.open(content, rent_id)
 
  }


}
