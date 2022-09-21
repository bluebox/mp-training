import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutGaurdGuard } from '../about-gaurd.guard';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';
import { MainComponent } from './components/main/main.component';

const routes: Routes = [
  {
    path: '',
        component: MainComponent,
        // this gaurd restricts entrance into this paths
        // canActivate: [AboutGaurdGuard],
        children: [
            { path: '', component: HomeComponent },
            { path: 'home', component: HomeComponent },
            { path: 'about', component: AboutComponent},
            { path: 'about/:name', component: AboutComponent}
        ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
