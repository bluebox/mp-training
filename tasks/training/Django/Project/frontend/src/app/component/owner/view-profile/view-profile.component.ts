import { GeneralService } from './../../../general.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {
  resp : any
  constructor(private service : GeneralService) {

  }

  ngOnInit(): void {
    this.getProfileDetails()
  }

  getProfileDetails(){
    this.service.getOwnerProfileDetails().subscribe(data  =>
      (this.resp =data))
  }

}
