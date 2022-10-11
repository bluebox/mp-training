import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {

    this.route.params.subscribe(data => {
      console.log(data['tourType']);
      this.tourType = data['tourType']
    })
    this.toursSubscription = this.dataService.getAdventureTours().subscribe(
      data => {
        let res = JSON.stringify(data)
        let resObj = JSON.parse(res)
        if(this.tourType && this.tourType != ""){
          this.toursList = resObj.filter((tour: { tour_type: string; }) => tour.tour_type.toLowerCase().includes(this.tourType.toLowerCase()))
        }else{
          this.toursList = resObj;
        }
      },
      err => alert(err.error.detail)
    )
  }

  ngOnDestroy() {
    if(this.toursSubscription){
      this.toursSubscription.unsubscribe()
    }
  }

}
