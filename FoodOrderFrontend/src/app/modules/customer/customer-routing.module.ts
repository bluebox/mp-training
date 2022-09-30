import { NgModule } from '@angular/core';
import { RouterModule, ROUTES, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RestaurantPageComponent } from './restaurant-page/restaurant-page.component';
import { SearchResultComponent } from './search-result/search-result.component';
import { SigninComponent } from './signin/signin.component';



const routes: Routes = [
    {path:'',component:HomeComponent,children:[
      {path:'login',component:LoginComponent},
      {path:'signin',component:SigninComponent},
      {path:'searchresult/:name',component:SearchResultComponent},
      {path:'restaurantpage/:name',component:RestaurantPageComponent}

    ]}
  ];
  
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class CustomerRoutingModule { }