import { Component, OnInit } from '@angular/core';
import { DataserveService } from 'src/app/dataserve.service';
import { Router, ActivatedRoute } from '@angular/router';
import { product_type } from 'src/app/data';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  

  constructor(private dashdata: DataserveService) {

  }

link="https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-674010.jpg&fm=jpg"


  data:any
  ngOnInit(): void {
    this.dashdata.getProductTypes().subscribe(  newdata => this.data = newdata);

  }


}






// register(values: any) {
  //   console.log('values are:', values)
  // }
      // this.dashdata.getProducts().subscribe(newdata => {
    //   // delete newdata.image;
    //   console.log("ygvhb")
    //   this.data = newdata
    //   console.log(newdata)
    // }
    // )
      // name: string = 'bhargavi';
  // cartId:number[]=[]
  // Cart(id:number){
  //   console.log("cart",id)
  //   this.cartId.push(id)
  // }