import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-enquiry-list',
  templateUrl: './enquiry-list.component.html',
  styleUrls: ['./enquiry-list.component.css']
})
export class EnquiryListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  enquiryList:any;

  ngOnInit(): void {
    this.dataService.getEnquiryList().subscribe(data => this.enquiryList = data)
  }

  editEnquiry(id: number){
    this.route.navigate(['admin/enquiries/viewEnquiry', id])
  }

}
