import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Food } from 'src/app/interfaces/food';
import { SearchService } from 'src/app/services/search.service';
import { ActivatedRoute } from '@angular/router';
import { Search } from 'src/app/interfaces/searchResult';
import { Restaurant } from 'src/app/interfaces/restaurant';
import { RestaurantService } from 'src/app/services/restaurant.service';
@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {
  
  res:Restaurant[]=[];
  oneRes:Restaurant[]=[];
  
  public name:any
  constructor( private fsearch:SearchService,private route:ActivatedRoute,private router:Router, private resSer:RestaurantService) { }

  ngOnInit(): void {
    

    let item=this.route.snapshot.paramMap.get('name')
    this.name=item
    this.getData()
  }

  getData(){
  this.fsearch.getSearch(this.name).subscribe(data=>{
     this.res=data
     console.log(this.res)

    console.log(typeof(this.res))
    })
  }

  resClicked=(r:Restaurant)=>
  {
    this.resSer.getOneRes(r.restaurant_id).subscribe(data=>{
      console.log(data)
      this.oneRes=data})

      this.router.navigate(['/customer/restaurantpage',r.restaurant_id])

  }




}
