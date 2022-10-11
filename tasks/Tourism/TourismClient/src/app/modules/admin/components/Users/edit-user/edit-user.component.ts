import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  routeSubscription!: Subscription;
  getUserSubscription!: Subscription;
  editUserSubscription!: Subscription;
  addUserSubscription!: Subscription;
  imagesSubscription!: Subscription;
  imageUrl!: string | null



  constructor(private dataservice: DataService,
    private dataService: DataServiceService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.routeSubscription = this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.getUserSubscription = this.dataservice.getUser(parseInt(res['id'])).subscribe(
          data=> {
          let userString = JSON.stringify(data)
          let userObj = JSON.parse(userString)
          this.UserForm.get('name')?.setValue(userObj.name);
          this.UserForm.get('email')?.setValue(userObj.email);
          this.UserForm.get('mobile')?.setValue(userObj.mobile);
          this.UserForm.get('image')?.setValue(userObj.image);
          this.imageUrl = userObj.image;
          this.UserForm.get('password')?.setValue(userObj.password);
          this.UserForm.get('isAdmin')?.setValue(userObj.isAdmin);
          this.UserForm.get('is_active')?.setValue(userObj.is_active);
        },
        err => alert(err.error.detail)
      );
      }
    })
  }
  id!: number

  UserForm: FormGroup = new FormGroup({
    name : new FormControl('', [Validators.required]),
    email : new FormControl('', [Validators.required, Validators.email]),
    mobile : new FormControl('', [Validators.required]),
    image : new FormControl('', [Validators.required]),
    password : new FormControl('', [Validators.required]),
    isAdmin : new FormControl('', [Validators.required]),
    is_active : new FormControl('', [Validators.required]),
  })

  onchange(e:any){
    console.log(e.target.files[0]);
    this.imagesSubscription = this.dataService.uploadImage(e.target.files[0]).subscribe(data => {
      let dataString = JSON.stringify(data)
      this.imageUrl = JSON.parse(dataString)
    })
  }


  ngOnInit(): void {
  }

  addUserObj() {
    let userObj = {...this.UserForm.value, image:this.imageUrl}
    if(this.id){
      this.editUserSubscription = this.dataservice.editUser(userObj, this.id).subscribe(
        data=>{
        console.log(data)
        alert("user profile updated successfully")
        this.router.navigate(['admin/users/userList'])
      },
      err => alert(err.error.detail)
    )
    }else{
      this.addUserSubscription = this.dataservice.addEmployee(userObj).subscribe(
        data=>{
        console.log(data)
        alert("user profile added successfully")
        this.router.navigate(['admin/users/userList'])
      },
      err => alert(err.error.detail)
    )
    }
  }

  ngOnDestroy(){
    if(this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
    if(this.getUserSubscription){
      this.getUserSubscription.unsubscribe()
    }
    if(this.editUserSubscription){
      this.editUserSubscription.unsubscribe()
    }
    if(this.addUserSubscription){
      this.addUserSubscription.unsubscribe()
    }
  }

}
