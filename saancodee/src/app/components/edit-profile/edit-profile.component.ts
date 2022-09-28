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


  profileForm = this.fb.group(
    {
      full_name: ['', [Validators.required, Validators.minLength(6)]],
      gender: [''],
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

  constructor(private router: Router, private fb: FormBuilder, private service: RegisterService) { }

  onSubmit() {
    console.log(this.profileForm.value)
    this.service.editProfile(this.profileForm.value).subscribe((data:any) => {
      console.log(data)
      this.router.navigate(['/profile'])
    })
  }

  ngOnInit(): void {
  }

}
