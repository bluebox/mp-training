import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-tours-list',
  templateUrl: './tours-list.component.html',
  styleUrls: ['./tours-list.component.css']
})
export class ToursListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  tourList:any;

  ngOnInit(): void {
    this.dataService.getToursList().subscribe(data => this.tourList = data)
  }

  editTour(id: number){
    this.route.navigate(['admin/tours/addTour', id])
  }

}
