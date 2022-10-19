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
  pageItems : any;
  totalPages : any


  getPageItems(num: number){
    this.subscription = this.dataService.getCancellationList(this.page + num).subscribe(
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
