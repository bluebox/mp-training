import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-paymentpage',
  templateUrl: './paymentpage.component.html',
  styleUrls: ['./paymentpage.component.css']
})
export class PaymentpageComponent implements OnInit {
  cost_for_slot:any
  cost_for_equipments:any
  total_cost:any
  constructor() { }

  ngOnInit(): void {

  
    var costobj = JSON.parse(sessionStorage['totalcost'])

    if (costobj){
    this.cost_for_slot=costobj.cost_for_slot
    this.cost_for_equipments=costobj.cost_for_equipments
    this.total_cost=costobj.total_cost
    }
    
  }

}
