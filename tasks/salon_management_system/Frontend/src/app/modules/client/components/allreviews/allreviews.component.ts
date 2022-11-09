import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-allreviews',
  templateUrl: './allreviews.component.html',
  styleUrls: ['./allreviews.component.css']
})
export class AllreviewsComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  reviews:any;
  displayedColumns :string[]=['review_id','Appointment_id','comments_and_reviews','rating'];
  constructor(private http : HttpserviceService) { }

  ngOnInit(): void {
    this.subscription =this.http.getreviews().subscribe((data) =>{this.reviews = data ;console.log(data)});

}
}
