import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from 'src/app/modules/admin/services/data.service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-package-tours',
  templateUrl: './package-tours.component.html',
  styleUrls: ['./package-tours.component.css']
})
export class PackageToursComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private dataService: DataServiceService
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

}
