import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TourItem } from 'src/app/Interfaces/TourInterface';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-tour',
  templateUrl: './tour.component.html',
  styleUrls: ['./tour.component.css']
})
export class TourComponent implements OnInit {

  toursList!: TourItem[];
  toursSubscription!: Subscription;
  tourType!:string



  constructor(private dataService: DataServiceService,
    private route: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {

    this.route.params.subscribe(data => {
      console.log(data['tourType']);
      this.tourType = data['tourType']
    })
    this.toursSubscription = this.dataService.filterTours(this.tourType).subscribe(
      (data: any) => {
        // let res = JSON.stringify(data)
        // let resObj = JSON.parse(res)
        this.toursList = data;

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

    // if(this.tourType && this.tourType != ""){

    //   // this.toursList = resObj.filter((tour: { tour_type: string; }) => tour.tour_type.toLowerCase().includes(this.tourType.toLowerCase()))
    // }else{
    //   this.toursList = resObj;
    // }

  }

  goToTourPage(tour: TourItem){
    this.router.navigate(['user/tour', tour.id])
  }

  ngOnDestroy() {
    if(this.toursSubscription){
      this.toursSubscription.unsubscribe()
    }
  }

}
