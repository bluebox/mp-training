import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  constructor(private auth: AuthService,
    private route: ActivatedRoute,
    private dataService: DataServiceService
    ) {
      console.log("edit profile component");
      // this.auth.currentLoginUser.subscribe(
      //   user => {
          // console.log(user)
          this.currentUser = this.auth.currentUser
          console.log(this.currentUser);
          this.ProfileEditForm.get('name')?.setValue(this.currentUser.name)
          this.ProfileEditForm.get('image')?.setValue(this.currentUser.image)
          // this.ProfileEditForm.get('email')?.setValue(this.currentUser.email)
          this.imageUrl = this.currentUser.image
          this.ProfileEditForm.get('mobile')?.setValue(this.currentUser.mobile)
      //   },
      //   err => {
        //   if(err.status == 404){
        //     alert(err.message)
        //   }
        //   else{
        //     alert(err.error.detail)
        //   }
        // }
      // )
    }

  currentUser!: any;
  imageUrl: string | undefined;
  subscription!: Subscription
  imagesSubscription!: Subscription

  ProfileEditForm: FormGroup = new FormGroup({
    name : new FormControl('', [Validators.required]),
    // email : new FormControl('', [Validators.required, Validators.email]),
    image : new FormControl(''),
    // password : new FormControl('', [Validators.required]),
    // confirm_password : new FormControl('', [Validators.required]),
    mobile : new FormControl('', [Validators.required]),
  })

  ngOnInit(): void {
    this.route.params.subscribe(res=>{
      this.auth.currentLoginUser.subscribe(
        user => {
          console.log(user)
          this.currentUser = user
          console.log(this.currentUser);
          this.ProfileEditForm.get('name')?.setValue(this.currentUser.name)
          this.ProfileEditForm.get('image')?.setValue(this.currentUser.image)
          this.imageUrl = this.currentUser.image
          this.ProfileEditForm.get('mobile')?.setValue(this.currentUser.mobile)
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
    })
  }

  onchange(e:any){
    console.log(e.target.files[0]);
    this.imagesSubscription = this.dataService.uploadImage(e.target.files[0]).subscribe(data => {
      let dataString = JSON.stringify(data)
      this.imageUrl = JSON.parse(dataString)
    })
  }

  editProfile(){
    console.log("object");
    let editedUserObj = {...this.currentUser, ...this.ProfileEditForm.value, image:this.imageUrl}
    console.log(editedUserObj);
    this.auth.editUserProfile(editedUserObj).subscribe(
      user=>{
        alert("User updated successfully")
      },
      err=>alert(err.error.detail)
    )
  }

  ngOnDestroy(): void {
    if(this.subscription){
      this.subscription.unsubscribe();
    }
    if(this.imagesSubscription){
      this.imagesSubscription.unsubscribe();
    }
  }


}
