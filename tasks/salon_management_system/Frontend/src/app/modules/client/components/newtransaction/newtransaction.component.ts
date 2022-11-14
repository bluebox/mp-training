import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-newtransaction',
  templateUrl: './newtransaction.component.html',
  styleUrls: ['./newtransaction.component.css']
})
export class NewtransactionComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  constructor(private http:HttpserviceService,private route:Router) { }

  transactionForm : FormGroup = new FormGroup({
    Appointment_id : new FormControl("",Validators.required),
    trans_type : new FormControl("",Validators.required),
    Total_amount : new FormControl("",Validators.required),
    trans_status : new FormControl("",Validators.required)
  })

  ngOnInit(): void {

  }

  onSubmit(){
    console.log(this.transactionForm.value);
  }

}
