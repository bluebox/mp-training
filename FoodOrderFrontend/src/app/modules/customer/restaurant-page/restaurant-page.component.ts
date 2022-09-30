import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from 'src/app/interfaces/restaurant';
import { RestaurantService } from 'src/app/services/restaurant.service';

@Component({
  selector: 'app-restaurant-page',
  templateUrl: './restaurant-page.component.html',
  styleUrls: ['./restaurant-page.component.css']
})
export class RestaurantPageComponent implements OnInit {


  name:any;
  res:any;
  obj:any;
  constructor(private route:ActivatedRoute,private router:Router, private resSer:RestaurantService) { }

  ngOnInit(): void {
    let item=this.route.snapshot.paramMap.get('name')
    this.name=item
    console.log(this.name)
    this.getData()
  }


  getData()
  {
    this.resSer.getOneRes(this.name).subscribe(data=>{
      this.res=data
      console.log("_____________")
      console.log(this.res)})

  }

}
