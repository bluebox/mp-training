import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TourItem } from 'src/app/Interfaces/TourInterface';
import { DataServiceService } from 'src/app/services/data-service.service';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-tour',
  templateUrl: './add-tour.component.html',
  styleUrls: ['./add-tour.component.css']
})
export class AddTourComponent implements OnInit {

  routeSubscription!: Subscription;
  getTourSubscription!: Subscription;
  editTourSubscription!: Subscription;
  addTourSubscription!: Subscription;
  getPlaceSubscription!: Subscription;
  getCouponSubscription!: Subscription;
  getVehicleSubscription!: Subscription;
  getEmployeeSubscription!: Subscription;


  constructor(private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router,
    private dataService: DataServiceService
    ) {
      this.routeSubscription = this.route.params.subscribe(res => {
        if(parseInt(res['id'])){
          this.id = parseInt(res['id'])
          this.getTourSubscription = this.dataservice.getTour(parseInt(res['id'])).subscribe(
            data=> {
            let tourString = JSON.stringify(data)
            let tourObj = JSON.parse(tourString)
            this.TourForm.get('tour_name')?.setValue(tourObj.tour_name);
            this.TourForm.get('tour_from')?.setValue(tourObj.tour_from);
            this.TourForm.get('tour_to')?.setValue(tourObj.tour_to);
            this.TourForm.get('tour_type')?.setValue(tourObj.tour_type);
            this.TourForm.get('nights')?.setValue(tourObj.nights);
            this.TourForm.get('days')?.setValue(tourObj.days);
            this.TourForm.get('price')?.setValue(tourObj.price);
            let date = (new Date(tourObj.start_date)).toISOString()
            this.TourForm.get('start_date')?.setValue(date.split('Z')[0]);
            this.TourForm.get('image')?.setValue(tourObj.image);
            this.imageUrl = tourObj.image
            this.TourForm.get('description')?.setValue(tourObj.description);
            this.vehicleid.setValue(tourObj.vehicleid)
            this.coupons.setValue(tourObj.coupons.map((coupon: { id: any; }) => coupon.id))
            this.places.setValue(tourObj.places.map((place: { id: any; }) => place.id))
            this.guides.setValue(tourObj.guides.map((guide: { id: any; }) => guide.id))
            },
            err => alert(err.error.detail)
          );
        }
      })
  }
  placesList: any;
  couponsList: any;
  guideList: any;
  vehicleList: any;
  imagesSubscription!: Subscription;
  imageUrl!: string | null
  id!: number;
  submit!: boolean

  TourForm: FormGroup = new FormGroup({
    tour_name : new FormControl('', [Validators.required]),
    tour_from : new FormControl('', [Validators.required]),
    tour_to : new FormControl('', [Validators.required]),
    tour_type : new FormControl('', [Validators.required]),
    nights : new FormControl('', [Validators.required]),
    days : new FormControl('', [Validators.required]),
    price : new FormControl('', [Validators.required]),
    image : new FormControl(''),
    start_date : new FormControl('', [Validators.required]),
    description : new FormControl('', [Validators.required]),
  })

  get formObj(){
    return this.TourForm.controls
  }

  vehicleid = new FormControl('');
  coupons = new FormControl([]);
  places = new FormControl([], [Validators.required]);
  guides = new FormControl([], [Validators.required]);



  ngOnInit(): void {
    this.getPlaceSubscription = this.dataservice.getPlaces().subscribe(
      res=> {
      let data = JSON.stringify(res)
      let toursData = JSON.parse(data)
      this.placesList = toursData;
      },
      err => alert(err.error.detail)
    )
    this.getCouponSubscription = this.dataservice.getCoupons().subscribe(
      res=> {
      let data = JSON.stringify(res)
      let couponsData = JSON.parse(data)
      this.couponsList = couponsData;
      },
      err => alert(err.error.detail)
    )
    this.getVehicleSubscription = this.dataservice.getVehicles().subscribe(
      res=> {
        let data = JSON.stringify(res)
        let vehicleData = JSON.parse(data)
        this.vehicleList = vehicleData;
      },
      err => alert(err.error.detail)
    )
    this.getEmployeeSubscription = this.dataservice.getEmployeeList().subscribe(
      res=> {
        let data = JSON.stringify(res)
        let employeeData = JSON.parse(data)
        this.guideList = employeeData;
      },
      err => alert(err.error.detail)
    )

  }

  onchange(e:any){
    console.log(e.target.files[0]);
    this.imagesSubscription = this.dataService.uploadImage(e.target.files[0]).subscribe(data => {
      let dataString = JSON.stringify(data)
      this.imageUrl = JSON.parse(dataString)
    })
  }

  addTourObj() {
    this.submit = true
    console.log(this.TourForm.value, this.TourForm.valid);
    if(this.TourForm.valid && this.places.valid && this.coupons.valid && this.guides.valid){
      console.log("object");
      let tour = {...this.TourForm.value, places:this.places.value, coupons:this.coupons.value, vehicleid:this.vehicleid.value, image:this.imageUrl, guides:this.guides.value}
      console.log(tour);
      if(this.id){
        this.editTourSubscription = this.dataservice.editTour(tour, this.id).subscribe(
          data=>{
          console.log(data)
          alert("tour updated successfully")
          this.router.navigate(['admin/tours/tourList'])
        },
        err => alert(err.error.detail)
      )
      }else{
        this.addTourSubscription = this.dataservice.addTour(tour).subscribe(
          data=>{
            console.log(data);
            alert("tour added successfully")
            this.router.navigate(['admin/tours/tourList'])
          },
          err => alert(err.error.detail)
        )
      }
    }
  }

  ngOnDestroy(){
    if(this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
    if(this.getTourSubscription){
      this.getTourSubscription.unsubscribe()
    }
    if(this.editTourSubscription){
      this.editTourSubscription.unsubscribe()
    }
    if(this.addTourSubscription){
      this.addTourSubscription.unsubscribe()
    }
    if(this.getVehicleSubscription){
      this.getVehicleSubscription.unsubscribe()
    }
    if(this.getCouponSubscription){
      this.getCouponSubscription.unsubscribe()
    }
    if(this.getPlaceSubscription){
      this.getPlaceSubscription.unsubscribe()
    }
    if(this.imagesSubscription){
      this.imagesSubscription.unsubscribe()
    }
    if(this.getEmployeeSubscription){
      this.getEmployeeSubscription.unsubscribe()
    }
  }

}
