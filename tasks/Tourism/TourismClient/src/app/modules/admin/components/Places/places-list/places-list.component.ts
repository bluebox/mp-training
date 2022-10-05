import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-places-list',
  templateUrl: './places-list.component.html',
  styleUrls: ['./places-list.component.css']
})
export class PlacesListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  placeList:any;

  ngOnInit(): void {
    this.dataService.getPlacesList().subscribe(data => this.placeList = data)
  }

  editPlace(id: number){
    this.route.navigate(['admin/places/addPlace', id])
  }
}
