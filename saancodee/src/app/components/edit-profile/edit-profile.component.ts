import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms'

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  profileForm = this.fb.group(
    {
      fullname: [''],
      gender: [''],
      location: [''],
      bio: [''],
      work: [''],
      education: ['']
    }
  )

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
  }

}
