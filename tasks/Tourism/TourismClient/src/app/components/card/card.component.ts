import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TourItem } from 'src/app/Interfaces/TourInterface';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor(private route: Router) { }
  @Input() tourItem!: TourItem;

  ngOnInit(): void {
  }

  goToTourPage(tour: TourItem){
    this.route.navigate(['user/tour', tour.id])
  }

}
