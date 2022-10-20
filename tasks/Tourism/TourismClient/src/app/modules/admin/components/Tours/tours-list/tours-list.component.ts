import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  pageItems : any;
  totalPages : any;

  searchText: FormGroup = new FormGroup({
    text: new FormControl(''),
  });


  getPageItems(num: number, searchText = this.searchText.get('text')?.value){
    this.subscription = this.dataService.getToursList(this.page + num, searchText).subscribe(
      (data: any) => {
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
