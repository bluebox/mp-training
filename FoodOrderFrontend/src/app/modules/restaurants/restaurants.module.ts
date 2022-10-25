import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RestaurantsRoutingModule } from './restaurants-routing.module';
import { HomeComponent } from './home/home.component';
import { SigninComponent } from './signin/signin.component';
import { LoginComponent } from './login/login.component';
import {MatFormFieldModule} from '@angular/material/form-field'; 
import {MatInputModule} from '@angular/material/input'; 
import {MatToolbarModule} from '@angular/material/toolbar'; 
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import { AddFoodComponent } from './add-food/add-food.component';
import {MatCheckboxModule} from '@angular/material/checkbox'; 
import { FormsModule } from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
import { MainPageComponent } from './main-page/main-page.component';
import { MenuComponent } from './menu/menu.component';
import { MenuListComponent } from './menu/menu-list/menu-list.component';
import { AddFoodtoMenuComponent } from './menu/add-foodto-menu/add-foodto-menu.component';
import { FoodComponent } from './main-page/food/food.component';
import { MenublockComponent } from './main-page/menublock/menublock.component';
import { NavbarComponent } from './navbar/navbar.component';
import { EditUserComponent } from './edit-user/edit-user.component';



@NgModule({
  declarations: [
    HomeComponent,
    SigninComponent,
    LoginComponent,
    AddFoodComponent,
    MainPageComponent,
    MenuComponent,
    MenuListComponent,
    AddFoodtoMenuComponent,
    FoodComponent,
    MenublockComponent,
    NavbarComponent,
    EditUserComponent
 
  
  ],
  imports: [
    CommonModule,
    RestaurantsRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    FormsModule,
    MatDialogModule,
    MatIconModule

  ]
})
export class RestaurantsModule { }
