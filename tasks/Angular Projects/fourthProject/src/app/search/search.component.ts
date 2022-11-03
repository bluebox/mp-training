import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
color:string='';
price=0;
size:string='';
isDirty=true;
  constructor(private activateRoute:ActivatedRoute) { 
    this.activateRoute.queryParams.subscribe(params=>{
      console.log(params)
      this.color=params['color']
      this.price=params['price']
      this.size=params['size']
    })
  }

  ngOnInit(): void {
  }

}
