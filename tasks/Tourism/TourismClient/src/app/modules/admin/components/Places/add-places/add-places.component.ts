import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-places',
  templateUrl: './add-places.component.html',
  styleUrls: ['./add-places.component.css']
})
export class AddPlacesComponent implements OnInit {

  constructor(private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.dataservice.getPlace(parseInt(res['id'])).subscribe(data=> {
          let placeString = JSON.stringify(data)
          let placeObj = JSON.parse(placeString)
          this.PlaceForm.get('place_name')?.setValue(placeObj.place_name);
          this.PlaceForm.get('image')?.setValue(placeObj.image);
          this.PlaceForm.get('description')?.setValue(placeObj.description);
        });
      }
    })
  }
  id!: number

  PlaceForm: FormGroup = new FormGroup({
    place_name : new FormControl('', [Validators.required]),
    image : new FormControl('', [Validators.required, Validators.email]),
    description : new FormControl('', [Validators.required]),
  })


  ngOnInit(): void {
  }

  addPlaceObj() {
    if(this.id){
      this.dataservice.editPlace(this.PlaceForm.value, this.id).subscribe(data=>{
        console.log(data)
        this.router.navigate(['admin/places/placeList'])
      })
    }else{
      this.dataservice.addPlace(this.PlaceForm.value).subscribe(data=>{
        console.log(data)
        this.router.navigate(['admin/places/placeList'])
      })
    }
  }

}
