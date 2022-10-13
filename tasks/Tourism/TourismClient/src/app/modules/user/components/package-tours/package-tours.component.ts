import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TourItem } from 'src/app/Interfaces/TourInterface';
import { DataService } from 'src/app/modules/admin/services/data.service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-package-tours',
  templateUrl: './package-tours.component.html',
  styleUrls: ['./package-tours.component.css']
})
export class PackageToursComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private dataService: DataServiceService,
    private router: Router
    ) { }
    packageName: any
    tourList: any

  ngOnInit(): void {
    this.route.params.subscribe(data=>{
      this.dataService.getPackage(parseInt(data['id'])).subscribe(
        res => {
          let packageString = JSON.stringify(res);
          let packageData = JSON.parse(packageString);
          this.packageName = packageData.package_name;
          this.tourList = packageData.tours;
          console.log(this.tourList);
        }
      )
    })
  }
  goToTourPage(tour: TourItem){
    this.router.navigate(['user/tour', tour.id])
  }

}
