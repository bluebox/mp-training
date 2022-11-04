import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TourItem } from 'src/app/Interfaces/TourInterface';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-tour-page',
  templateUrl: './tour-page.component.html',
  styleUrls: ['./tour-page.component.css']
})
export class TourPageComponent implements OnInit {

  subscription!: Subscription
  tourObject!: TourItem
  // tourImages!: string[]


  constructor(private route: ActivatedRoute,
    private auth: DataServiceService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.route.params.subscribe(data => {
      let id = parseInt(data['id'])
      this.subscription = this.auth.getTourById(id).subscribe(
        data => {
          let res = JSON.stringify(data)
          let resObj = JSON.parse(res)
          this.tourObject = resObj
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

  bookTour(id: number) {
    this.router.navigate(['user/checkOut', id])
  }

  ngOnDestroy() {
    if(this.subscription){
      this.subscription.unsubscribe();
    }
  }

}
