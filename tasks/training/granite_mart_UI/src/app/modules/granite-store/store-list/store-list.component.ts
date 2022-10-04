import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-store-list',
  templateUrl: './store-list.component.html',
  styleUrls: ['./store-list.component.css']
})
export class StoreListComponent implements OnInit {
  storeList:any
  constructor(private service:DataServiceService,private router:Router) { }

  ngOnInit(): void {
    this.service.getStores().subscribe(data=>{this.storeList=data; console.log(data)})
  }

  viewStore(store_id:any){
    this.router.navigate(['viewStore',store_id])
  }
  editStore(store_id:any){}
  deleteStore(store_id:any){}

}
