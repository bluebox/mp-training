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
  pageItems : any;
  totalPages : any


  getPageItems(num: number){
    this.subscription = this.dataService.getUsersByAdmin(this.page + num).subscribe(
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
    if(confirm("User will be deleted, Do you want to continue")){
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
