import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-view-facilities',
  templateUrl: './view-facilities.component.html',
  styleUrls: ['./view-facilities.component.css']
})
export class ViewFacilitiesComponent implements OnInit {
facilities : any 
  constructor(private service : ServicesService) {
    this.service.getfacilities().subscribe(data =>{ this.facilities=data
      console.log(data);
     })
   }

  ngOnInit(): void {
  }

}
