import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { FeedBack } from 'src/app/Interfaces/FeedbackInterface';
import { TourItem } from 'src/app/Interfaces/TourInterface';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dataService: DataServiceService,
    private route: Router
  ) { }

  feedbacks!: FeedBack[];
  feedbackSubscription!: Subscription;


  ngOnInit(): void {

    this.feedbackSubscription = this.dataService.getFeedBacks().subscribe(data => {
      let res = JSON.stringify(data)
      let resObj = JSON.parse(res)
      this.feedbacks = resObj
    })
  }

  navigateToTours(tourType : string) {
    this.route.navigate(['user/tours', tourType])
  }


  ngOnDestroy() {
    if(this.feedbackSubscription){
      this.feedbackSubscription.unsubscribe()
    }
  }



}
