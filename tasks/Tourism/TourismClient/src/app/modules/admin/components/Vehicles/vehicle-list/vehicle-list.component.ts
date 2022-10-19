import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  deleteSubscription!: Subscription;
  vehicleList:any;
  subscription!: Subscription;


  page: number = 1;
  pageItems : any;
  totalPages : any


  getPageItems(num: number){
    this.subscription = this.dataService.getVehiclesList(this.page + num).subscribe(
      (data: any) => {
        console.log(data);
        this.pageItems = data.pageItems
        this.page = data.page
        this.totalPages = data.totalPages
      },
      err => alert(err.error.detail)
    )
  }

  ngOnInit(): void {
    this.getPageItems(0)
  }


  editTour(id: number){
    this.route.navigate(['admin/vehicles/addVehicle', id])
  }
  deleteTour(id: number){
    this.deleteSubscription = this.dataService.deleteVehicle(id).subscribe(
      data => {
        alert(`Vehicle deleted successfully`)
      },
      err => alert(err.error.detail)
    )
  }

  ngOnDestroy() {
    if(this.subscription){
      this.subscription.unsubscribe()
    }
    if(this.deleteSubscription){
      this.deleteSubscription.unsubscribe()
    }
  }

}
