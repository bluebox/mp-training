import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { FeedBack } from 'src/app/Interfaces/FeedbackInterface';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-all-feedbacks',
  templateUrl: './all-feedbacks.component.html',
  styleUrls: ['./all-feedbacks.component.css']
})
export class AllFeedbacksComponent implements OnInit {

  constructor(private dataService: DataServiceService) { }
  feedbackSubscription!: Subscription;
  averageRatingSubscription!: Subscription;
  feedbacks!: FeedBack[];
  ratingObj: any;

  ngOnInit(): void {
    this.feedbackSubscription = this.dataService.getFeedBacks().subscribe(
      data => {
        let res = JSON.stringify(data)
        let resObj = JSON.parse(res)
        this.feedbacks = resObj
      },
      err => {
          if(err.status == 404 || 500){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
    )
    this.averageRatingSubscription = this.dataService.getAverageRatingAndTotalRatings().subscribe(
      data => {
        console.log(data);
        this.ratingObj = data;
      },
      err => {
          if(err.status == 404 || 500){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
    )
  }

  ngOnDestroy(){
    if(this.averageRatingSubscription){
      this.averageRatingSubscription.unsubscribe()
    }
    if(this.feedbackSubscription){
      this.feedbackSubscription.unsubscribe()
    }
  }
}
