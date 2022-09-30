import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

interface Bill {
  type: string;
  description: string;
  billNumber: number;
  isPaid: boolean;
  totalBill: number;
  owner: string;

}

@Component({
  selector: 'app-show-bills',
  templateUrl: './show-bills.component.html',
  styleUrls: ['./show-bills.component.css']
})
export class ShowBillsComponent implements OnInit {

  bills: Bill[] = [];

  constructor(private auth: AuthService, private user: UserService) {
    try {
      let email = this.user.loginUser.email;
      let x = { email: email };
      this.auth.showBills(x).subscribe(data => {
        console.log(data);
        this.bills = data;
        console.log(this.bills);
      }
      )
    }
    catch (err) { console.log(err); }

  }


  ngOnInit(): void {
  }

}
