import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent implements OnInit {

  constructor(private router: Router, private user: UserService) { }

  ngOnInit(): void {
  }

  updateProfile() {
    this.router.navigate(['admin/updateprofile']);
  }
  addReview() {
    this.router.navigate(['admin/addreview']);

  }
  showBills() {
    this.router.navigate(['admin/showbills']);
  }

}
