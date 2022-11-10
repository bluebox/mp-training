import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators } from '@angular/forms'
import { Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {


  full_name: any;
  gender: any;
  location: any;
  bio: any;
  work: any;
  education: any;
  mobile: any;
  github: any;
  facebook: any;
  instagram: any;

  profileForm = this.fb.group(
    {
      full_name: ['', [Validators.required, Validators.minLength(6)]],
      gender: [''],
      private: [],
      location: [''],
      bio: [''],
      work: [''],
      education: [''],
      mobile: [''],
      github: [''],
      facebook: [''],
      instagram: ['']
    }
  )

  constructor(private router: Router, private fb: FormBuilder, private service: RegisterService) { 
    let profile = this.service.profile;
    this.full_name = profile.full_name;
    this.gender = profile.gender;
    this.location = profile.location;
    this.bio = profile.bio;
    this.work = profile.work;
    this.education = profile.education;
    this.mobile = profile.mobile;
    this.github = profile.github;
    this.facebook = profile.facebook;
    this.instagram = profile.instagram;
   }

  onSubmit() {
    console.log(this.profileForm.value)
    this.service.editProfile(this.profileForm.value).subscribe((data:any) => {
      this.service.profile = data;
      console.log(this.service.profile);
      console.log("////////////");
      let username = localStorage.getItem('username')
      this.router.navigate(['profile', username]);
    })
  }

  ngOnInit(): void {
  }

}
