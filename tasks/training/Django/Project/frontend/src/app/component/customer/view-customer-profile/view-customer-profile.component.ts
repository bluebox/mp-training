import { Component, OnInit } from '@angular/core';
import { GeneralService } from 'src/app/general.service';

@Component({
  selector: 'app-view-customer-profile',
  templateUrl: './view-customer-profile.component.html',
  styleUrls: ['./view-customer-profile.component.css']
})
export class ViewCustomerProfileComponent implements OnInit {
  resp : any
  constructor(private service : GeneralService) { }

  ngOnInit(): void {
    this.getProfileDetails()
  }
  getProfileDetails(){
    this.service.getprofileDetails().subscribe(data  =>
    (this.resp =data))
  }
}
