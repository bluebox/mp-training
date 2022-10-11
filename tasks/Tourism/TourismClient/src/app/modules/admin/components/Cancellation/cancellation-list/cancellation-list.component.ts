import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-cancellation-list',
  templateUrl: './cancellation-list.component.html',
  styleUrls: ['./cancellation-list.component.css']
})
export class CancellationListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  cancellationList: any;
  subscription!: Subscription
  deleteSubscription!: Subscription


  page: number = 1;
  pageSize: number = this.dataService.pageSize;
  length!: number;
  pageItems : any;
  totalPages : any;

  changePage(num: number){
    if(num>0){
      if(this.page < this.length/this.pageSize){
        this.page += num
        this.pageItems = this.cancellationList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }else{
      if(this.page != 1 ){
        this.page += num
        this.pageItems = this.cancellationList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }
  }

  ngOnInit(): void {
    this.dataService.getCancellationList().subscribe(
      data => {
        this.cancellationList = data;
        this.length = this.cancellationList.length
        this.totalPages = Math.ceil(this.length/this.pageSize)
        this.pageItems = this.cancellationList.slice(0, this.pageSize)
      },
      err => alert(err.error.detail)
    )

  }

  editCancellation(id: number) {
    this.route.navigate(['admin/cancellation/addCancellation', id])
  }

  deleteCancellation(id: number){
    if(confirm("this object will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deleteCancellation(id).subscribe(
        data=>{
          alert(data +' deleted successfully');
          this.ngOnInit()
        },
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
