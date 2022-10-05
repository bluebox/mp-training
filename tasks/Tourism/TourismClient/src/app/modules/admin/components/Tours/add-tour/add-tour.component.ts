import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TourItem } from 'src/app/Interfaces/TourInterface';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-tour',
  templateUrl: './add-tour.component.html',
  styleUrls: ['./add-tour.component.css']
})
export class AddTourComponent implements OnInit {

  constructor(private dataservice: DataService,
    private route: ActivatedRoute
    ) {
      this.route.params.subscribe(res => {
        if(parseInt(res['id'])){
          this.id = parseInt(res['id'])
          this.dataservice.getTour(parseInt(res['id'])).subscribe(data=> {
            let tourString = JSON.stringify(data)
            let tourObj = JSON.parse(tourString)
            this.TourForm.get('tour_name')?.setValue(tourObj.tour_name);
            this.TourForm.get('tour_from')?.setValue(tourObj.tour_from);
            this.TourForm.get('tour_to')?.setValue(tourObj.tour_to);
            this.TourForm.get('tour_type')?.setValue(tourObj.tour_type);
            this.TourForm.get('nights')?.setValue(tourObj.nights);
            this.TourForm.get('days')?.setValue(tourObj.days);
            this.TourForm.get('price')?.setValue(tourObj.price);
            this.TourForm.get('image')?.setValue(tourObj.image);
            this.TourForm.get('description')?.setValue(tourObj.description);
            // this.TourForm.get('tour_name')?.setValue(tourObj.tour_name);
            // this.TourForm.get('tour_name')?.setValue(tourObj.tour_name);
            this.vehicleid.setValue(tourObj.vehicleid)
            this.coupons.setValue(tourObj.coupons.map((coupon: { id: any; }) => coupon.id))
            this.places.setValue(tourObj.places.map((place: { id: any; }) => place.id))
          });
        }
      })
  }
  placesList: any;
  couponsList: any;
  vehicleList: any;
  id!: number;

  TourForm: FormGroup = new FormGroup({
    tour_name : new FormControl('', [Validators.required]),
    tour_from : new FormControl('', [Validators.required]),
    tour_to : new FormControl('', [Validators.required]),
    tour_type : new FormControl('', [Validators.required]),
    nights : new FormControl('', [Validators.required]),
    days : new FormControl('', [Validators.required]),
    price : new FormControl('', [Validators.required]),
    image : new FormControl('', [Validators.required]),
    description : new FormControl('', [Validators.required]),
  })


  vehicleid = new FormControl('');
  coupons = new FormControl('');
  places = new FormControl('');



  ngOnInit(): void {
    this.dataservice.getPlaces().subscribe(res=> {
      let data = JSON.stringify(res)
      let toursData = JSON.parse(data)
      this.placesList = toursData;
    })
    this.dataservice.getCoupons().subscribe(res=> {
      let data = JSON.stringify(res)
      let couponsData = JSON.parse(data)
      this.couponsList = couponsData;
    })
    this.dataservice.getVehicles().subscribe(res=> {
      let data = JSON.stringify(res)
      let vehicleData = JSON.parse(data)
      this.vehicleList = vehicleData;
    })

  }




  addTourObj() {
    if(this.id){
      let tour = {...this.TourForm.value, places:this.places.value, coupons:this.coupons.value, vehicleid:this.vehicleid.value}
      console.log(tour);
      this.dataservice.editTour(tour, this.id).subscribe(data=>console.log(data))
    }else{
      let tour = {...this.TourForm.value, places:this.places.value, coupons:this.coupons.value, vehicleid:this.vehicleid.value}
      console.log(tour);
      this.dataservice.addTour(tour).subscribe(data=>console.log(data))
    }
  }

}
