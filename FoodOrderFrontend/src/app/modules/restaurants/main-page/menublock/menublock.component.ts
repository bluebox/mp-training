import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Menu } from 'src/app/interfaces/menu';
import { MenuService } from 'src/app/services/menu.service';
import { AddFoodtoMenuComponent } from '../../menu/add-foodto-menu/add-foodto-menu.component';
import { MenuListComponent } from '../../menu/menu-list/menu-list.component';
import { MenuComponent } from '../../menu/menu.component';

@Component({
  selector: 'app-menublock',
  templateUrl: './menublock.component.html',
  styleUrls: ['./menublock.component.css']
})
export class MenublockComponent implements OnInit {
  public menuItem:Menu[]=[];
  constructor(public dialog:MatDialog, private menu:MenuService) { }

  ngOnInit(): void {
    this.getData()
  }
  newMenu()
  {
    this.dialog.open(MenuComponent, {
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
  }

  getData()
{
  this.menu.getMenu().subscribe(data=>{
    console.log(data);this.menuItem=data
  ;console.log(this.menuItem)})

}

addFood()
{
  this.dialog.open(AddFoodtoMenuComponent, {
    autoFocus: false,
    maxHeight: '90vh' //you can adjust the value as per your view
})
}

menuList()
{
  this.dialog.open(MenuListComponent, {
    autoFocus: false,
    maxHeight: '90vh' //you can adjust the value as per your view
})
}

}
