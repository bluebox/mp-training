import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-enquiry-list',
  templateUrl: './enquiry-list.component.html',
  styleUrls: ['./enquiry-list.component.css']
})
export class EnquiryListComponent implements OnInit {

  subscription!: Subscription

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  enquiryList:any;

  ngOnInit(): void {
    this.subscription = this.dataService.getEnquiryList().subscribe(
      data => this.enquiryList = data,
      err => alert(err.error.detail)
    )
  }

  editEnquiry(id: number){
    this.route.navigate(['admin/enquiries/viewEnquiry', id])
  }

  ngOnDestroy() {
    if(this.subscription){
      this.subscription.unsubscribe()
    }
  }

}
