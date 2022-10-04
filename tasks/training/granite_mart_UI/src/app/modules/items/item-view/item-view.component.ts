import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-item-view',
  templateUrl: './item-view.component.html',
  styleUrls: ['./item-view.component.css']
})
export class ItemViewComponent implements OnInit {

  contains_id: any;
  item_details: any;
  constructor(private service: DataServiceService, private aroute: ActivatedRoute) {
    this.aroute.params.subscribe(data => {
      this.contains_id = data['contains_id']
      
      this.service.getItem(this.contains_id).subscribe(data => {
         this.item_details = data;
          console.log(this.item_details)
         })
      console.log(this.item_details)
    })
   
  }

  ngOnInit(): void {
  }



}
