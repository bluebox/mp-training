import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {


  constructor(private dataService: DataService,
    private route: Router
  ) { }
  activeUsers: any;
  subscription!: Subscription;
  deleteSubscription!: Subscription;


  page: number = 1;
  pageSize: number = this.dataService.pageSize;
  length!: number;
  pageItems : any;
  totalPages : any

  changePage(num: number){
    if(num>0){
      if(this.page < this.totalPages){
        this.page += num
        this.pageItems = this.activeUsers.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }else{
      if(this.page != 1 ){
        this.page += num
        this.pageItems = this.activeUsers.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }
  }

  ngOnInit(): void {
    this.subscription = this.dataService.getUsersByAdmin().subscribe(
      data => {
        console.log(data);
        this.activeUsers = data
        this.length = this.activeUsers.length
        this.totalPages = Math.ceil(this.length/this.pageSize)
        this.pageItems = this.activeUsers.slice(0, this.pageSize)
      },
      err => alert(err.error.detail)
    )
  }

  changeUserRole(id:number){
    this.dataService.editUsersAdminStatus(id).subscribe(
      data=> {
        alert('user admin status updated successfully');
        window.location.reload();
      },
      err=> alert(err.message)
    )
  }

  editUser(id: number){
    this.route.navigate(['admin/users/editUser', id])
  }

  deleteUser(id: number){
    if(confirm("Place will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deleteUser(id).subscribe(
        data=>{
          alert('user deleted successfully')
        },
        err => alert(err.error.detail)
      )
    }
  }

  ngOnDestroy(){
    if(this.deleteSubscription){
      this.deleteSubscription.unsubscribe()
    }
    if(this.subscription){
      this.subscription.unsubscribe()
    }
  }

}
