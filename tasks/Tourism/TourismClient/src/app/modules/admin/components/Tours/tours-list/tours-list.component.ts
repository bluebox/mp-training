import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-tours-list',
  templateUrl: './tours-list.component.html',
  styleUrls: ['./tours-list.component.css']
})
export class ToursListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  tourList:any;
  subscription!: Subscription;
  deleteSubscription!: Subscription;


  page: number = 1;
  pageSize: number = this.dataService.pageSize;
  length!: number;
  pageItems : any;
  totalPages!: number;

  changePage(num: number){
    if(num>0){
      if(this.page < this.totalPages){
        this.page += num
        this.pageItems = this.tourList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }else{
      if(this.page != 1 ){
        this.page += num
        this.pageItems = this.tourList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }
  }

  ngOnInit(): void {
    this.subscription = this.dataService.getToursList().subscribe(
      data => {
        this.tourList = data,
        this.length = this.tourList.length
        this.totalPages = Math.ceil(this.length/this.pageSize)
        this.pageItems = this.tourList.slice(0, this.pageSize)
      },
      err => alert(err.error.detail)
    )
  }

  editTour(id: number){
    this.route.navigate(['admin/tours/addTour', id])
  }
  deleteTour(id: number){
    if(confirm("Tour will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deleteTour(id).subscribe(
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
