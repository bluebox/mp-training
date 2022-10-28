import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Menu } from 'src/app/interfaces/menu';
import { LoginService } from 'src/app/services/login.service';
import { MenuService } from 'src/app/services/menu.service';
import { AddFoodComponent } from '../add-food/add-food.component';
import { AddFoodtoMenuComponent } from '../menu/add-foodto-menu/add-foodto-menu.component';
import { MenuListComponent } from '../menu/menu-list/menu-list.component';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  public user:any;
  message=""

  constructor(public dialog:MatDialog, private menu:MenuService,private router:Router,private loginService:LoginService) { }

  ngOnInit(): void {
    this.loginCheck()
  
    
  }




onFood()
{

}

loginCheck(){
  this.loginService.loginCheck().subscribe((data)=>{
    console.log(data)
    this.user=data
    console.log("hi resss")
    console.log(this.user)
    this.message=`Hello ${data.body.restaurant_name}`
    
  })
  
}

}
