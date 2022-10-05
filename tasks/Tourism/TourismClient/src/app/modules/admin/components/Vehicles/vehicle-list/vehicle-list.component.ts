import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  vehicleList:any;

  ngOnInit(): void {
    this.dataService.getVehiclesList().subscribe(data => this.vehicleList = data)
  }

  editTour(id: number){
    this.route.navigate(['admin/vehicles/addVehicle', id])
  }

}
