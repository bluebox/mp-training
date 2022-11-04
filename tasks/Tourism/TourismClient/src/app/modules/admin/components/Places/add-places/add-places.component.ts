import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-places',
  templateUrl: './add-places.component.html',
  styleUrls: ['./add-places.component.css']
})
export class AddPlacesComponent implements OnInit {

  routeSubscription!: Subscription;
  getPlaceSubscription!: Subscription;
  editPlaceSubscription!: Subscription;
  addPlaceSubscription!: Subscription;
  imagesSubscription!: Subscription;
  imageUrl!: string | null

  constructor(private dataservice: DataService,
    private dataService: DataServiceService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.routeSubscription = this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.getPlaceSubscription = this.dataservice.getPlace(parseInt(res['id'])).subscribe(
          data=> {
          let placeString = JSON.stringify(data)
          let placeObj = JSON.parse(placeString)
          this.PlaceForm.get('place_name')?.setValue(placeObj.place_name);
          this.PlaceForm.get('image')?.setValue(placeObj.image);
          this.imageUrl = placeObj.image
          this.PlaceForm.get('description')?.setValue(placeObj.description);
        },
        err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
      );
      }
    })
  }
  id!: number
  submit!: boolean

  onchange(e:any){
    console.log(e.target.files[0]);
    this.imagesSubscription = this.dataService.uploadImage(e.target.files[0]).subscribe(data => {
      let dataString = JSON.stringify(data)
      this.imageUrl = JSON.parse(dataString)
    })
  }

  PlaceForm: FormGroup = new FormGroup({
    place_name : new FormControl('', [Validators.required]),
    image : new FormControl('', [Validators.required]),
    description : new FormControl('', [Validators.required]),
  })

  get formObj(){
    return this.PlaceForm.controls
  }


  ngOnInit(): void {
  }

  addPlaceObj() {
    this.submit = true
    this.PlaceForm.get('image')?.setValue(this.imageUrl)
    if(this.PlaceForm.valid){
      if(this.id){
        this.editPlaceSubscription = this.dataservice.editPlace({...this.PlaceForm.value, image:this.imageUrl}, this.id).subscribe(
          data=>{
          console.log(data)
          alert("Place updated successfully")
          this.router.navigate(['admin/places/placeList'])
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
      }else{
        this.addPlaceSubscription = this.dataservice.addPlace({...this.PlaceForm.value, image:this.imageUrl}).subscribe(
          data=>{
          console.log(data)
          alert("Place added successfully")
          this.router.navigate(['admin/places/placeList'])
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
      }
    }
  }

  ngOnDestroy(){
    if(this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
    if(this.getPlaceSubscription){
      this.getPlaceSubscription.unsubscribe()
    }
    if(this.editPlaceSubscription){
      this.editPlaceSubscription.unsubscribe()
    }
    if(this.addPlaceSubscription){
      this.addPlaceSubscription.unsubscribe()
    }
    if(this.imagesSubscription){
      this.imagesSubscription.unsubscribe()
    }
  }

}
