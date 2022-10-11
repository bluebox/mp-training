import { Component, OnInit } from '@angular/core';
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
  pageSize: number = this.dataService.pageSize;
  length!: number;
  pageItems : any;
  totalPages : any;

  changePage(num: number){
    if(num>0){
      if(this.page < this.length/this.pageSize){
        this.page += num
        this.pageItems = this.packageList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }else{
      if(this.page != 1 ){
        this.page += num
        this.pageItems = this.packageList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }
  }

  ngOnInit(): void {
    this.dataService.getPackageList().subscribe(
      data => {
        this.packageList = data;
        this.length = this.packageList.length
        this.totalPages = Math.ceil(this.length/this.pageSize)
        this.pageItems = this.packageList.slice(0, this.pageSize)
      },
      err => alert(err.error.detail)
    )

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
        err=>alert(err.error.detail)
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
