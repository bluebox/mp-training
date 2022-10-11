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
  pageSize: number = this.dataService.pageSize;
  length!: number;
  pageItems : any;
  totalPages : any;

  changePage(num: number){
    if(num>0){
      if(this.page < this.length/this.pageSize){
        this.page += num
        this.pageItems = this.vehicleList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }else{
      if(this.page != 1 ){
        this.page += num
        this.pageItems = this.vehicleList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }
  }

  ngOnInit(): void {
    this.subscription = this.dataService.getVehiclesList().subscribe(
      data => {
        this.vehicleList = data;
        this.length = this.vehicleList.length
        this.totalPages = Math.ceil(this.length/this.pageSize)
        this.pageItems = this.vehicleList.slice(0, this.pageSize)
      },
      err => alert(err.error.detail)
    )
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
