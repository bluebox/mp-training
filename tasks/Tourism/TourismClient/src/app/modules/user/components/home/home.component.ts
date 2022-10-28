import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { FeedBack } from 'src/app/Interfaces/FeedbackInterface';
import { TourItem } from 'src/app/Interfaces/TourInterface';
import { DataService } from 'src/app/modules/admin/services/data.service';
import { AuthService } from 'src/app/services/auth.service';
import { DataServiceService } from 'src/app/services/data-service.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dataService: DataServiceService,
    private route: Router,
    private dataservice: DataService,
  ) { }


  feedbacks!: FeedBack[];
  packagesList: any;
  feedbackSubscription!: Subscription;
  getPackagesSubscription!: Subscription;


  ngOnInit(): void {

    this.feedbackSubscription = this.dataService.getFeedBacks().subscribe(
      data => {
        let res = JSON.stringify(data)
        let resObj = JSON.parse(res)
        this.feedbacks = resObj.slice(0,3)
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
    this.getPackagesSubscription = this.dataservice.getAllPackageList().subscribe(
      data => {
        // console.log(data);
        this.packagesList = data;
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

  navigateToTours(tourType : string) {
    this.route.navigate(['user/tours', tourType])
  }

  goToTours(id: number){
    this.route.navigate(['user/package', id])
  }

  ngOnDestroy() {
    if(this.feedbackSubscription){
      this.feedbackSubscription.unsubscribe()
    }
    if(this.getPackagesSubscription){
      this.getPackagesSubscription.unsubscribe()
    }
  }

}
