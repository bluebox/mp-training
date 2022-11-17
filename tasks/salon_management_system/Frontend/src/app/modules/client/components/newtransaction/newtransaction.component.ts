import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

interface type {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-newtransaction',
  templateUrl: './newtransaction.component.html',
  styleUrls: ['./newtransaction.component.css']
})
export class NewtransactionComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  constructor(private http:HttpserviceService,private route:Router) { }

  types: type[] = [
    {value: 'Credit Card', viewValue: 'Credit Card'},
    {value: 'Debit Card', viewValue: 'Debit Card'},
    {value: 'Net Banking', viewValue: 'Net Banking'},
    {value: 'UPI', viewValue: 'UPI'},
    {value: 'Pay at Salon', viewValue: 'Pay at Salon'}
  ];

  transactionForm : FormGroup = new FormGroup({
    Appointment_id : new FormControl("",Validators.required),
    trans_type : new FormControl(this.types[1].value,Validators.required),
    Total_amount : new FormControl("",Validators.required),
    trans_status : new FormControl("success",Validators.required)
  })

  ngOnInit(): void {
   
  }

  onSubmit(){
    console.log(this.transactionForm.value);
    this.http.addTransactions(this.transactionForm.value).subscribe(data => {console.log(data);
      this.route.navigate(['client/footer'])
    });
  }

}
