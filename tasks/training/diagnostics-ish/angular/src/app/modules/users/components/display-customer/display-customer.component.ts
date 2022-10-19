import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from 'src/app/services/http-service/http.service';
import { MatDialog } from '@angular/material/dialog';
import { CloseDialogComponent } from '../close-dialog/close-dialog.component';
import { CustomerService } from 'src/app/services/customer-service/customer.service';

export interface Customer{
  customer_id:string,
  user_id : any
}

@Component({
  selector: 'app-display-customer',
  templateUrl: './display-customer.component.html',
  styleUrls: ['./display-customer.component.css']
})
export class DisplayCustomerComponent implements OnInit {
  customer_id : string =''
  customerDetails : any
  UserDetails : any
  appointments: any

  getCustomerMethod(){
    this.http.getCustomer(this.customer_id).subscribe({
      next: (data: any) => {
        this.customerDetails = data["customer_details"]
        this.UserDetails = data["user_details"]
        this.appointments = data["appointments"]
      },
      error: (err) => {
        console.log(err.error.detail);
      }
    })
  }
  constructor(private router : Router,private actRouter: ActivatedRoute, private http: CustomerService, public dialog: MatDialog) { 
    this.actRouter.params.subscribe(data => {
      this.customer_id = data['cutomer_id']
    })
    
  }
  ngOnInit(): void {
    this.getCustomerMethod()
  }

  openDialog(id: any) {
    let dialogRef = this.dialog.open(CloseDialogComponent)

    dialogRef.afterClosed().subscribe(result => {
      if (result == 'true') {
        this.http.deleteCustomer(id).subscribe({
          next: (res) => {
            console.log(res);
            this.router.navigate(['admin/display-customers'])
            
          }
        })
        console.log('delete');
        
      }
    })
  }
  updateCustomer(customer_id: string) {
    this.router.navigate(['update-customer/', customer_id])
  }
}
