import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-store-view',
  templateUrl: './store-view.component.html',
  styleUrls: ['./store-view.component.css']
})
export class StoreViewComponent implements OnInit {

  store_id:any
  store_data:any

  constructor(private aroute:ActivatedRoute, private service:DataServiceService) {
    this.aroute.params.subscribe(data=>this.store_id=data['store_id'])
    this.service.getStore(this.store_id).subscribe(data=>this.store_data=data) }

  ngOnInit(): void {
  }

}
