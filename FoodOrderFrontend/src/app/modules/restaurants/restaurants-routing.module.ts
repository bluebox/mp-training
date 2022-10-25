import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddFoodComponent } from './add-food/add-food.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { FoodComponent } from './main-page/food/food.component';
import { MainPageComponent } from './main-page/main-page.component';
import { MenublockComponent } from './main-page/menublock/menublock.component';
import { MenuComponent } from './menu/menu.component';
import { ProfileComponent } from './profile/profile.component';
import { SigninComponent } from './signin/signin.component';

const routes: Routes = [
  {path:'',component:HomeComponent,children:[
    {path:'login',component:LoginComponent},
    {path:'signin',component:SigninComponent},
    {path:'addfood',component:AddFoodComponent},
    {path:'main',component:MainPageComponent},
    {path:'menu',component:MenuComponent},
    {path:'food',component:FoodComponent},
    {path:'profile',component:ProfileComponent},
    {path:'menublock',component:MenublockComponent},
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantsRoutingModule { }
