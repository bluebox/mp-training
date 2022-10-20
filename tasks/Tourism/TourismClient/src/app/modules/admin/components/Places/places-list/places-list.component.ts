import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  pageItems : any;
  totalPages : any;

  searchText: FormGroup = new FormGroup({
    text: new FormControl(''),
  });


  getPageItems(num: number, searchText = this.searchText.get('text')?.value){
    this.subscription = this.dataService.getPlacesList(this.page + num, searchText).subscribe(
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
