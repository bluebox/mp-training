import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Menu } from 'src/app/interfaces/menu';
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



  constructor(public dialog:MatDialog, private menu:MenuService) { }

  ngOnInit(): void {
  
    
  }




onFood()
{

}
}
