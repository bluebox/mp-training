import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-package-list',
  templateUrl: './package-list.component.html',
  styleUrls: ['./package-list.component.css']
})
export class PackageListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  packageList: any;
  subscription!: Subscription
  deleteSubscription!: Subscription


  page: number = 1;
  pageItems : any;
  totalPages : any

  searchText: FormGroup = new FormGroup({
    text: new FormControl(''),
  });


  getPageItems(num: number, searchText = this.searchText.get('text')?.value){
    this.subscription = this.dataService.getPackageList(this.page + num, searchText).subscribe(
      (data: any) => {
        console.log(data);
        this.pageItems = data.pageItems
        this.page = data.page
        this.totalPages = data.totalPages
      },
      err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
    )
  }

  ngOnInit(): void {
    this.getPageItems(0)
  }


  editPackage(id: number) {
    this.route.navigate(['admin/package/addPackage', id])
  }

  deletePackage(id: number){
    if(confirm("Package will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deletePackage(id).subscribe(
        data=>{
          console.log(data);
          alert('package deleted successfully');
          this.ngOnInit()
        },
        err=>{
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
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
