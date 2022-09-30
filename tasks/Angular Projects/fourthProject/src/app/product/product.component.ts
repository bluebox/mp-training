import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
personId=0;
photoId=0;
  constructor(private activateRoute:ActivatedRoute) {
    this.activateRoute.params.subscribe((params)=>{
      console.log(params);
      const tempValues=params
      this.personId=tempValues['id'];
      this.photoId=tempValues['photoId'];

    })
   }

  ngOnInit(): void {
  }

}
