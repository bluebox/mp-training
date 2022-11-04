import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieOptions, CookieService } from 'ngx-cookie-service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-item-view',
  templateUrl: './item-view.component.html',
  styleUrls: ['./item-view.component.css']
})
export class ItemViewComponent implements OnInit {

  contains_id: any;
  item_details: any;
  constructor(private service: DataServiceService, private aroute: ActivatedRoute,private router:Router, private cservice:CookieService) {
    this.aroute.params.subscribe(data => {
      this.contains_id = data['contains_id']
      
    this.service.getItem(this.contains_id).subscribe(data => {
      this.item_details = data; })
    console.log(this.item_details[0])
      
    })
   
  }

  ngOnInit(): void {
  }

  addToCart(contains_id:any){
    this.service.addToCart(contains_id).subscribe(data=>console.log(data))

  }
  buyNow(contains_id:any){
    this.service.addToCart(contains_id).subscribe(data=>console.log(data))
    this.router.navigate(['viewCart',this.cservice.get('username')])

  }

  goToCart(){
    this.router.navigate(['viewCart',this.cservice.get('username')])
  }

}
