import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

interface Review {
  title: string;
  reviews: string;
  email: string;

}

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {

  reviews: Review[] = [];


  constructor(private auth: AuthService) { }

  ngOnInit(): void {
    this.auth.getreviews().subscribe(data => {
      this.reviews = data;
      console.log(this.reviews);

    });


  }

}
