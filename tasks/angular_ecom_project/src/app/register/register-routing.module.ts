import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from '../app.component';
import { DataserveService } from '../dataserve.service';
import { RouterGuardGuard } from '../router-guard.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProductsComponent } from './products/products.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
    {
        path:'index', component: DashboardComponent, children: [
           { path: 'pro', component: ProductsComponent
        }, ], canActivate: [RouterGuardGuard]
    },
    {path:'', component: SignupComponent },

    {path:'signedin',redirectTo:'index',pathMatch:"full"},
    
    {path:'login', component: LoginComponent },
    {path:'home', component: HomeComponent },
    {path:'home1', component: HomeComponent },
    

];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    providers: [DataserveService, RouterGuardGuard],
    bootstrap: [AppComponent]
  })

export class RegisterRoutingModule { 
    
}


