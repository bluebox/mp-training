import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-package',
  templateUrl: './add-package.component.html',
  styleUrls: ['./add-package.component.css']
})
export class AddPackageComponent implements OnInit {

  routeSubscription!: Subscription;
  getPackageSubscription!: Subscription;
  editPackageSubscription!: Subscription;
  addPackageSubscription!: Subscription;
  imagesSubscription!: Subscription;
  imageUrl!: string | null;
  // videoUrl!: string | null;
  getTourSubscription!: Subscription;
  tourList: any;

  constructor(private dataservice: DataService,
    private dataService: DataServiceService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.routeSubscription = this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.getPackageSubscription = this.dataservice.getPackage(parseInt(res['id'])).subscribe(
          data=> {
          let packageString = JSON.stringify(data)
          let packageObj = JSON.parse(packageString)
          console.log(packageObj);
          this.PackageForm.get('package_name')?.setValue(packageObj.package_name);
          this.PackageForm.get('package_type')?.setValue(packageObj.package_type);
          this.PackageForm.get('image')?.setValue(packageObj.image);
          this.imageUrl = packageObj.image
          // this.videoUrl = packageObj.video
          this.PackageForm.get('description')?.setValue(packageObj.description);
          this.tours.setValue(packageObj.tours);
        },
        err => alert(err.error.detail)
      );
      }
    })
  }
  id!: number

  onchange(e:any){
    console.log(e.target.files[0]);
    this.imagesSubscription = this.dataService.uploadImage(e.target.files[0]).subscribe(data => {
      let dataString = JSON.stringify(data)
      this.imageUrl = JSON.parse(dataString)
    })
  }

  PackageForm: FormGroup = new FormGroup({
    package_name : new FormControl('', [Validators.required]),
    package_type : new FormControl(''),
    image : new FormControl(''),
    description : new FormControl('', [Validators.required]),
  })
  tours = new FormControl('', [Validators.required])

  get formObj(){
    return this.PackageForm.controls
  }


  ngOnInit(): void {
    this.getTourSubscription = this.dataservice.getToursList().subscribe(
      data=> {
        this.tourList = data;
      },
      err => alert(err.error.detail)
    )
  }

  addPackageObj() {
    if(this.PackageForm.valid && this.tours.valid){
      let packageObj = {...this.PackageForm.value, image:this.imageUrl, tours:this.tours.value}
      console.log(packageObj);
      if(this.id){
        this.editPackageSubscription = this.dataservice.editPackage(packageObj, this.id).subscribe(
          data=>{
          console.log(data)
          alert("package updated successfully")
          this.router.navigate(['admin/package/packageList'])
        },
        err => alert(err.error.detail)
      )
      }else{
        this.addPackageSubscription = this.dataservice.addPackage(packageObj).subscribe(
          data=>{
          console.log(data)
          alert("package added successfully")
          this.router.navigate(['admin/package/packageList'])
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
    if(this.getPackageSubscription){
      this.getPackageSubscription.unsubscribe()
    }
    if(this.editPackageSubscription){
      this.editPackageSubscription.unsubscribe()
    }
    if(this.addPackageSubscription){
      this.addPackageSubscription.unsubscribe()
    }
    if(this.getTourSubscription){
      this.getTourSubscription.unsubscribe()
    }
  }

}
