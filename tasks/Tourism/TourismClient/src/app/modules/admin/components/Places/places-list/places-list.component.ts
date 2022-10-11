import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-places-list',
  templateUrl: './places-list.component.html',
  styleUrls: ['./places-list.component.css']
})
export class PlacesListComponent implements OnInit {

  subscription!: Subscription;
  deleteSubscription!: Subscription;

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  placeList:any;



  page: number = 1;
  pageSize: number = this.dataService.pageSize;
  length!: number;
  pageItems : any;
  totalPages : any;

  changePage(num: number){
    if(num>0){
      if(this.page < this.length/this.pageSize){
        this.page += num
        this.pageItems = this.placeList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }else{
      if(this.page != 1 ){
        this.page += num
        this.pageItems = this.placeList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }
  }

  ngOnInit(): void {
    this.subscription = this.dataService.getPlacesList().subscribe(
      data =>{
        this.placeList = data,
        this.length = this.placeList.length
        this.totalPages = Math.ceil(this.length/this.pageSize)
        this.pageItems = this.placeList.slice(0, this.pageSize)
      },
      err => alert(err.error.detail)
    )
  }

  editPlace(id: number){
    this.route.navigate(['admin/places/addPlace', id])
  }

  deletePlace(id: number){
    if(confirm("Place will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deletePlace(id).subscribe(
        data=>alert(data +' deleted successfully')
      )
    }
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
