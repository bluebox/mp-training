import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-view-cart',
  templateUrl: './view-cart.component.html',
  styleUrls: ['./view-cart.component.css']
})
export class ViewCartComponent implements OnInit {
  cart_data: any=""
  username: any
  quantities: any
  // buyingData = new Observable()
  constructor(private service: DataServiceService, private aroute: ActivatedRoute, private router: Router) {
    this.aroute.params.subscribe(data => this.username = data['username'])
    this.service.getCart(this.username).subscribe(data => { console.log(data); this.cart_data = data }, err => console.log(err))

  }

  ngOnInit(): void {
    
  }

  increment(contains_id: any) {
    let ele = document.getElementById(contains_id.toString())
    if(ele!=null && typeof(ele.textContent)=='string')
    ele.textContent= (parseInt(ele.textContent)+1).toString()
    this.service.updateCart(contains_id, +1).subscribe(data => console.log(data))
    // this.router.navigate(['viewCart', this.username])
    this.aroute.params.subscribe(data => this.username = data['username'])
    this.service.getCart(this.username).subscribe(data => { console.log(data); this.cart_data = data }, err => console.log(err))
    // window.location.reload()
    
    
  }
  decrement(contains_id: any) {

    let ele = document.getElementById(contains_id.toString())
    if(ele!=null && typeof(ele.textContent)=='string'){
      if(parseInt(ele.textContent)>1)
        ele.textContent= (parseInt(ele.textContent)-1).toString()
      else
        window.location.reload()
    }
    
    this.service.updateCart(contains_id, -1).subscribe(data => console.log(data))
    this.router.navigate(['viewCart', this.username])
    // window.location.reload()

  }

  delete(contains_id:any){
    this.service.deleteCart(contains_id).subscribe(data=>{console.log(data);
    if(data=='successfull'){
      window.location.reload()
    }
  })

  }

  continue(){
    let arr = this.cart_data.data
    if(arr.length>0){
    let confirm=window.confirm("proceed to buy ?")
    if(confirm==true){
      this.router.navigate(['placeOrder',this.username])
    }
    }
  }

}
