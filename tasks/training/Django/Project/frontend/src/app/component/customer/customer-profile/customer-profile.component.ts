import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  data_1 : any =window.sessionStorage.getItem('customer_id')
  data=JSON.parse(this.data_1)

  editProfile(){
    
  }
}
