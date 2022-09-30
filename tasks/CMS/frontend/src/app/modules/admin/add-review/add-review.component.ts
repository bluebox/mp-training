import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  reviewForm!: FormGroup;
  email = new FormControl('', [Validators.email, Validators.required]);
  title = new FormControl('', [Validators.required]);
  reviews = new FormControl('', [Validators.required]);


  constructor(private formBuilder: FormBuilder, private auth: AuthService) { }
  ngOnInit(): void {
    this.reviewForm = this.formBuilder.group({
      email: this.email,
      title: this.title,
      reviews: this.reviews
    })


  }
  onSubmit() {
    this.auth.addReview(this.reviewForm.value).subscribe(data => console.log(data));

  }
}