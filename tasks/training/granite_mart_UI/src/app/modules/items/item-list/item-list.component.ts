import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  items_list:any
  constructor(private service:DataServiceService,private router:Router) {
    this.service.getItems().subscribe(data=>{this.items_list=data;console.log(this.items_list[0])})

   }

  ngOnInit(): void {
  }
  viewItem(contains_id:any){
    this.router.navigate(['viewItem',contains_id.toString()])

  }
  mouseEnter(contains_id:any){

    let element:any=document.getElementById(contains_id.toString())
    console.log(contains_id,element)
    element.style["display"]='block';
  }
  mouseExit(contains_id:any){

    let element:any=document.getElementById(contains_id.toString())
    console.log(contains_id,element)
    element.style["display"]='none';
  }
    
}
