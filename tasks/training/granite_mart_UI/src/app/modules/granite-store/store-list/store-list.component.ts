import { Component, OnInit } from '@angular/core';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-store-list',
  templateUrl: './store-list.component.html',
  styleUrls: ['./store-list.component.css']
})
export class StoreListComponent implements OnInit {
  storeList:any
  constructor(private service:DataServiceService) { }

  ngOnInit(): void {
    this.service.getStores().subscribe(data=>{this.storeList=data; console.log(data)})
  }

}
